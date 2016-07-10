package TCPConnect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader
{
    private static Properties prop;
    private static String[] conf;
    
    public static void InitComponents() throws IOException
    {
        prop = new Properties();
        
        prop.load(new FileInputStream(new File("Connect.ini")));
        
        conf = prop.getProperty("Connect").split(";");
    }
    
    public static String GetAdress()
    {
        return conf[0];
    }
    
    public static int GetPort()
    {
        return Integer.parseInt(conf[1]);
    }
}
