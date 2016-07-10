package dataBase;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyConnection 
{
    MySQLAdapter db;                                                            //адаптер для соединения с базой данных    
    
    //<editor-fold defaultstate="collapsed" desc="готовые методы">
    //создание соединения с базой данных
    private void CreateConnection() throws SQLException
    {
        db = new MySQLAdapter();
        
        db.init();
        db.connect();
    }
    
    //получение пароля из базыданных по логину
    public String GetPass(String str) throws SQLException
    {
        //переменная для получения пароля из базы данных
        String pass;
        
        //соединение с базой данных
        CreateConnection();
        
        //получение из базы данных пароля
        pass = db.executeScalar("SELECT password FROM loginpass WHERE login = ?", str).toString();
        
        if(pass.equals("null"))
        {
            pass = "pass_null";
        }
        
        db.disconnect();
        return pass;
    }
    
    //проверка присутствия логина в базе данных
    public String VerifyLogin(String str) throws SQLException
    {
        //переменная для получения пароля из базы данных
        String pass;
        
        //соединение с базой данных
        CreateConnection();
        
        try
        {
            pass = db.executeScalar("SELECT `password` FROM `loginpass` WHERE login = ?", str).toString();
        }
        catch(Exception ex)
        {
            pass = "null";
        }
        
        db.disconnect();
        return pass;
    }
    
    //получение из базы данных по логину и паролю группы доступа к главному окну
    public String Login(String str) throws SQLException
    {
        //переменная для получения пароля из базы данных
        String pass, res;
        
        //соединение с базой данных
        CreateConnection();
        
        String[] tmp = str.split("/");
        
        //получение из таблицы loginpass пароля
        pass = db.executeScalar("SELECT password FROM loginpass WHERE login = ?", tmp[0]).toString();
        
        //проверка на совпадение пароля полученного из таблицы loginpass и введенного пользователем
        if(pass.equals(tmp[1]))
        {    
            //получение из базы номера группы доступа
            res = db.executeScalar("SELECT group_id FROM personal WHERE lastName = ?", tmp[0]).toString();
        }
        else
        {
            return "pass_null";
        }
        db.disconnect();
        return res;
    }
    
    //получение списка профессий для заполнения комбобокса со списком профессий
    public String GetDoctorList() throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String doctorList = "";
        
        //получение из таблицы group id всех врачей
        String id = db.executeScalar("SELECT `id` FROM `group` WHERE `group_name` = ?", "Врачи").toString();
        
        //выполнение запроса к базе данных
        ResultSet res = db.executeSelect("SELECT `profession` FROM `personal` WHERE "
                + "group_id = ? ORDER BY `profession`", id);
        
        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    doctorList = doctorList + res.getString("profession") + "/";
                }
                res.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
        
        //закрытие соединения с базой данных
        db.disconnect();
        return doctorList;
    }
    
    //получение списка областей
    public String GetRegions() throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String regions = "";
        
        //выполнение запроса к базе данных
        ResultSet rg = db.executeSelect("SELECT `rName` FROM `region` ORDER BY `rName`", "0");
        
        if(rg != null)
        {
            try
            {
                while(rg.next())
                {
                    regions = regions + rg.getString("rName") + "/";
                }
                rg.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
         
        //закрытие соединения с базой данных
        db.disconnect();
        return regions;
    }
    
    //получение списка городов
     public String GetTowns(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String towns = "";
        
        //получение номера области
        String id = db.executeScalar("Select `id` FROM `region` WHERE `rName` = ?", str).toString();
        
        //выполнение запроса к базе данных
        ResultSet tn = db.executeSelect("SELECT `tName`, `phoneId` FROM `town` WHERE `regionId` = ? ORDER BY `tName`", id);
        
        if(tn != null)
        {
            try
            {
                while(tn.next())
                {
                    towns = towns + tn.getString("tName") + ";" + tn.getString("phoneId") + "/";
                }
                tn.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
         
        //закрытие соединения с базой данных
        db.disconnect();
        return towns;
    }
    
    //получение списка категорий
    public String GetCategories() throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String categories = "";
        
        //выполнение запроса к базе данных
        ResultSet cat = db.executeSelect("SELECT `group_name` FROM `group` ORDER BY `group_name`", "0");
        
        if(cat != null)
        {
            try
            {
                while(cat.next())
                {
                    categories = categories + cat.getString("group_name") + "/";
                }
                cat.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
         
        //закрытие соединения с базой данных
        db.disconnect();
        return categories;
    }
    
    //получение списка фамилий работников
    public String GetLName(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String LNameList = "";
        ResultSet res;
        
        switch(str)
        {
            //получение из таблицы personal фамилий всех работников
            case "All":
                res = db.executeSelect("SELECT `lastname`, `category` FROM `personal` ORDER BY `lastname`", "0");
                break;
            
            //получение из таблицы personal фамилий всех докторов
            case "Doc":
                String id = db.executeScalar("SELECT `id` FROM `group` WHERE `group_name` = ?", "Врачи").toString();

                res = db.executeSelect("SELECT `lastname`, `category` FROM `personal` WHERE"
                        + " `group_id` = ? ORDER BY `lastname`", id);
                break;
  
            //получение из таблицы персонал списка докторов состоящих в штате
            case "штат":
                String st = db.executeScalar("SELECT `id` FROM `group` WHERE `group_name` = ?", "Врачи").toString();

                res = db.executeSelect("SELECT `lastname`, `category` FROM `personal` WHERE"
                        + " `group_id` = ? AND `category` = ? ORDER BY `lastname`", st + "-штат");
                break;
            //получение из базы данных фамилий докторов по выбранной профессии
            default:
                res = db.executeSelect("SELECT `lastname`, `category` FROM `personal` WHERE "
                        + " profession = ? ORDER BY `lastname`", str);
        }
        
        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    LNameList = LNameList + res.getString("lastname") + "-" + res.getString("category") + "/";
                }
                res.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
        
        //закрытие соединения с базой данных
        db.disconnect();
        return LNameList;
    }
    
    //добавление в таблицу sertificate данных о сертификатах выбранного доктора
    public String AddSert(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String[] tmp = str.split("#");
        String answer, id;
        try
        {       
            //получение из таблицы personal, id выбранного доктора
            id = db.executeScalar("SELECT `id` FROM `sertificat` WHERE `spec` = ?", tmp[4]).toString();
        }
        catch(Exception e)
        {
            id = "null";
        }
        
        if(id.equals("null"))
        {
            String pers_id = db.executeScalar("SELECT `id` FROM `personal` WHERE `lastName` = ?", tmp[0]).toString();
            
            //добавление в таблицу sertificate данных о сертификате выбранного докторов
            if (db.executeQuery("INSERT INTO `sertificat`(`number`, `issueDate`,"
                    + " `expireDate`, `spec`, `personal_id`) VALUES (?, ?, ?, ?, ?)",
                    tmp[1] + "#" + tmp[2] + "#" + tmp[3] + "#" + tmp[4] + "#" + pers_id ))

            {   
                answer = "INS";
            }
            else
            {
                answer = "NO";
            }
        }
        else
        {
            if(db.executeQuery("UPDATE `umec`.`sertificat` SET `number` = ?, "
                    + "`issueDate` = ?, `expireDate` = ?, `spec` = ? WHERE `sertificat`.`id` = ?", 
                    tmp[1] + "#" + tmp[2] + "#" + tmp[3] + "#" + tmp[4] + "#" + id))
            {
                answer = "UPD";
            }
            else
            {
                answer = "NO";
            }
        }
        
        //закрытие соединения с базой данных
        db.disconnect();
        return answer;   
    }
    
    //получение из таблицы sertificate данных о всех сертификатах выбранного доктора
    public String GetListSertificat(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String listSert = "";
        
        String id = db.executeScalar("SELECT `id` FROM `personal` WHERE lastName = ?", str).toString();
        
        //выполнение запроса к таблице sertificat
        ResultSet res = db.executeSelect("SELECT `number`, `spec` FROM `sertificat` "
                + "WHERE `personal_id` = ? ORDER BY `number`", id);
        
        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    listSert = listSert + res.getString("number") + "/" 
                            + res.getString("spec") + ";";
                }
                res.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
        
        if(listSert.equals(""))
            listSert = "null";
         
        //закрытие соединения с базой данных
        db.disconnect();
        return listSert;
    }
    
    //получение данных из таблицы sertificat о сертификате выбранного доктора
    public String GetSertificate(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String sert = "";
        
        //выполнение запроса к таблице sertificat
        ResultSet res = db.executeSelect("SELECT `issueDate`, `expireDate` FROM `sertificat` "
                + "WHERE `number` = ?", str);
        
        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    sert = sert + res.getString("issueDate") + "#" +  
                            res.getString("expireDate");
                }
                res.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
         
        //закрытие соединения с базой данных
        db.disconnect();
        return sert;
    }
    
    //создание нового логина и пароля для работника
    public String CreateNewLogin(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        String[] tmp = str.split("/");
        String answer;
        
        if(db.executeQuery("INSERT INTO `loginpass`(`login`, `password`) "
                        + "VALUES (?, ?)", tmp[0] + "#" + tmp[1]))
        {
            //закрытие соединения с базой данных
            db.disconnect();
            answer = "OK";
        }
        else
        {
            //закрытие соединения с базой данных
            db.disconnect();
            answer = "NO";
        }
        
        //закрытие соединения с базой данных
        db.disconnect();
        return answer;
    }
    
    //добавление нового работника в таблицу personal
    public String AddPerson(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String login_id, group_id, que;
        String[] tmp = str.split("#");
        /*
        INSERT INTO `personal`(`lastName`, `firstName`, `surName`, `birthDay`, 
        `sex`, `address`, `phone`, `mobPhone`, `vuz`, `profession`, `category`, `salary`, `hireDate`, 
        `dissmissDate`, `group_id`, `login_id`) VALUES
        ('Лимаренко', 'Виктория', 'Валерьевна', '0000-00-00', 'Ж', 'Запорожье', 
        '12345', '0661983680', 'главврач', 1500, '0000-00-00', NULL, 1, 1)
         */
        if(tmp[14].equals("Санитарки"))
        {    
            try
            {
                //получение group_id группы из таблицы group 
                group_id = db.executeScalar("SELECT id FROM `group` WHERE group_name = ?", tmp[14]).toString();
            }
            catch(Exception ex)
            {
                group_id = "null";
                ex.printStackTrace();
            }
            
            if(group_id.equals("null"))
                    return("group_id");
            
            que = "INSERT INTO `personal`(`lastName`, `firstName`, `surName`,"
                    + " `birthDay`, `sex`, `address`, `phone`, `mobPhone`, `vuz`,"
                    + " `profession`, `category`, `salary`, `hireDate`, `dissmissDate`, "
                    + "`group_id`)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }
        else
        {
            try
            {
                 //получение group_id группы из таблицы group 
                group_id = db.executeScalar("SELECT id FROM `group` WHERE group_name = ?", tmp[14]).toString();
                
                //получение login_id работника из таблицы loginpass
                login_id = db.executeScalar("SELECT id FROM `loginpass` WHERE login = ?", tmp[0]).toString();
            }
            catch(Exception ex)
            {
                login_id = "null";
                group_id = "null";
                ex.printStackTrace();
            }
            
            if(login_id.equals("null"))
                return("login_id");
        
            if(group_id.equals("null"))
                return("group_id");
            
            que = "INSERT INTO `personal`(`lastName`, `firstName`, `surName`,"
                    + " `birthDay`, `sex`, `address`, `phone`, `mobPhone`, `vuz`,"
                    + " `profession`, `category`, `salary`, `hireDate`, `dissmissDate`, `group_id`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }
        
        System.out.println(que);
        
        if(db.executeQuery(que, tmp[0] + "#" + tmp[1] + "#" + 
                    tmp[2] + "#" + tmp[3] + "#" + tmp[4] + "#" + 
                    tmp[5] + "#" + tmp[6] + "#" + tmp[7] + "#" + 
                    tmp[8] + "#" + tmp[9] + "#" + tmp[10] + "#" + 
                    tmp[11] + "#" + tmp[12] + "#" + tmp[13] + "#" + group_id ))
            return "OK";
        else
            return "NO";
    }
    
    //получение данных о работнике из таблицы personal
    public String GetPersonInfo(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String personInfo = "";
        
        //получение из таблицы personal информации о выбранном работнике
        ResultSet res = db.executeSelect("SELECT * FROM `personal` WHERE `lastName` = ?", str);
         
        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    String group_id = db.executeScalar("SELECT `group_name` FROM `group` WHERE id = ?",
                            String.valueOf(res.getInt("group_id"))).toString();
                    
                    personInfo = personInfo + res.getInt("id") + "#" +
                                    res.getString("lastName") + "#" + 
                                    res.getString("firstName") + "#" +
                                    res.getString("surName") + "#" + 
                                    res.getString("birthDay") + "#" + 
                                    res.getString("sex") + "#" + 
                                    res.getString("address") + "#" + 
                                    res.getString("phone") + "#" + 
                                    res.getString("mobPhone") + "#" +
                                    res.getString("vuz") + "#" + 
                                    res.getString("profession")+ "#" +
                                    res.getString("category")+ "#" +
                                    res.getInt("salary") + "#" + 
                                    res.getString("hireDate") + "#" + 
                                    res.getString("dissmissDate") + "#" + 
                                    group_id;
                }
                
                res.close();
                
                //закрытие соединения с базой данных
                db.disconnect();
                return personInfo;
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
        
        //закрытие соединения с базой данных
        db.disconnect();
        return "null";
    }
    
    //внесение изменений в таблицу personal о выбранном работнике
    public String UpdatePersonInfo(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String[] tmp = str.split("#");
        
        String group_id = db.executeScalar("SELECT `id` FROM `group` WHERE `group_name`= ?", tmp[15]).toString();
        
        if(db.executeQuery("UPDATE `umec`.`personal`"
                + " SET `lastName` = ?, `firstName` = ?, `surName` = ?, `birthDay` = ?, `sex` = ?,"
                + "`address` = ?, `phone` = ?, `mobPhone` = ?, `vuz` = ?, `profession` = ?, `category` = ?, `salary` = ?, `hireDate` = ?, "
                + "`dissmissDate` = ?, `group_id` = ? WHERE `personal`.`id` = ?", tmp[1] + "#" + tmp[2] + "#" 
                + tmp[3] + "#" + tmp[4] + "#" + tmp[5] + "#" + tmp[6] + "#" + tmp[7] + "#" + tmp[8] + "#" 
                + tmp[9] + "#" + tmp[10] + "#" + tmp[11] + "#" + tmp[12] + "#" + tmp[13] + "#" + tmp[14] + "#" + group_id + "#" + tmp[0]))
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "OK";
        }
        else
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "NO";
        }
    }
      
    //добавление графика работы персонала в таблицу
    public String AddGrafic(String str) throws SQLException
    {
        String pers_id = "", workSchedule = "";
        
        //соединение с базой данных
        CreateConnection();
        
        String[] tmp = str.split("/");
/*
    INSERT INTO `workschedule`(`date_workSchedule`, `month_year`, `personal_id`)
            VALUES ([value-2],[value-4],[value-5])
        "addGrafic_" + DocName + "/" + grafic + "/"+ month.toString() + year;
*/
        try
        {
            pers_id = db.executeScalar("SELECT `id` FROM `personal` WHERE `lastName` = ?", tmp[0]).toString();
           
            workSchedule = db.executeScalar("SELECT `date_workSchedule` FROM "
                    + "`workschedule` WHERE `month_year` = ? AND `personal_id` = ?", tmp[2] + "-" + pers_id).toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            workSchedule = "null";
        }
        
        if(workSchedule.equals("null"))
        {
            if(db.executeQuery("INSERT INTO `workschedule`(`date_workSchedule`,"
                    + " `month_year`, `personal_id`) VALUES (?, ?, ?)", tmp[1] + "#" + tmp[2] + "#" + pers_id))
            {
                //закрытие соединения с базой данных
                db.disconnect();
                return "INS";
            }
            else
            {
                //закрытие соединения с базой данных
                db.disconnect();
                return "NOINS";
            }
        }
        else
        {
            //"addKonsult_" + DocLName + "#" + m_y.format(date) + "#" + priem
            if(db.executeQuery("UPDATE `umec`.`workschedule` SET `date_workSchedule` = ? "
                    + "WHERE `workschedule`.`month_year` = ? AND `workschedule`.`personal_id` = ?",
                    tmp[1] + "#" + tmp[2] + "#" + pers_id))
            {
                //закрытие соединения с базой данных
                db.disconnect();
                return "UPD";
            }
            else
            {
                //закрытие соединения с базой данных
                db.disconnect();
                return "NOUPD";
            }
        }
    }
    
    //получение графика работы выбранного специалиста
    public String GetGrafic(String str) throws SQLException
    {
        //"getGrafic_" + DocLName + m_y.format(date)
        String[] tmp = str.split("%");
        
        //соединение с базой данных
        CreateConnection();
        
        String GraficList = "";
        
        //получение из базы id доктора
        String id = db.executeScalar("SELECT `id` FROM `personal` WHERE `lastname` = ?", tmp[0]).toString();
        
        String phone = db.executeScalar("SELECT `phone` FROM `personal` WHERE `lastname` = ?", tmp[0]).toString();
        
        //получение из базы данных графика приема доктора
        ResultSet res = db.executeSelect("SELECT `date_workSchedule`, `date_priemTime_LName` "
                + "FROM `workschedule` WHERE `month_year` = ? AND `personal_id` = ?", tmp[1] + "-" + id);
        
        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    GraficList = GraficList + res.getString("date_workSchedule") + "%" +
                                           res.getString("date_priemTime_LName");
                }
                res.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
                return "null";
            }
        }
        
        if(GraficList.equals(""))
            GraficList = "null%null";

        //закрытие соединения с базой данных
        db.disconnect();
        return GraficList + "%" + phone;
    }
    
    //добавление нового пацинета в таблицу patients
    public String AddPatient(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String que;
        String[] tmp = str.split(",");
        /*
            INSERT INTO `patients`(`lastName`, `firstName`, `surName`, `birthDay`,
            `pol`, `address`, `work`, `profession`, `phone`, `insuranceCompany`, 
            `numberInsurancePolice`) 
            VALUES ([value-2],[value-3],[value-4],[value-5],[value-6],
            [value-7],[value-8],[value-9],[value-10],[value-11],[value-12])
        */
        que = "INSERT INTO `patients`(`lastName`, `firstName`, `surName`, "
                + "`birthDay`, `pol`, `address`, `work`, `profession`, `phone`, "
                + "`insuranceCompany`, `numberInsurancePolice`)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        System.out.println(que);
        
        if(db.executeQuery(que, tmp[0] + "#" + tmp[1] + "#" + tmp[2] 
                + "#" + tmp[3] + "#" + tmp[4] + "#" + tmp[5] + "#" + tmp[6] 
                + "#" + tmp[7] + "#" + tmp[8] + "#" + tmp[9] + "#" + tmp[10]))
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "OK";
        }
        else
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "NO";
        }
    }
        
    //получение списка фамилий пациентов
    public String GetLNamePatients() throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String LNamePatientsList = "";
        
        //получение из таблицы personal фамилий всех пациентов
        ResultSet res = db.executeSelect("SELECT `lastname`, `birthDay` FROM `patients` ORDER BY `lastname`", "0");
        
        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    LNamePatientsList = LNamePatientsList + 
                            res.getString("lastname") + "-" +
                            res.getString("birthDay")+ ";";
                }
                res.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
        
        //закрытие соединения с базой данных
        db.disconnect();
        return LNamePatientsList;
    }
    
    //получение данных о пациенте из таблицы patients
    public String GetPatientInfo(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String patientInfo = "";
        
        //получение из таблицы patients информации о выбранном пациенте
        ResultSet res = db.executeSelect("SELECT * FROM `patients` WHERE "
                + "`lastName` = ? AND `birthDay` = ?", str);
         
        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    patientInfo = patientInfo + res.getInt("id") + "," +            //0
                                        res.getString("lastName") + "," +           //1
                                        res.getString("firstName") + "," +          //2
                                        res.getString("surName") + "," +            //3
                                        res.getString("birthDay") + "," +           //4
                                        res.getString("pol") + "," +                //5
                                        res.getString("address") + "," +            //6
                                        res.getString("work") + "," +               //7
                                        res.getString("profession")+ "," +          //8
                                        res.getString("phone") + "," +              //9
                                        res.getString("insuranceCompany") + "," +   //10
                                        res.getString("numberInsurancePolice");     //11
                }
                res.close();
                
                //закрытие соединения с базой данных
                db.disconnect();
                return patientInfo;
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
        //закрытие соединения с базой данных
        db.disconnect();
        return "null";
    }
    
    //внесение изменений в таблицу patients о выбранном работнике
    public String UpdatePatientInfo(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String[] tmp = str.split(",");
        
        String que = "UPDATE `umec`.`patients` SET `lastName` = ?,`firstName` = ?,"
                                    + "`surName` = ?, `birthDay` = ?, `pol` = ?,"
                                    + "`address` = ?, `work` = ?, `profession` = ?,"
                                    + "`phone` = ?, `insuranceCompany` = ?,"
                                    + "`numberInsurancePolice` = ? WHERE `patients`.`id` = ?";
        
        if(db.executeQuery(que, tmp[1] + "#" + tmp[2] + "#" + tmp[3] + "#" + tmp[4] + "#" +
                tmp[5] + "#" + tmp[6] + "#" + tmp[7] + "#" + tmp[8] + "#" + tmp[9] + "#" + tmp[10] + "#" + tmp[11] + "#" + tmp[0]))
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "OK";
        }
        else
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "NO";
        }
    }
    
    //добавление медицинской карты в базу дынных
    public String AddHealthCard(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String que;
        /*
            UPDATE `umec`.`patients` SET `medKarta`= ? WHERE `lastName` = ? AND `birthDay` = ?
        Patients\rd_Сидоров-18_12_1988/healthCard_Сидоров-18_12_1988.mp3
        */
        que = "UPDATE `umec`.`patients` SET `medKarta`= ? WHERE `lastName` = ? AND `birthDay` = ?";
        
        System.out.println(que);
        String lName = str.substring(9, str.indexOf("-"));
        String bDay = str.substring(str.indexOf("-") + 1, str.indexOf("/")).replace('_', '/');
        if(db.executeQuery(que, str + "#" + lName + "#" + bDay ))
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "OK";
        }
        else
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "NO";
        }
    }
    
    //отправка амбулаторной карты клиенту
    public String GetHealthCard(String str) throws SQLException
    {
        //переменная для получения пароля из базы данных
        String path;
        
        //соединение с базой данных
        CreateConnection();
        
        String[] tmp = str.split("-");
        
        //получение из базы данных путь к медкарте пациента
        try
        {
            path = db.executeScalar("SELECT `medKarta` FROM `patients` WHERE `lastName` = ? AND `birthDay` = ?", str).toString();
        }
        catch(Exception e)
        {
            path = "null";
        }

        db.disconnect();
        return path;
    }
    
    //отправка результатов обследования клиенту
    public String GetTestResult(String str) throws SQLException
    {
        //переменная для получения пароля из базы данных
        String answer = "";
        
        String[] tmp = str.split("-");
        
        String p_id = GetPatientId(tmp[0] + "-" + tmp[1]);
        
        //соединение с базой данных
        CreateConnection();
        
        //полчение из базы номера для вида обследования
        String vid_id = db.executeScalar("SELECT `id` FROM `vidreserch` WHERE `vid_name` = ? ", tmp[3]).toString();
        
        //получение из базы данных результатов обследования
        answer = db.executeScalar("SELECT `result` FROM `resreserch` WHERE `date_reserch` = ? AND `id_patient` = ? AND `id_vid` = ?", 
                tmp[2] + "-" + p_id + "-" + vid_id).toString();
        
        db.disconnect();
        return answer;
    }
            
    //изменение графика према пациентов
    public String AddVisit(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String[] tmp = str.split("#");
        
        //получение из базы id доктора
        String pers_id = db.executeScalar("SELECT id FROM personal WHERE lastname = ?", tmp[0]).toString();
        
    String que = "UPDATE `umec`.`workschedule` SET `date_priemTime_LName` = ? WHERE `workschedule`.`month_year` = ? "
            + "AND `workschedule`.`personal_id` = ?";
        
       if(db.executeQuery(que, tmp[2] + "#" + tmp[1] + "#" + pers_id))
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "OK";
        }
        else
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "NO";
        }
    }
        
    //получение id пациента из таблицы patient
    public String GetPatientId(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        String id;
        
        try
        {
            //получение из таблицы patients id выбранного пациента
            id = db.executeScalar("SELECT `id` FROM `patients` WHERE `lastName` = ? AND `birthDay` = ?",
                str).toString();
        }
        catch(Exception e)
        {
            id = "null";
        }
        
        //закрытие соединения с базой данных
        db.disconnect();
        return id;
    }
        
    //получение списка консультаций выбранного пациента
    public String GetKonsultList(String str) throws SQLException
    {
        String[] tmp = str.split("-");
        
        //получение из таблицы patients id выбранного пациента
        String id = GetPatientId(str);
        
        //соединение с базой данных
        CreateConnection();
        
        String info = "";
        
        ResultSet data = db.executeSelect("SELECT `firstName`,`surName`, `insuranceCompany` "
                + "FROM `patients` WHERE `lastName` = ? AND `birthDay` = ?", str);
        
        if(data != null)
        {
            try
            {
                while(data.next())
                {
                    info = info + data.getString("firstName") + ":" 
                            + data.getString("surName") + ":"
                            + data.getString("insuranceCompany");
                }
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
        String konsult = "";
        
        ResultSet res = db.executeSelect("SELECT `doctor_id`, `date` FROM `konsultacii` WHERE `patient_id` = ?", id);

        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    konsult = konsult + db.executeScalar("SELECT `lastName` FROM `personal` WHERE `id` = ?", res.getString("doctor_id"))
                            + "-" + res.getString("date")  + ";";  
                }
                res.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }

        if(konsult.equals(""))
            konsult = "null";

        //закрытие соединения с базой данных
        db.disconnect();
        return info + "#" + konsult;
    }
    
    //получение данных о выбранной консультации выбранного пациента
    public String GetKonsult(String str) throws SQLException
    {
        String[] tmp = str.split("-");
        
        //получение из таблицы patients id выбранного пациента
        String id = GetPatientId(tmp[0] + "-" +tmp[1]);
        
        //соединение с базой данных
        CreateConnection();
        
        String konsult = "";
        
        ResultSet res = db.executeSelect("SELECT `walobi`, `objective`, `diagnose`, `treatment` "
                + "FROM `konsultacii` WHERE `patient_id` = ? AND `date` = ? AND `doctor_id` = ?", id + "-" + tmp[3] + "-"
                + db.executeScalar("SELECT `id` FROM `personal` WHERE `lastName` = ?", tmp[2]));

        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    konsult = konsult + res.getString("walobi") + "#" +
                            res.getString("objective") + "#" + 
                            res.getString("diagnose")  + "#" + 
                            res.getString("treatment");  
                }
                res.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }

        //закрытие соединения с базой данных
        db.disconnect();
        return konsult;
    }
    
    //добавление нового типа обследования в базу
    public String AddNewVidObsled(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String[] tmp = str.split("#");
        
        String que = "INSERT INTO `vidreserch`(`vid_name`, `id_type`) VALUES (?, ?)";

        if(db.executeQuery(que, tmp[0] + "#" + db.executeScalar("SELECT `id` FROM `typereserch` WHERE `type` = ?", tmp[1]).toString()))
        {
            return "OK";
        }
        else
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "null";
        }
    }
    
    //получение списка видов обследования
    public String GetVidObsledov(String str) throws SQLException
    {
        //соединение с базой данных
        CreateConnection();
        
        String konsult = "";
        //SELECT * FROM users WHERE userid=? AND password=?

        ResultSet res = db.executeSelect("SELECT `vid_name` FROM `vidreserch` "
                + "WHERE `id_type` = ? ORDER BY `vid_name`", db.executeScalar("SELECT `id` FROM `typereserch` WHERE `type` = ?", str ).toString());

        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    konsult = konsult + res.getString("vid_name") + "-";
                }
                res.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }

        //закрытие соединения с базой данных
        db.disconnect();
        return konsult;
    }
    
    //получение списка результатов анализов по выбранному виду обследования
    public String GetDateResObsled(String str) throws SQLException
    {
        String[] tmp = str.split("-");
        
        //получение из таблицы patients id выбранного пациента
        String id = GetPatientId(tmp[0] + "-" + tmp[1]);
        
        //соединение с базой данных
        CreateConnection();
        
        String resReserch = "";
        
        ResultSet res = db.executeSelect("SELECT `date_reserch` FROM `resreserch` "
                + "WHERE `id_patient` = ? AND `id_vid` = ? ORDER BY `date_reserch`", id + "-" 
                        + db.executeScalar("SELECT `id` FROM `vidreserch` WHERE `vid_name` = ?", tmp[2]));

        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    resReserch = resReserch + res.getString("date_reserch") + "-";
                }
                res.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
        if(resReserch.equals(""))
            resReserch = "null";

        //закрытие соединения с базой данных
        db.disconnect();
        return resReserch;
    }
    //</editor-fold>
    
    //внесение результатов обследования в текстовом виде
    public String AddTextResultObsled(String str) throws SQLException
    {
        String[]tmp = str.split("#");
        
        //получение из таблицы patients id выбранного пациента
        String patient_id = GetPatientId(tmp[2]);
        
        //соединение с базой данных
        CreateConnection();
        
        //получение из таблицы vidreserch id вида обследований
        String vid_id = db.executeScalar("SELECT `id` FROM `vidreserch` WHERE `vid_name` = ?", tmp[0]).toString();
        
        //INSERT INTO `resreserch`(`date_reserch`, `result`, `id_patient`, `id_vid`)
        //                      VALUES ([value-2],[value-3],[value-4],[value-5])
        
        String que = "INSERT INTO `resreserch`(`date_reserch`, `result`, `id_patient`, `id_vid`) VALUES (?, ?, ?, ?)";

        if(db.executeQuery(que, tmp[1].replace('-', '/') + "#" + "text:" + tmp[3] + "#" + patient_id + "#" + vid_id))
        {
            return "OK";
        }
        else
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "null";
        }
    }
    
     //внесение результатов обследования в виде файла
    public String AddFileResultObsled(String str, String path) throws SQLException
    {
        String[]tmp = str.split("/");
        
        //получение из таблицы patients id выбранного пациента
        String patient_id = GetPatientId(str.substring(str.indexOf("/") + 1, str.indexOf(".")));
        
        //соединение с базой данных
        CreateConnection();
        
        //получение из таблицы vidreserch id вида обследований
        String vid_id = db.executeScalar("SELECT `id` FROM `vidreserch` WHERE `vid_name` = ?", tmp[0]).toString();
        
        //INSERT INTO `resreserch`(`date_reserch`, `result`, `id_patient`, `id_vid`)
        //                      VALUES ([value-2],[value-3],[value-4],[value-5])
        
        String que = "INSERT INTO `resreserch`(`date_reserch`, `result`, `id_patient`, `id_vid`) VALUES (?, ?, ?, ?)";

        if(db.executeQuery(que, tmp[4].replace('-', '/') + "#" + "file:" + path + "#" + patient_id + "#" + vid_id))
        {
            return "OK";
        }
        else
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "null";
        }
    }
    
    //сохранение в таблице konsultacii результатов осмотра на текущую дату
    public String SaveResultOsmotra(String str) throws SQLException
    {
        String[] tmp = str.split("#");
        
        //получение из таблицы patients id пациента
        String patient_id = GetPatientId(tmp[5]);
        
        //соединение с базой данных
        CreateConnection();
        
        //получение из таблицы personal id доктора
        String doc_id = db.executeScalar("SELECT `id` FROM `personal` WHERE "
                + "`lastName` = ?", tmp[6]).toString();
        
        //INSERT INTO `konsultacii`(`date`, `walobi`, `objective`, `diagnose`, 
        //                              `treatment`, `patient_id`, `doctor_id`) 
        //          VALUES ([value-2],[value-3],[value-4],[value-5],[value-6],
        //                  [value-7],[value-8])
        
        String que = "INSERT INTO `konsultacii`(`date`, `walobi`, `objective`, "
                + "`diagnose`, `treatment`, `patient_id`, `doctor_id`)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        if(db.executeQuery(que, tmp[0] + "#" + tmp[1] + "#" + tmp[2] + "#" 
                + tmp[3] + "#" + tmp[4] + "#" + patient_id + "#" + doc_id))
        {
            return "OK";
        }
        else
        {
            //закрытие соединения с базой данных
            db.disconnect();
            return "null";
        }
    }
    
    //отправка клиенту списка предприятий
    public String GetCompanyList() throws SQLException
    {
        //переменная для получения пароля из базы данных
        String company = "";
        
        //соединение с базой данных
        CreateConnection();
        
        //получение из базы списка компаний
        ResultSet res = db.executeSelect("SELECT `company_name`, `company_address`, `phone` FROM `company` ORDER BY `company_name`", "0");
        
        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    company = company + res.getString("company_name") + "-" + res.getString("company_address") + "-" + res.getString("phone") + ";";
                }
                res.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
        else
        {
            company = "null";
        }
        //закрытие соединения с базой данных
        db.disconnect();
        return company;
    }
    
    //добавление в базу предприятия
    public String AddCompany(String str) throws SQLException
    {
        String[] tmp = str.split("#");
        
        //переменная для получения пароля из базы данных
        String answer;
        
        //соединение с базой данных
        CreateConnection();
        
        try
        {
            //проверка на приутствие компании в базе данных
            answer = db.executeScalar("SELECT `phone` FROM `company` WHERE `company_name` = ?", tmp[0]).toString();
        }
        catch(Exception e)
        {
            answer = "null";
        }
        
        if(answer.equals("null"))
        {
            String que = "INSERT INTO `company`(`company_name`, `company_address`, `phone`) VALUES (?, ?, ?)";

            if(db.executeQuery(que, tmp[0] + "#" + tmp[1] + "#" + tmp[2]))
            {
                answer = "OK";
            }
            else
            {
                answer = "null";
            }
        }
        else
            answer = "exist";
        
        db.disconnect();
        return answer;
    }
    
    //внесение изменений в таблицу patients о выбранном работнике
    public String UpdateCompanyInfo(String str) throws SQLException
    {
        String answer;
        
        //соединение с базой данных
        CreateConnection();
        
        String[] tmp = str.split("#");
        
        String que = "UPDATE `umec`.`company` SET `company_address` = ?,"
                                + "`phone` = ? WHERE `company`.`company_name` = ?";

        if(db.executeQuery(que, tmp[1] + "#" + tmp[2] + "#" + tmp[0]))
        {
            answer = "OK";
        }
        else
        {
            answer = "NO";
        }
        
        //закрытие соединения с базой данных
        db.disconnect();
        return answer;
    }
    
    //отправка клиенту списка работников предприятия
    public String GetEmployeeList(String str) throws SQLException
    {
        String emplList = "";
        
        //соединение с базой данных
        CreateConnection();
        
        String company_id = db.executeScalar("SELECT `id` FROM `company` WHERE `company_name` = ?", str).toString();
        
        //получение из базы списка компаний
        ResultSet res = db.executeSelect("SELECT `fio`, `birthDay` FROM `employee` WHERE `company_id` = ? ORDER BY `fio`", company_id);
        
        if(res != null)
        {
            try
            {
                while(res.next())
                {
                    emplList = emplList + res.getString("fio") + "-" + res.getString("birthDay") + ";";
                }
                res.close();
            }
            catch(SQLException se)
            {
                System.out.println(se.getMessage());
            }
        }
        
        if(res == null || emplList.equals(""))
        {
            emplList = "null";
        }
        //закрытие соединения с базой данных
        db.disconnect();
        return emplList;
    }
}
