package server;

import java.net.Socket;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.table.DefaultTableModel;

public class VerifyConnect extends Thread
{
    private UserList users;
    private DefaultTableModel model;
    
    public VerifyConnect(UserList user, DefaultTableModel mod)
    {
        this.users = user;
        this.model = mod;
        setDaemon(true);        
    }
    
    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                if(!users.IsEmpty())
                {
                    long time = Calendar.getInstance(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru")).getTimeInMillis();

                    Iterator<Socket> keySetIterator = users.KeySet();

                    while(keySetIterator.hasNext())
                    {
                      Socket key = keySetIterator.next();
                      System.out.println("key: " + key + " value: " + users.GetValue(key));

                        if((time - users.GetValue(key)) > 40000)
                        {
                            synchronized(this)
                            {
                                for(int i = 0; i < model.getRowCount(); i++)
                                {
                                    if(model.getValueAt(i, 2).equals(key))
                                        model.removeRow(i);
                                }
                                users.Remove(key);
                                users.Notify();
                            }
                        }
                    }
                }   
                Thread.sleep(20000);
            }
        }
        catch(InterruptedException ire)
        {
            ire.printStackTrace();
        }
    }
}
