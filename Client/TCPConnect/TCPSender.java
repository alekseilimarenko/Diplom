package TCPConnect;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPSender 
{
    Socket Txtclient;
    
    //попытка соединения с сервером
    public boolean TryConnent() throws Exception
    {
        PropertyReader.InitComponents();
        
        String address = PropertyReader.GetAdress();
        int port = PropertyReader.GetPort();
        
        Txtclient = new Socket(address, port);
                
        if(Txtclient != null)
        {
            System.out.println("Соединено с:" + Txtclient.getInetAddress().toString()
                                                    + ":" + Txtclient.getPort());
            return true;
        }
        return false;
    }
    
    //отправка сообщения на сервер об отключении клиента
    public void AppExit(String str)
    {
        try
        {
            if (str.compareTo("") == 0) return; 

            //line.interrupt();
            synchronized(this)
            {
                Txtclient.getOutputStream().write(str.getBytes("UTF8"), 0, str.getBytes("UTF8").length);            
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //отправка на сервер текстовых сообщений
    public String SendTextToServer(String str)
    {
        String answer = "";
        try
        {
            ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
            byte[] b = new byte[4];

            System.out.println("Отправлено клиентом : " + str);

            if (str.compareTo("") == 0) return ""; 

            synchronized(this)
            {
                Txtclient.getOutputStream().write(str.getBytes("UTF8"), 0, str.getBytes("UTF8").length);
                BAOS.reset();
                do{
                    int cnt = Txtclient.getInputStream().read(b, 0 , b.length);
                    if(cnt == -1) throw new IOException("-1 bytes received");
                    BAOS.write(b, 0, cnt);
                }while(Txtclient.getInputStream().available() > 0);
            }

            System.out.println("Получено клиентом : " + new String (BAOS.toByteArray(), 0, BAOS.toByteArray().length, "UTF8"));
            answer = new String (BAOS.toByteArray(), 0, BAOS.toByteArray().length, "UTF8");
        }
        catch(IOException ioe)
        {
            System.out.println("IOException #1 : " + ioe.getMessage());
        }
        return answer;
    }
    
    //отправка на сервер файла
    public String SendFileToServer(String str) throws IOException
    {
        try
        {
            ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
            byte[] b = new byte[4];

                System.out.println("Отправлено клиентом : " + str);
                
                if (str.compareTo("") == 0) return ""; 
                
                String[] tmp = str.split("_");
                File file = new File(tmp[2]);
                String msg = tmp[0] + "_" + tmp[1] + "_" + file.length();
                
                synchronized(this)
                {
                    Txtclient.getOutputStream().write(msg.getBytes("UTF8"), 0, msg.getBytes("UTF8").length);                    
                
                    byte[] buffer = new byte[32768];
                    int count;
                    FileInputStream inputFile = new FileInputStream(file);
                    while((count = inputFile.read(buffer)) != -1)
                    {
                        Txtclient.getOutputStream().write(buffer, 0, count);
                    }
                
                    BAOS.reset();
                    do{
                        int cnt = Txtclient.getInputStream().read(b, 0 , b.length);
                        if(cnt == -1) throw new IOException("-1 bytes received");
                        BAOS.write(b, 0, cnt);
                    }while(Txtclient.getInputStream().available() > 0);
                }
                
                System.out.println("Получено клиентом : " + new String (BAOS.toByteArray(), 0, BAOS.toByteArray().length, "UTF8"));
                return new String (BAOS.toByteArray(), 0, BAOS.toByteArray().length, "UTF8");
        }
        catch(IOException ioe)
        {
            System.out.println("IOException #1 : " + ioe.getMessage());
        }
        return "";
    }
    
    //получение данных от сервера
    public String ReceiveDataFromServer(String str)
    {
        File file;
        File folder;
        String answer = "";
        String[] tmp = null;
        
        try
        {
            ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
            byte[] b = new byte[4];

                System.out.println("Отправлено клиентом : " + str);

                if (str.compareTo("") == 0) 
                {
                    str = "";
                    return null;
                } 

                synchronized(this)
                {
                    Txtclient.getOutputStream().write(str.getBytes("UTF8"), 0, str.getBytes("UTF8").length);                    
                    BAOS.reset();
                    do{
                        int cnt = Txtclient.getInputStream().read(b, 0 , b.length);
                        if(cnt == -1) throw new IOException("-1 bytes received");
                        BAOS.write(b, 0, cnt);
                    }while(Txtclient.getInputStream().available() > 0);
                }

                System.out.println("Получено клиентом : " + new String (BAOS.toByteArray(), 0, BAOS.toByteArray().length, "UTF8"));
                tmp = new String(BAOS.toByteArray(), 0, BAOS.toByteArray().length, "UTF8").split(":");
                
                switch(tmp[0])
                {
                    case "no_med":
                        answer = "no_med";
                        break;
                    case "text":
                        answer = tmp[0] + ":" + tmp[1];
                        break;
                    case "test":
                    case "health":
                        long fSize = Long.valueOf(tmp[1]);
                        DataInputStream input = new DataInputStream(Txtclient.getInputStream());
                        String folName = "Patients/" + tmp[2].substring(0, tmp[2].indexOf('/'));
                        folder = new File(folName);

                        if(!folder.exists()) folder.mkdirs();
                        file = new File("Patients/" + tmp[2]);

                        if(!file.exists())
                        {
                            file.createNewFile();
                        }

                        byte[] buffer = new byte[32768];

                        try (FileOutputStream outputFile = new FileOutputStream("Patients/" + tmp[2])) 
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
                        
                        answer = "file:" + "Patients/" + tmp[2];
                        break;
                }
        }
        catch(IOException ioe)
        {
            answer = "file:" + "Patients/" + tmp[2];
            System.out.println("IOException #1 : " + ioe.getMessage());
        }
        return answer;
    }
}
