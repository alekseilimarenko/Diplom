package dataBase;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

class SqlConnectionString 
{
    public String strHost = "localhost";
    public String strPort = "3306";
    
    public String strDBName = "umec";
    public String strUser = "root";
    public String strPswd = "alex";
    
    public String strUseUnicode = "true";
    public String strCharEncoding = "utf8";
    
    @Override
    public String toString()
    {
        //"jdbc:mysql://localhost:3306/bdname?user=username&password=password&
        //                          useUnicode=true&characterEncoding=utf8";
        
        String S = "jdbc:mysql://" + strHost + ":" + strPort + "/" + strDBName +
                "?user=" + strUser + "&password=" + strPswd + "&userUnicode" + 
                strUseUnicode + "&characterEncoding" + strCharEncoding;
        
        return S;
    }
}

/**
 * Class MySQLAdapter
 * ---------------------------------------------------------------------
 */
public class MySQLAdapter
{
    // ----- Class members -------------------------------------------------
    private	Properties		prop			= new Properties();
            SqlConnectionString	ConnectionString	= new SqlConnectionString();
    private	boolean			isConnected		= false;
    private	Connection		con;

    // ----- Class methods -------------------------------------------------
    public void	init()
    {
        prop.put("user",		ConnectionString.strUser);
        prop.put("password",		ConnectionString.strPswd);
        prop.put("useUnicode",		ConnectionString.strUseUnicode);
        prop.put("characterEncoding",	ConnectionString.strCharEncoding);

    //	prop.put("autoReconnect",		"false");
    //	prop.put("maxReconnects",		"3");
    }

    public boolean isConnected()
    {
        return this.isConnected;
    }

    public synchronized void connect() throws SQLException
    {
    // ----- First, disconnecting, if connected ----------------------------
        if (isConnected)
        {
            try
            {
                con.close();
            }
            catch (SQLException se)
            {
                System.err.println("MySQLAdapter connect #1 SQLException : " + se.getMessage());
            }
            isConnected	= false;
        }

    // ----- Connect to DB Server ------------------------------------------
        int	nTryCount	= 5;
        while (true)
        {
            try
            {
                con = DriverManager.getConnection(ConnectionString.toString(), prop);
            }
            catch (SQLException se)
            {
                System.err.println("MySQLAdapter connect #2 SQLException : " + se.getMessage());

                nTryCount--;
                if (nTryCount == 0) return;

                try
                {
                    Thread.sleep(10000);
                }
                catch (InterruptedException ie) {}
                continue;
            }
            isConnected = true;
            break;
        }
    }

    public synchronized void disconnect()
    {
        if (isConnected == false) return;
        try
        {
            con.close();
        }
        catch (SQLException se)
        {
            System.err.println("MySQLAdapter disconnect SQLException : " + se.getMessage());
        }
        isConnected	= false;
    }

    //<editor-fold defaultstate="collapsed" desc="Method createTable">
    /*
    * Метод, исполняющий запрос CALL
    * @param query - Строка с запросом CALL
    */
    public synchronized boolean executeCall(String query)
    {
        if (isConnected == false) return false;
        ResultSet res = null;
        try
        {
            CallableStatement call = con.prepareCall(query);
            
            res = call.executeQuery();
            if(res != null)
                return true;
        }
        catch (SQLException se)
        {
            System.err.println("MySQLAdapter executeScalar SQLException #1 : " + se.getMessage());
            System.err.println(query);
            return false;
        }
        return false;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Method executeSelect">
    /**
     * Метод, исполняющий запрос SELECT
     * @param query	- Строка с запросом SELECT
     * @return	- Объект ResultSet с результирующей таблицей или null
     * в случае ошибки
     */
    public synchronized ResultSet executeSelect(String query, String param)
    {
        if (isConnected == false) return null;
        PreparedStatement stmt;
        ResultSet result = null;
        try
        {
            String[] tmp = param.split("-");
            int len = tmp.length;
            switch(len)
            {
                case 1:
                    if(tmp[0].equals("0"))
                    {
                        stmt = con.prepareStatement(query);
                        result = stmt.executeQuery();
                    }
                    else
                    {
                        stmt = con.prepareStatement(query);
                        stmt.setString(1, param);
                        result = stmt.executeQuery();
                    }
                    break;
                case 2:
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, tmp[0]);
                    stmt.setString(2, tmp[1]);
                    result = stmt.executeQuery();
                    break;
                case 3:
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, tmp[0]);
                    stmt.setString(2, tmp[1]);
                    stmt.setString(3, tmp[2]);
                    result = stmt.executeQuery();
                    break;
            }
        }
        catch (SQLException se)
        {
            System.err.println("MySQLAdapter executeSelect SQLException : " + se.getMessage());
            System.err.println(query);
        }
        return  result;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method executeQuery">
    /**
     * Метод, исполняющий запросы INSERT, UPDATE, DELETE
     * @param query	- Строка с запросом INSERT / DELETE / UPDATE
     * @return	- Результат исполнения запроса (true - успешно)
     */
    public synchronized boolean executeQuery(String query, String param)
    {
        if (this.isConnected == false) return false;
        
        PreparedStatement stmt;
        
        try
        {
            String[] strings = param.split("#");
            
            int len = strings.length;
            stmt = con.prepareStatement(query);
            for(int i = 0; i < strings.length; i++)
            {
                stmt.setString(i + 1, strings[i]);
            }
            stmt.executeUpdate();
        }
        catch (SQLException se)
        {
            System.err.println("MySQLAdapter executeQuery SQLException : "
                                                + se.getMessage());
            System.err.println(query);
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method executeScalar">
    /**
     * Метод, исполняющий запрос SELECT, результатом которого является одно значение
     * (результирующая таблица из одной строки и одного столбца)
     * @param query - Строка с запросом SELECT
     * @return      - Объект с результатом или null в случае ошибки
     */
    public synchronized Object executeScalar(String query, String param)
    {
        if (isConnected == false) return null;

        ResultSet result = null;

        String[] tmp = param.split("-");
        int len = tmp.length;
        
        if(len == 1)
        {
            try
            {
                if(tmp[0].equals("0"))
                {
                    PreparedStatement stmt = con.prepareStatement(query);
                    result = stmt.executeQuery();
                }
                else
                {
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, param);
                    result = stmt.executeQuery();
                }
                
            }
            catch (SQLException se)
            {
                System.err.println("MySQLAdapter executeScalar SQLException #1 : " + se.getMessage());
                System.err.println(query);
                return null;
            }
        }
        if(len == 2)
        {
            try
            {
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, tmp[0]);
                stmt.setString(2, tmp[1]);
                result = stmt.executeQuery();
            }
            catch (SQLException se)
            {
                System.err.println("MySQLAdapter executeScalar SQLException #1 : " + se.getMessage());
                System.err.println(query);
                return null;
            }
        }
        
        if(len == 3)
        {
            try
            {
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, tmp[0]);
                stmt.setString(2, tmp[1]);
                stmt.setString(3, tmp[2]);
                result = stmt.executeQuery();
            }
            catch (SQLException se)
            {
                System.err.println("MySQLAdapter executeScalar SQLException #1 : " + se.getMessage());
                System.err.println(query);
                return null;
            }
        }

        if (result != null)
        {
            try
            {
                if (result.next()) return result.getObject(1);
            }
            catch (SQLException se)
            {
                System.err.println("MySQLAdapter executeScalar SQLException #2 : " + se.getMessage());
                System.err.println(query);
                return null;
            }
        }
        return null;
    }
    //</editor-fold>
}

