package umec.Priem;

import TCPConnect.TCPSender;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.net.ConnectException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PriemSearchPatients extends javax.swing.JDialog
{
    //объявление переменных
    private javax.swing.JPanel HistoryPanel;
    private javax.swing.JScrollPane LNameScrollPane;
    private javax.swing.JTable PatientInfoTable;
    private TCPSender sender;
    private String Doctor;
    
    //конструктор окна
    public PriemSearchPatients(java.awt.Frame parent, boolean modal, TCPSender sn, String login)
    {
        super(parent, modal);
        this.Doctor= login;
        this.sender = sn;
        initComponents();
    }
    
    //<editor-fold defaultstate="collapsed" desc="создание компонентов окна">
    private void initComponents()
    {
        setTitle("Поиск клиента");
        
        //определение размера окна
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        
        if(frameSize.height > screenSize.height)
        {
            frameSize.height = screenSize.height;
        }
        
        if(frameSize.width > screenSize.width)
        {
            frameSize.width = screenSize.width;
        }
        
         //установка положения окна
        this.setLocation((screenSize.width - frameSize.width) / 600, 
                (screenSize.height - frameSize.height) / 14);
        
        HistoryPanel = new javax.swing.JPanel();
        LNameScrollPane = new javax.swing.JScrollPane();
        PatientInfoTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        //HistoryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Поиск пациента"));

        PatientInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Фамилия клиента", "Дата рождения"}
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };
        });
        LNameScrollPane.setViewportView(PatientInfoTable);
        
        PatientInfoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PatientInfoTableMouseClicked(evt);
            }
        });
        
        if (PatientInfoTable.getColumnModel().getColumnCount() > 0) {
            PatientInfoTable.getColumnModel().getColumn(0).setResizable(false);
            PatientInfoTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            PatientInfoTable.getColumnModel().getColumn(1).setResizable(false);
            PatientInfoTable.getColumnModel().getColumn(1).setPreferredWidth(5);
        }

        javax.swing.GroupLayout HistoryPanelLayout = new javax.swing.GroupLayout(HistoryPanel);
        HistoryPanel.setLayout(HistoryPanelLayout);
        HistoryPanelLayout.setHorizontalGroup(
            HistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LNameScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );
        HistoryPanelLayout.setVerticalGroup(
            HistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LNameScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HistoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HistoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FillPatientsList();
        
        pack();
    }
    //</editor-fold>
    
    //заполнение списка фамилий пациентов
    private void FillPatientsList()
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            String[] patients = sender.SendTextToServer("getLNamePatients_").split(";");
            
            DefaultTableModel model = (DefaultTableModel) PatientInfoTable.getModel();
            
            for(String patient : patients)
            {
                String[] value = patient.split("-");
                model.addRow(new Object[]{value[0], value[1]});
            }
        }
        catch(ConnectException conExc)
        {
            JOptionPane.showMessageDialog(this, "Связь с сервером отсутствует", 
                    "Ошибка соединения", JOptionPane.ERROR_MESSAGE);
            System.out.println(conExc.getMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    //обработчик выбора строки в таблице с фамилиями пациента
    private void PatientInfoTableMouseClicked(MouseEvent evt)
    {
        String param = Doctor + "-" + PatientInfoTable.getValueAt(PatientInfoTable.getSelectedRow(), 0).toString()
                + "-" + PatientInfoTable.getValueAt(PatientInfoTable.getSelectedRow(), 1).toString();
        new PatientInfo(this, true, sender, param).setVisible(true);         
    }
}
