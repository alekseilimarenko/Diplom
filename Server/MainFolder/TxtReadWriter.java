package server;

import dataBase.MyConnection;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.table.DefaultTableModel;

public class TxtReadWriter extends Thread
{
    private final Socket client;
    private MyConnection dbConnect;
    //private UserList users;
    private DefaultTableModel model;
    private Calendar cal;

    public TxtReadWriter(Socket client, DefaultTableModel mod)
    {
        this.client = client;
        this.model = mod;
        //this.users = map;
        //cal = new GregorianCalendar(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru"));
        setDaemon(true);
    }
    
    //прием и отправка сообщений сервером
    @Override
    public void run()
    {   
        byte[] b = new byte[16];
        ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
        try
        {
            while(true)
            {
                String answer;
                BAOS.reset();
                do{
                    int cnt = client.getInputStream().read(b, 0, b.length);
                    if (cnt == -1) throw new IOException("Received -1 byte"); 
                    BAOS.write(b, 0, cnt);
                }while(client.getInputStream().available() > 0);
            
                byte[] a = BAOS.toByteArray();
                String msg = new String(a, 0, a.length, "UTF8");
                String[] data = msg.split("_");
                System.out.println("На сервере получено : " + msg);
                switch (data[0])
                {
                    case "receiveFile" :
                        synchronized(this)
                        {
                            client.getOutputStream().write((ReceiveFileFromClient(data[1], Long.parseLong(data[2]))).getBytes("UTF8"));
                        }
                        break;
                    case "sendData" :
                        SendData(data[1]);
                        break;
                    case "exit" :
                        synchronized(this)
                        {
                            java.awt.EventQueue.invokeLater(() -> {
                                for(int i = 0; i < model.getRowCount(); i++)
                                {
                                    if(model.getValueAt(i, 0).equals(data[1]))

                                            model.removeRow(i);
                                }
                            });
                        }
                        break;
                    default :
                        answer = DBExchange(msg);                
                        System.out.println("С сервера отправлено : " + answer);
                        synchronized(this)
                        {
                            client.getOutputStream().write(answer.getBytes("UTF8"));
                        }
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(IOException ioe)
        {
            System.out.println("Соединение разорвано : " + client.getInetAddress().toString());
            synchronized(this)
            {
                java.awt.EventQueue.invokeLater(() ->
                {
                    for(int i = 0; i < model.getRowCount(); i++)
                    {
                        if(model.getValueAt(i, 2).equals(client.getPort()))
                            model.removeRow(i);
                    }
                });
            }
        }
    }
    
    //сохранение файла на сервер
    private String ReceiveFileFromClient(String str, Long fSize)
    {
        String path;
        
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
            cal = Calendar.getInstance(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru"));
            
            String tmp = str.replace('/', '_');
         
            //healthCard/Сидоренко-04/12/1963.rtf
            //Рентген/Сидоров-18/12/1988.rtf/14-12-2015
            
            DataInputStream input = new DataInputStream(client.getInputStream());
            String folName = "Patients/" + (tmp.substring(tmp.indexOf('_')+1, tmp.lastIndexOf(".")));
            File folder = new File(folName);
            
            if(!folder.exists()) folder.mkdirs();
            String[] s = str.split("/");
            if(s[0].equals("healthCard"))
                path = folder + "/" + tmp.substring(tmp.indexOf('_') + 1);
            else
                path = folder + "/" + sdf.format(cal.getTime()) + tmp.substring(tmp.indexOf('.'), tmp.lastIndexOf('_'));
            File file = new File(path);
            
            if(!file.exists()) file.createNewFile();
            
            byte[] buffer = new byte[32768];

            try (FileOutputStream outputFile = new FileOutputStream(path)) 
            {
                int count, total = 0;
                while((count = input.read(buffer)) != -1)
                {
                    total += count;
                    outputFile.write(buffer, 0, count);
                    if(total == fSize)
                        break;
                }   
                outputFile.flush();
            }
            
            String[] tbl = tmp.split("_");
            
            if(tbl[0].equals("healthCard"))
            {
                return dbConnect.AddHealthCard(path);
            }
            else
            {    
                return dbConnect.AddFileResultObsled(str, path);
            }
        }
        catch(SQLException sql)
        {
            sql.printStackTrace();
            return "errorDB";
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
            return "errorFile";
        }
    }
    
    //подготовка данных для отправки файла клиенту
    private void SendData(String str)
    {
        byte[] byteArray;
        String msg;
        try
        {
            byte[] b = new byte[4];
            if (str.compareTo("") == 0)
            {
                msg = "null";
                byteArray = msg.getBytes("UTF8");

                client.getOutputStream().write(byteArray, 0, byteArray.length);
            } 
            String[] tmp = str.split(":");
            if(tmp[0].equals("health"))
            {
                String fpath = dbConnect.GetHealthCard(tmp[1]);
                if(fpath.equals("null"))
                {
                    byteArray = "no_med".getBytes("UTF8");
                    client.getOutputStream().write(byteArray, 0, byteArray.length);
                }
                else
                {
                    File file = new File(fpath);
                    String fname = fpath.substring(fpath.indexOf('\\') + 1);
                    
                    SendFile("health:" + String.valueOf(file.length()+ ":" + fname), file);
                }
            }
            else
            {
                String[] answer = dbConnect.GetTestResult(tmp[1]).split(":");
                if(answer[0].equals("file"))
                {
                    File file = new File(answer[1]);
                    String fname = answer[1].substring(answer[1].indexOf('\\') + 1);
                    SendFile("test:" + String.valueOf(file.length() + ":" + fname), file);
                }
                else
                {
                    msg = answer[0] + ":" + answer[1];
                    byteArray = msg.getBytes("UTF8");

                    client.getOutputStream().write(byteArray, 0, byteArray.length);
                }
            }
            byteArray = null;
        }
        catch(SQLException sql)
        {
            sql.printStackTrace();  
        }
        catch(IOException ioe)
        {
            System.out.println("IOException #1 : " + ioe.getMessage());
        }
    }
    
    //отправка файла клиенту
    private void SendFile(String comm, File f) throws IOException
    {
        client.getOutputStream().write(comm.getBytes("UTF8"), 0, comm.getBytes("UTF8").length);
        byte[] buffer = new byte[32768];
        int count;
        FileInputStream inputFile = new FileInputStream(f);
        while((count = inputFile.read(buffer)) != -1)
        {
            client.getOutputStream().write(buffer, 0, count);
        }
        System.out.println("Файл успешно отправлен");
    }
    
    //работа сервера с базой данных
    private synchronized String DBExchange(String mess) throws SQLException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy/hh-mm-ss");
        
        dbConnect = new MyConnection();
        
        String[] tmp = mess.split("_");
        try
        {
             switch (tmp[0])
            {
                case "Hello":
                    return "OK";                                                //проверка соединения с сервером
                case "verifyLogin":
                    return dbConnect.VerifyLogin(tmp[1]);                       //проверка привутствия логина в базе данных
                case "getPass":
                    return dbConnect.GetPass(tmp[1]);                           //получение пароля для проверки наличия записи работника в таблице loginpass
                case "login":
                    String[] log = tmp[1].split("/");
                    
                    for(int i = 0; i < model.getRowCount(); i++)
                    {
                        if(model.getValueAt(i,0).equals(log[0]))
                        {
                            return "busy";
                        }
                    }
                    
                    String pass = dbConnect.Login(tmp[1]);                      //получение логина для проверки доступа к системе
                    if(!pass.equals("pass_null"))
                    {
                        cal = Calendar.getInstance(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru"));
                        synchronized(this)
                        {
                            java.awt.EventQueue.invokeLater(() -> {
                                System.out.println("login = " + log[0] + ", time = " + sdf.format(cal.getTime()) +", port = " + client.getPort());
                                model.addRow(new Object[]{log[0], sdf.format(cal.getTime()), client.getPort()});
                            });   
                        }
                        

                    }
                    return pass;                     
                case "createLogin":
                    return dbConnect.CreateNewLogin(tmp[1]);                    //создание логина и пароля для нового работника в таблице loginpass
                case "getLName":                                    
                    return dbConnect.GetLName(tmp[1]);                          //получение фамилии
                case "addPerson":
                    return dbConnect.AddPerson(tmp[1]);                         //внесение инфомации о новом работнике в базу данных
                case "updatePersonInfo":
                    return dbConnect.UpdatePersonInfo(tmp[1]);                  //изменение информации о работнике в базе данных
                 case "getPersonInfo":
                    return dbConnect.GetPersonInfo(tmp[1]);                     //получение информации о работнике из базы данных
                case "addSert":
                    return dbConnect.AddSert(tmp[1]);                           //внесение данных о сертификате докторов
                case "getListSert":
                    return dbConnect.GetListSertificat(tmp[1]);                 //получение из таблицы sertificat данных сертификатах выбранного доктора
                case "getSert":
                    return dbConnect.GetSertificate(tmp[1]);                    //получение данных о выбранном сертификате
                case "getDoctorList":
                    return dbConnect.GetDoctorList();                           //получение перечня специальностей докторов из базы данных
                case "getCategories":
                    return dbConnect.GetCategories();                           //получение перечня категорий из базы данных
                case "getRegions":
                    return dbConnect.GetRegions();                              //получение списка областей
                case "getTowns":
                    return dbConnect.GetTowns(tmp[1]);                      //получение списка городов
                case "addGrafic":
                    return dbConnect.AddGrafic(tmp[1]);                         //внесение графика работы в базу
                case "testGrafic":
                    return dbConnect.GetGrafic(tmp[1]);                         //проверка существует ли таблица графика работы в базе
                case "getGrafic":
                    return dbConnect.GetGrafic(tmp[1]);                         //получение графика работы специалиста на текущий месяц
                case "addKonsult":
                    return dbConnect.AddVisit(tmp[1]);                          //добавление времени консультации в базу данных
                case "addPatient":
                    return dbConnect.AddPatient(tmp[1]);                        //внесение информации о новом пациенте в базу данных
                case "updatePatientInfo":
                    return dbConnect.UpdatePatientInfo(tmp[1]);                 //изменение информации о пациенте в базе данных
                case "getPatientInfo":
                    return dbConnect.GetPatientInfo(tmp[1]);                    //получение информации о пациента из базы данных
                case "getLNamePatients":
                    return dbConnect.GetLNamePatients();                        //получение информации о пациента из базы данных
                case "getPatientId":
                    return dbConnect.GetPatientId(tmp[1]);                      //получение id пациента по его фамилии и году рождения
                case "getKonsultList":
                    return dbConnect.GetKonsultList(tmp[1]);                    //получение информации о всех консультациях выбранного пациента
                case "getKonsult":
                    return dbConnect.GetKonsult(tmp[1]);                        //получение информации о выбранной консультации выбранного пациента
                case "addNewVidTesting":
                    return dbConnect.AddNewVidObsled(tmp[1]);                   //добавление нового вида обследования в таблицу
                case "getVidObsled":
                    return dbConnect.GetVidObsledov(tmp[1]);                    //получение видов обследования по выбранному типу
                case "getDateResObsled" :
                    return dbConnect.GetDateResObsled(tmp[1]);                  //получение результатов обследования по id вида обследования и id пациента
                case "addResultObsled" :
                    return dbConnect.AddTextResultObsled(tmp[1]);               //внесение результатов обследования в таблицу resreserch
                case "getResObsled"  :
                    return dbConnect.GetTestResult(tmp[1]);                      //получение результатов обследования                
                case "saveResultOsmotra" :
                    return dbConnect.SaveResultOsmotra(tmp[1]);                 //сохранение в базе данных результатов осмотра на текущую дату
                case "getCompanyList" :
                    return dbConnect.GetCompanyList();                          //отправка клиенту списка предприятий
                case "addCompany" :
                    return dbConnect.AddCompany(tmp[1]);                        //добавление в базу нового предприятия
                case "modifyCompany" :
                    return dbConnect.UpdateCompanyInfo(tmp[1]);                 //изменение данных о предприятии
                case "getEmployeeList" :
                    return dbConnect.GetEmployeeList(tmp[1]);                   //отправка клиенту списка работников предприятия
             }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "null";
        }
        return "";
    }
}
