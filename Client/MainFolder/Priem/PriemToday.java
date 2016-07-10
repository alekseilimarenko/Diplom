package umec.Priem;

import TCPConnect.TCPSender;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PriemToday extends javax.swing.JDialog
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private javax.swing.JTable PriemListTable;
    private javax.swing.JPanel PriemPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private TCPSender sender;
    SimpleDateFormat dateFormat;
    String DocLName;
    String PatientLName;
    String PatientbDay;
//</editor-fold>
 
    //конструктор формы
    public PriemToday(java.awt.Frame parent, boolean modal, TCPSender sn, String log)
    {
        super(parent, modal);
        this.DocLName = log;
        this.sender = sn;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Создание элементов окна">                          
    private void initComponents()
    {
        setTitle("График приема");
        
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

        PriemPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PriemListTable = new javax.swing.JTable();
        dateFormat = new SimpleDateFormat("dd#MM#yyyy");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        PriemPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        PriemListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ФИО клиента", "Дата рождения", "Время"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false};
        });
        PriemListTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        PriemListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PriemListTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(PriemListTable);
        PriemListTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (PriemListTable.getColumnModel().getColumnCount() > 0) {
            PriemListTable.getColumnModel().getColumn(0).setResizable(false);
            PriemListTable.getColumnModel().getColumn(0).setPreferredWidth(80);
            PriemListTable.getColumnModel().getColumn(1).setResizable(false);
            PriemListTable.getColumnModel().getColumn(2).setMinWidth(60);
            PriemListTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            PriemListTable.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        javax.swing.GroupLayout PriemPanelLayout = new javax.swing.GroupLayout(PriemPanel);
        PriemPanel.setLayout(PriemPanelLayout);
        PriemPanelLayout.setHorizontalGroup(
            PriemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PriemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PriemPanelLayout.setVerticalGroup(
            PriemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PriemPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PriemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PriemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FillPriemListTable();
        
        pack();
    }// </editor-fold>                        

    //заполнение таблицы приема доктора на текущее число 
    private void FillPriemListTable()
    {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru"));
        String[] curr_day = dateFormat.format(cal.getTime()).split("#");
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //отправка запроса на сервер с фамилией доктора
            String[] resultGrafic =  sender.SendTextToServer("getGrafic_" + DocLName 
                    + "%" + curr_day[1] + curr_day[2]).split("%");    
            
            String priem = resultGrafic[1];

            //сохранение графика приема:    дата:пациент-время,пациент-время;
                                          //дата:пациент-время,пациент-время;
            DefaultTableModel model = (DefaultTableModel) PriemListTable.getModel();
            
            String[] date_priem = priem.split("@");

            for(String dt : date_priem)
            {
                String[] t = dt.split(":");
                if(t[0].equals(curr_day[0]))
                {
                    String[] time_patient = t[1].split(";");
                    for(String tp : time_patient)
                    {
                        String[] p = tp.split("-");
                        String fio, dr, time;
                        if(p[0].equals("null"))
                            fio = null;
                        else
                            fio =p[0];

                        if(p[1].equals("null"))
                            dr = null;
                        else
                            dr = p[1];
                        
                        if(p[2].equals("null"))
                            time = null;
                        else
                            time = p[2];

                        model.addRow(new Object[]{fio, dr, time});
                    }
                }
            }
            if(model.getRowCount() == 0)
            {
                for(int i = 0; i < 15; i++)
                {
                    model.addRow(new Object[]{null, null}); 
                }
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
    
    //обработчик клика по строке в таблице PriemListTable
    private void PriemListTableMouseClicked(java.awt.event.MouseEvent evt) 
    {                 
        String patient = "";
       
        try
        {
            patient = PriemListTable.getValueAt(PriemListTable.getSelectedRow(), 0).toString();
            patient = patient.substring(0, patient.indexOf(' '));
            
            String dr = PriemListTable.getValueAt(PriemListTable.getSelectedRow(), 1).toString();

            String param = DocLName + "-" + patient + "-" + dr;

            new PatientInfo(this, true, sender, param).setVisible(true);
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
    }
}