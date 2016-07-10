package server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class MainForm extends javax.swing.JFrame
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private javax.swing.JLabel Label;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton StartButton;
    private javax.swing.JLabel StateLabel;
    private javax.swing.JButton StopButton;
    private javax.swing.JScrollPane TableScrollPane;
    private javax.swing.JTable ConnectedTable;
    private javax.swing.table.DefaultTableModel model;
    private ServerStart sStart;
    private VerifyConnect vc;
    //private UserList users;
    private ServerSocket server;
//</editor-fold>
    
    public MainForm() 
    {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="создание элементов окна">                          
    private void initComponents() 
    {
        MainPanel = new javax.swing.JPanel();
        StartButton = new javax.swing.JButton();
        StopButton = new javax.swing.JButton();
        StateLabel = new javax.swing.JLabel();
        Label = new javax.swing.JLabel();
        TableScrollPane = new javax.swing.JScrollPane();
        ConnectedTable = new javax.swing.JTable();
        
        //обработчик закрытия окна
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event)
            {    
                StopButtonActionPerformed(null);
            }
        });

        MainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        StartButton.setText("Start");
        StartButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                StartButtonActionPerformed(evt);
        });

        StopButton.setText("Stop");
        StopButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                StopButtonActionPerformed(evt);
        });

        StateLabel.setText("Состояние сервера:");

        Label.setText("Выключен");

        ConnectedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Пользователь", "Дата/Время", "Клиент"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false};
        });
                
        TableScrollPane.setViewportView(ConnectedTable);
        if (ConnectedTable.getColumnModel().getColumnCount() > 0) {
            ConnectedTable.getColumnModel().getColumn(0).setResizable(false);
            ConnectedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            ConnectedTable.getColumnModel().getColumn(1).setResizable(false);
            ConnectedTable.getColumnModel().getColumn(0).setPreferredWidth(80);
            ConnectedTable.getColumnModel().getColumn(2).setResizable(false);
            ConnectedTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        }
        
        //ConnectedTable.removeColumn(ConnectedTable.getColumnModel().getColumn(2));

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(StopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(StateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(TableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartButton)
                    .addComponent(StopButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StateLabel)
                    .addComponent(Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        StopButton.setEnabled(false);
        
        pack();
    }// </editor-fold>                        

    //обработчик клика по кнопке старта сервера
    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt)
    {                                            
        try
        {
            StartButton.setEnabled(false);
            StopButton.setEnabled(true);
            
            PropertyReader.InitComponents();
        
            String address = PropertyReader.GetAdress();
            int port = PropertyReader.GetPort();
            
            //создание серверного соединения
            server = new ServerSocket(port, 0, InetAddress.getByName(address));
            
            model = (javax.swing.table.DefaultTableModel) ConnectedTable.getModel();
            
            sStart = new ServerStart(Label, model, server);
            sStart.setDaemon(true);
            sStart.start();
            
            //vc = new VerifyConnect(users, model);
            //vc.start();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }                                           

    //обработчик клика по кнопке остановки сервера
    private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {     
        try
        {
            //остановка потока прослушивания сети
            if(server == null) return;
            
            //закрытие сокетного соединения
            server.close();
            server = null;
                
            if(sStart.isAlive())
                sStart.interrupt();

            StartButton.setEnabled(true);
            StopButton.setEnabled(false);
            sStart = null;
            Label.setText("Не работает");
            
            //очистка таблицы с подключившимися клиентами
            while (model.getRowCount() != 0)
                model.removeRow(0);

            model = null;

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    } 
    
    //запуск приложения
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
               new MainForm().setVisible(true);
            }
        });
    }
}
