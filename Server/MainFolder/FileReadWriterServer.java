package server;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FileReadWriterServer extends Thread
{
    private final Socket client;
    private String comand;
    String[] message;
    DataInputStream input;
    
    public FileReadWriterServer(Socket host)
    {
        this.setDaemon(true);
        this.client = host;
    }
    
    //прием/отправка файлов
    @Override
    public void run()
    {
        try
        {
            input = new DataInputStream(client.getInputStream());
            message = comand.split("|");
            if(message[0].equals("receiveFile"))
                ReceiveFile(message[1]);
            if(message[0].equals("sendFile"))
                SendFile(message[1]);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    //получение файлов от клиента и сохранение их на диск
    private void ReceiveFile(String fName)
    {
        try
        {
            File file = new File("/Patients/" + fName);
            long fSize = input.readLong();
            if(!file.exists())
            {
                file.mkdir();
            }
            byte[] buffer = new byte[32768];
            FileOutputStream outputFile = 
                    new FileOutputStream("/Patients/healthFile_" + fName);
            int count, total = 0;
            while((count = input.read(buffer)) != -1)
            {
                total += count;
                outputFile.write(buffer, 0, count);
                if(total == fSize)
                    break;
            }
            outputFile.flush();
            outputFile.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    //передача файлов клиенту
    private void SendFile(String fName) 
    {
        
    }
}
