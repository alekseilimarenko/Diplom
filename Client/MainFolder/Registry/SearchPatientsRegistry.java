package umec.Registry;

import TCPConnect.TCPSender;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SearchPatientsRegistry extends JDialog
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменых">
    private javax.swing.JButton Cancel_Button;
    private com.toedter.calendar.JDateChooser DateChooser;
    private javax.swing.JLabel DateKosultLabel;
    private javax.swing.JTable GraficTable;
    private javax.swing.JPanel HistoryPanel;
    private javax.swing.JComboBox LNameDoctorComboBox;
    private javax.swing.JLabel LNameDoctorLabel;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel NewVisitPanel;
    private javax.swing.JTable PatientInfoTable;
    private javax.swing.JLabel PhoneLabel;
    private javax.swing.JTextField PhoneTextField;
    private javax.swing.JLabel PriemLabel;
    private javax.swing.JComboBox ProfessionComboBox;
    private javax.swing.JLabel ProfessionLabel;
    private javax.swing.JButton Save_Button;
    private javax.swing.JTable VisitHistoryTable;
    private javax.swing.JLabel WorkSheduleLabel;
    private javax.swing.JTextField WorkSheduleTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private TableModel graficTableModel;
    private DefaultTableModel defGraficTableModel;
    private DefaultTableModel defVisitHistoryTableModel;
    private TCPSender sender;
    private Date date;                                          //дата консультации
    private SimpleDateFormat sdf, m_y;
    private String priem;
    private String dateChoose;
    private String DocLName;
    private String workStart = "", workEnd = "";    

//</editor-fold>
    
    //конструктор окна
    public SearchPatientsRegistry(Frame parent, boolean modal, TCPSender sn)
    {
        super(parent, modal);
        this.sender = sn;
        initComponents();
    }
    
    //<editor-fold defaultstate="collapsed" desc="создание компонентов окна">
    private void initComponents()
    {
        MainPanel = new javax.swing.JPanel();
        HistoryPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        VisitHistoryTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        PatientInfoTable = new javax.swing.JTable();
        NewVisitPanel = new javax.swing.JPanel();
        ProfessionComboBox = new javax.swing.JComboBox();
        Save_Button = new javax.swing.JButton();
        Cancel_Button = new javax.swing.JButton();
        DateChooser = new com.toedter.calendar.JDateChooser();
        ProfessionLabel = new javax.swing.JLabel();
        DateKosultLabel = new javax.swing.JLabel();
        PriemLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        GraficTable = new javax.swing.JTable();
        LNameDoctorComboBox = new javax.swing.JComboBox();
        LNameDoctorLabel = new javax.swing.JLabel();
        PhoneLabel = new javax.swing.JLabel();
        WorkSheduleTextField = new javax.swing.JTextField();
        PhoneTextField = new javax.swing.JTextField();
        WorkSheduleLabel = new javax.swing.JLabel();
        sdf = new SimpleDateFormat("dd#MM#yyyy");
        m_y = new SimpleDateFormat("MMyyyy");


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Консультация:");
        
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
        this.setLocation((screenSize.width - frameSize.width) / 6, 
                (screenSize.height - frameSize.height) / 8);

        VisitHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Фамилия доктора", "Дата посещения"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(VisitHistoryTable);
        if (VisitHistoryTable.getColumnModel().getColumnCount() > 0) {
            VisitHistoryTable.getColumnModel().getColumn(0).setResizable(false);
            VisitHistoryTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            VisitHistoryTable.getColumnModel().getColumn(1).setResizable(false);
            VisitHistoryTable.getColumnModel().getColumn(1).setPreferredWidth(12);
        }

        PatientInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Фамилия клиента", "Дата рождения"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(PatientInfoTable);
        PatientInfoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PatientInfoTableMouseClicked(evt);
            }
        });
        if (PatientInfoTable.getColumnModel().getColumnCount() > 0) {
            PatientInfoTable.getColumnModel().getColumn(0).setResizable(false);
            PatientInfoTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            PatientInfoTable.getColumnModel().getColumn(1).setResizable(false);
            PatientInfoTable.getColumnModel().getColumn(1).setPreferredWidth(12);
        }

        javax.swing.GroupLayout HistoryPanelLayout = new javax.swing.GroupLayout(HistoryPanel);
        HistoryPanel.setLayout(HistoryPanelLayout);
        HistoryPanelLayout.setHorizontalGroup(
            HistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        HistoryPanelLayout.setVerticalGroup(
            HistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HistoryPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HistoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HistoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        NewVisitPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Назначение даты нового посещения"));

        ProfessionComboBox.addActionListener((ActionEvent evt) -> {
                ProfessionComboBoxActionPerformed(evt);
        });
        
        LNameDoctorComboBox.addActionListener((ActionEvent evt)-> {
                LNameDoctorComboBoxActionPerformed(evt);
        });

        Save_Button.setText("Сохранить");
        Save_Button.addActionListener((java.awt.event.ActionEvent evt) -> {
                Save_ButtonActionPerformed(evt);
        });

        Cancel_Button.setText("Отмена");
        Cancel_Button.addActionListener((ActionEvent evt) -> {
            Cancel_ButtonActionPerformed(evt); 
        });

        ProfessionLabel.setText("Cпециалист : ");

        DateKosultLabel.setText("Дата консультации :");
        DateChooser.setLocale(new Locale("ru"));

        PriemLabel.setText("График приема : ");

        GraficTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Фамилия клиента", "Дата рождения", "Время посещения"}
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true
            };
        });
        
        jScrollPane2.setViewportView(GraficTable);
        GraficTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (GraficTable.getColumnModel().getColumnCount() > 0) {
            GraficTable.getColumnModel().getColumn(0).setResizable(false);
            GraficTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            GraficTable.getColumnModel().getColumn(1).setMinWidth(100);
            GraficTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            GraficTable.getColumnModel().getColumn(1).setMaxWidth(100);
            GraficTable.getColumnModel().getColumn(2).setResizable(false);
            GraficTable.getColumnModel().getColumn(2).setPreferredWidth(5);
        }
        
        graficTableModel = GraficTable.getModel();
        graficTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e)
            {
                VerifyInput(e);
            }
        });
        
        LNameDoctorLabel.setText("Фамилия : ");

        PhoneLabel.setText("№ телефона доктора : ");

        WorkSheduleTextField.setEditable(false);
        WorkSheduleTextField.setFont(new java.awt.Font("Times New Roman", 1, 12));

        PhoneTextField.setEditable(false);
        PhoneTextField.setFont(new java.awt.Font("Times New Roman", 1, 12));

        WorkSheduleLabel.setText("График работы : ");
        
                javax.swing.GroupLayout NewVisitPanelLayout = new javax.swing.GroupLayout(NewVisitPanel);
        NewVisitPanel.setLayout(NewVisitPanelLayout);
        NewVisitPanelLayout.setHorizontalGroup(
            NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewVisitPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(NewVisitPanelLayout.createSequentialGroup()
                        .addGroup(NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProfessionLabel)
                            .addComponent(DateKosultLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProfessionComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DateChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NewVisitPanelLayout.createSequentialGroup()
                        .addGroup(NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PhoneLabel)
                            .addComponent(PriemLabel)
                            .addComponent(WorkSheduleLabel)
                            .addComponent(LNameDoctorLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(WorkSheduleTextField)
                            .addComponent(PhoneTextField)
                            .addComponent(LNameDoctorComboBox, 0, 167, Short.MAX_VALUE)))
                    .addGroup(NewVisitPanelLayout.createSequentialGroup()
                        .addComponent(Save_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancel_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        NewVisitPanelLayout.setVerticalGroup(
            NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewVisitPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DateKosultLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProfessionLabel)
                    .addComponent(ProfessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LNameDoctorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LNameDoctorLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PhoneLabel)
                    .addComponent(PhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WorkSheduleLabel)
                    .addComponent(WorkSheduleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PriemLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NewVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save_Button)
                    .addComponent(Cancel_Button))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NewVisitPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NewVisitPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );


        ProfessionComboBox.setEnabled(false);
        Save_Button.setEnabled(false);
        Cancel_Button.setEnabled(false);
        DateChooser.setEnabled(false);
        GraficTable.setEnabled(false);
        LNameDoctorComboBox.setEnabled(false);
        
        FillDoctorList();
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
    
    //обработчик клика на список пациентов
    private void PatientInfoTableMouseClicked(java.awt.event.MouseEvent evt)
    {                                          
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //отправка запроса на сервер для получения списка консультаций выбранного пациента
            String answer = sender.SendTextToServer("getKonsultList_" 
                    + PatientInfoTable.getValueAt(PatientInfoTable.getSelectedRow(), 0)
                    + "-" + PatientInfoTable.getValueAt(PatientInfoTable.getSelectedRow(), 1));
            
            defVisitHistoryTableModel = (DefaultTableModel) VisitHistoryTable.getModel();
            
            if(answer.equals("id"))
            {
                JOptionPane.showMessageDialog(this, "Клиент с данной фамилией в базе отсутствует", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else
            {
                if(defVisitHistoryTableModel.getRowCount() > 0)
                {
                    while(defVisitHistoryTableModel.getRowCount() > 0) defVisitHistoryTableModel.removeRow(0);
                }
                
                String[] konsList = answer.split("#");
                if(!konsList[1].equals("null"))
                {
                    for(String kons : konsList[1].split(";"))
                    {
                        String[] value = kons.split("-");
                        defVisitHistoryTableModel.addRow(new Object[]{value[0], value[1]});
                    }
                }
            }
            
            ProfessionComboBox.setEnabled(true);
            Save_Button.setEnabled(true);
            Cancel_Button.setEnabled(true);
            DateChooser.setEnabled(true);
            GraficTable.setEnabled(true);
            LNameDoctorComboBox.setEnabled(true);
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
    
    //проверка вводимых в таблицу значений на соответствие графику работы
    private void VerifyInput(TableModelEvent e)
    {
        if(e.getColumn() == 0)
        {
            Save_Button.setEnabled(true);
            return;
        }
        
        if(e.getColumn() == 1)
        {
            try
            {
                //проверка соединения с сервером
                if(sender.SendTextToServer("Hello").equals(""))
                {
                    sender.TryConnent();
                }
                
                String lName = graficTableModel.getValueAt(e.getFirstRow(), 0).toString();
                String bDay = graficTableModel.getValueAt(e.getFirstRow(), 1).toString();

                try
                {
                    lName = lName.substring(0, lName.indexOf(' '));
                }
                catch(StringIndexOutOfBoundsException str)
                {
                    lName = lName;
                }
                 
                if(bDay.length() < 10)
                {
                    JOptionPane.showMessageDialog(this, "Неправильный формат даты рождения", 
                        "Ошибка доступа", JOptionPane.ERROR_MESSAGE);
                    Save_Button.setEnabled(false);
                    return;
                }
                
                //отправка данных на сервер и заполнение комбобокса со списком профессий
                String answer = sender.SendTextToServer("getPatientId_" + lName + "-" + bDay);
                
                if(answer.equals("null"))
                {
                    JOptionPane.showMessageDialog(this, "Клиент с данной фамилией отсутствует в базе", 
                        "Ошибка доступа", JOptionPane.ERROR_MESSAGE);
                    Save_Button.setEnabled(false);
                    return;
                }
            }
            catch(ConnectException conExc)
            {
                JOptionPane.showMessageDialog(this, "Связь с сервером отсутствует", 
                        "Ошибка соединения", JOptionPane.ERROR_MESSAGE);
                System.out.println(conExc.getMessage());
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }

        double value = 0;
        
        if(e.getType() == TableModelEvent.UPDATE)
        {
            try
            {
                Save_Button.setEnabled(true);
                value = Double.valueOf(graficTableModel.getValueAt(e.getFirstRow(), 2).toString().replace(',', '.'));
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

            if(value < Double.valueOf(workStart) || value > Double.valueOf(workEnd))
            {
                JOptionPane.showMessageDialog(GraficTable, "Введено неправильное время консультации", 
                "Ошибка", JOptionPane.ERROR_MESSAGE);
                Save_Button.setEnabled(false);
            }
        }
    }
    
    //обработчик нажатия на кнопку подтверждения выбора
    private void Save_ButtonActionPerformed(ActionEvent evt)
    {
        AddVisit();
    }

    //обработчик нажатия на кнопку отмены
    private void Cancel_ButtonActionPerformed(ActionEvent evt) 
    {
        this.dispose();
    }
    
    //заполнение комбобокса со списком специалистов
    private void FillDoctorList() 
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //отправка данных на сервер и заполнение комбобокса со списком профессий
            ProfessionComboBox.setModel(new DefaultComboBoxModel(sender.SendTextToServer("getDoctorList").split("/")));
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
    
    //обработчик события выбора строки в комбобоксе со списком специалистов
    private void ProfessionComboBoxActionPerformed(ActionEvent evt)
    {
        try
        {
            JComboBox box = (JComboBox)evt.getSource();
            
            Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru"));
            Date presentDate = cal.getTime();

            date = DateChooser.getDate();
            System.out.println(date);

            if(date.after(presentDate))
            {
                //проверка соединения с сервером
                if(sender.SendTextToServer("Hello").equals(""))
                {
                    sender.TryConnent();
                }
                
                //заполнение комбобокса с фамилией доктора
                LNameDoctorComboBox.setModel(new DefaultComboBoxModel(sender.SendTextToServer("getLName_" + 
                    box.getSelectedItem().toString()).split("/")));
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Ошибка выбора даты", "Неверная "
                        + "дата обследования", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(ConnectException conExc)
        {
            JOptionPane.showMessageDialog(this, "Связь с сервером отсутствует", 
                    "Ошибка соединения", JOptionPane.ERROR_MESSAGE);
            System.out.println(conExc.getMessage());
        }
        catch(NullPointerException nul)
        {
            JOptionPane.showMessageDialog(this, "Выберите дату консультации", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //обработчик события выбора строки в комбобоксе с фамилиями докторов
    private void LNameDoctorComboBoxActionPerformed(ActionEvent evt)
    {
        JComboBox box = (JComboBox)evt.getSource();
        DocLName = box.getSelectedItem().toString();
        
        FillWorkTable();
    }
    
    //заполнение таблицы графика работы специалиста
    private void FillWorkTable()
    {
        Save_Button.setEnabled(true);
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            dateChoose = sdf.format(date);
            
            //отправка запроса на сервер с фамилией доктора
            String[] resultGrafic =  sender.SendTextToServer("getGrafic_" + DocLName 
                    + "%" + m_y.format(date)).split("%");    
            
            PhoneTextField.setText(resultGrafic[2]);
            String workTime = resultGrafic[0];
            priem = resultGrafic[1];
            String[] curr_day = dateChoose.split("#");

            if(!workTime.equals("null"))
            {
                String[] date_workTime = workTime.split("I");
                for(String s : date_workTime)
                {
                    String[] s1 = s.split(";");
                    
                    if(s1[1].equals(curr_day[0]))
                    {    
                        workStart = s1[2];
                        workEnd = s1[3];
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "График работы данного доктора отсутствует", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                Save_Button.setEnabled(false);
                return;
            }
            
            //сохранение графика приема:    дата:пациент-время,пациент-время;
                                          //дата:пациент-время,пациент-время;
            if(!workStart.equals("null") && !workEnd.equals("null"))
            {
                WorkSheduleTextField.setText("на " + dateChoose.replace('#', '/') + " c " + workStart + " до " + workEnd);
                defGraficTableModel = (DefaultTableModel) GraficTable.getModel();
            
                if(defGraficTableModel.getRowCount() > 0)
                {
                    while (defGraficTableModel.getRowCount() > 0)  defGraficTableModel.removeRow(0);
                }
                
                if(!priem.equals("null"))
                {
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
                                String f, dr, v;
                                    f =p[0];
                                    dr = p[1];
                                    v = p[2];

                                defGraficTableModel.addRow(new Object[]{f, dr, v});
                            }
                            for(int i = 0; i < 15 - time_patient.length; i++ )
                                defGraficTableModel.addRow(new Object[]{null, null, null});
                        }
                    }
                    if(defGraficTableModel.getRowCount() == 0)
                    {
                        for(int i = 0; i < 15; i++)
                        {
                            defGraficTableModel.addRow(new Object[]{null, null, null}); 
                        }
                    }
                }
                else
                {
                    for(int i = 0; i < 15; i++)
                    {
                        defGraficTableModel.addRow(new Object[]{null, null, null}); 
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "На указанную дату приема нет", 
                    "Внимание", JOptionPane.INFORMATION_MESSAGE);
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
    
    //добавление консультации в базу данных
    private void AddVisit()
    {
        //сохранение графика приема:    число:пациент-время,пациент-время;
        //                              число:пациент-время,пациент-время;
        int columnCount = defGraficTableModel.getColumnCount();
        int rowCount = defGraficTableModel.getRowCount();
        String konsult = "";
        for(int i = 0; i < rowCount; i++)
        {
            for(int j = 0; j < columnCount; j++)
            {
                if(j == (columnCount - 1) && i == (rowCount - 1))
                {
                    if(defGraficTableModel.getValueAt(i,j) != null)
                        konsult = konsult + defGraficTableModel.getValueAt(i,j).toString().replace(',', '.');
                }
                else
                    if(j == (columnCount - 1))
                    {
                        if(defGraficTableModel.getValueAt(i,j) != null)
                            konsult = konsult + defGraficTableModel.getValueAt(i,j).toString().replace(',', '.') + ";";
                    }
                    else
                    {
                        if(defGraficTableModel.getValueAt(i,j) != null)                        
                            konsult = konsult + defGraficTableModel.getValueAt(i,j).toString().replace(',', '.') + "-";
                    }
            }
        }
        
        String[] konsArray = konsult.split(";");
        for(int i = konsArray.length - 1; i > 0; i--)
        {
            for (int j = 0; j < i; j++)
            {
                String[] tmp1 = konsArray[j].split("-");
                String[] tmp2 = konsArray[j+1].split("-");
                if(!tmp1[2].equals("null") && !tmp2[2].equals("null"))
                {
                    if(Double.parseDouble(tmp1[2]) > Double.parseDouble(tmp2[2]))
                    {
                        String repl = konsArray[j];
                        konsArray[j] = konsArray[j+1];
                        konsArray[j+1] = repl;
                    }
                }
            }
        }
        
        konsult = "";

        for(int i = 0; i < konsArray.length; i++)
        {
            if(i == konsArray.length-1)
                konsult = konsult + konsArray[i] + "@";                
            else
                konsult = konsult + konsArray[i] + ";";
        }
        
        //konsult = efwefwf-13.30;null-14.00;null-14.30;null-15.00;null-15.30;null-16.00;null-16.30;null-17.00;null-17.30;null-18.00;null-18.30;
        String[] curr_day = dateChoose.split("#");
        
        if(!priem.equals("null"))
        {
            String[] date_priem = priem.split("@");

            for(String dt : date_priem)
            {
                String[] t = dt.split(":");
                if(t[0].equals(curr_day[0]))                    //поиск в графике консультаций даты на которую назначена консультация
                {
                    String repl = t[0] + ":" + t[1] + "@";
                    priem = priem.replaceAll(repl, "");
                }
            }
            
            priem = priem + curr_day[0] + ":" + konsult;
        }
        else
        {
            priem = curr_day[0] + ":" + konsult;
        }
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
        
            String answer = sender.SendTextToServer("addKonsult_" + DocLName + "#" + m_y.format(date) + "#" + priem);

            if(answer.equals("OK"))
            {
                JOptionPane.showMessageDialog(this, "Данные сохранены успешно", 
                        "Внимание", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else
                JOptionPane.showMessageDialog(this, "Ошибка сохранения данных", 
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
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
}