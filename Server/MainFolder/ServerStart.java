package server;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class ServerStart extends Thread
{
    private JLabel label;
    private DefaultTableModel model;
    private ServerSocket server;
    
    public ServerStart(JLabel lb, DefaultTableModel mod, ServerSocket srv)
    {
        this.label = lb;
        this.model = mod;
        this.server = srv;
    }
    
    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                label.setText("Ожидаем соединение");
                Socket txtClient = server.accept();
                System.out.println("Установлено соединение с : " + 
                        txtClient.getInetAddress().toString() + ":" + 
                        txtClient.getPort());
                TxtReadWriter TRW = new TxtReadWriter(txtClient, model);
                TRW.start();  
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
    }
}
