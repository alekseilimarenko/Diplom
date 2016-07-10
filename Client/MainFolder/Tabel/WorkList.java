package umec.Tabel;

import javax.swing.*;
import TCPConnect.TCPSender;

public class WorkList extends JDialog
{
    TCPSender sender;
    
    public WorkList(JFrame parent, boolean modal, TCPSender sn)
    {
        super(parent, modal);
        this.sender = sn;
        initComponents();
    }
    
    private void initComponents()
    {
        
    }
}
