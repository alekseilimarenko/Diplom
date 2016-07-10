package server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class UserList extends HashMap
{
    private HashMap<Socket, Long> userList = new HashMap<>(); 

    public synchronized void Wait() throws InterruptedException
    {
        this.wait();
    }
    
    public synchronized void Notify()
    {
        this.notifyAll();
    }
    
    public void Add(Socket sc, Long lg)
    {
        this.userList.put(sc, lg);
    }
    
    public Socket GetKey(Socket sc)
    {
        if(userList.containsKey(sc))
        {
            return sc;
        }
        return null;
    }
    
    public Long GetValue(Socket sc)
    {
        return this.userList.get(sc);
    }
    
    public void Clear()
    {
        this.userList.clear();
    }
    
    public void Remove(Socket sc)
    {
        this.userList.remove(sc);
    }
    
    public int GetSize()
    {
        return this.userList.size();
    }
    
    public boolean IsEmpty()
    {
        return this.userList.isEmpty();
    }
    
    public void Replace(Socket sc, Long nV)
    {
        this.userList.replace(sc, nV);
    }
    
    public Iterator<Socket> KeySet()
    {
         return userList.keySet().iterator();
    }
}
