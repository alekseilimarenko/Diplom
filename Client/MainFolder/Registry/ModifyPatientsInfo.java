package umec.Registry;

import TCPConnect.TCPSender;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.net.ConnectException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ModifyPatientsInfo extends javax.swing.JDialog
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JTextField AddressTextField;
    private javax.swing.JLabel BirthDateLabel;
    private javax.swing.JTextField BDayTextField;
    private javax.swing.JTextField FileTextField;
    private javax.swing.JButton Cancel_Button;
    private javax.swing.JLabel FNameLabel;
    private javax.swing.JTextField FNameTextField;
    private javax.swing.JLabel FileChooserLabel;
    private javax.swing.JButton FileChooser_Button;
    private javax.swing.JLabel InsurLabel;
    private javax.swing.JTextField InsurNumberTextField;
    private javax.swing.JTextField InsurTextField;
    private javax.swing.JLabel LNameLabel;
    private javax.swing.JLabel LNameSearchLabel;
    private javax.swing.JTextField LNameTextField;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JRadioButton MenRadioButton;
    private javax.swing.JButton ModifyPatientInfo_Button;
    private javax.swing.JLabel NumberLabel;
    private javax.swing.JTable PatientInfoTable;
    private javax.swing.JLabel PhoneLabel;
    private javax.swing.JTextField PhoneTextField;
    private javax.swing.JLabel ProfLabel;
    private javax.swing.JTextField ProfTextField;
    private javax.swing.JLabel SNameLabel;
    private javax.swing.JTextField SNameTextField;
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JLabel SexLabel;
    private javax.swing.JRadioButton WomenRadioButton;
    private javax.swing.JLabel WorkLabel;
    private javax.swing.JTextField WorkTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private TCPSender sender;
    private String[] patientsInfo;
    private File file;
//</editor-fold>

    //конструктор окна
    public ModifyPatientsInfo(java.awt.Frame parent, boolean modal, TCPSender sn)
    {
        super(parent, modal);
        this.sender = sn;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Создание компонентов окна">                          
    private void initComponents()
    {
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
        this.setLocation((screenSize.width - frameSize.width) / 12, 
                (screenSize.height - frameSize.height) / 5);
        
        SearchPanel = new javax.swing.JPanel();
        LNameSearchLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PatientInfoTable = new javax.swing.JTable();
        MainPanel = new javax.swing.JPanel();
        ModifyPatientInfo_Button = new javax.swing.JButton();
        FNameLabel = new javax.swing.JLabel();
        FNameTextField = new javax.swing.JTextField();
        LNameLabel = new javax.swing.JLabel();
        LNameTextField = new javax.swing.JTextField();
        SNameLabel = new javax.swing.JLabel();
        SNameTextField = new javax.swing.JTextField();
        BirthDateLabel = new javax.swing.JLabel();
        BDayTextField = new javax.swing.JTextField();
        AddressLabel = new javax.swing.JLabel();
        AddressTextField = new javax.swing.JTextField();
        WorkLabel = new javax.swing.JLabel();
        WorkTextField = new javax.swing.JTextField();
        Cancel_Button = new javax.swing.JButton();
        PhoneLabel = new javax.swing.JLabel();
        PhoneTextField = new javax.swing.JTextField();
        ProfTextField = new javax.swing.JTextField();
        ProfLabel = new javax.swing.JLabel();
        InsurLabel = new javax.swing.JLabel();
        InsurTextField = new javax.swing.JTextField();
        NumberLabel = new javax.swing.JLabel();
        InsurNumberTextField = new javax.swing.JTextField();
        FileChooserLabel = new javax.swing.JLabel();
        FileChooser_Button = new javax.swing.JButton();
        SexLabel = new javax.swing.JLabel();
        MenRadioButton = new javax.swing.JRadioButton();
        WomenRadioButton = new javax.swing.JRadioButton();
        FileTextField = new javax.swing.JTextField();
        FileTextField.setEditable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Редактирование информации о клиенте");

        SearchPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        LNameSearchLabel.setText("Выберите клиента :");

        PatientInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Фамилия", "Дата рождения"}
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };
        });
        jScrollPane1.setViewportView(PatientInfoTable);
        PatientInfoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PatientInfoTableMouseClicked(evt);
            }
        });
        if (PatientInfoTable.getColumnModel().getColumnCount() > 0) {
            PatientInfoTable.getColumnModel().getColumn(0).setResizable(false);
            PatientInfoTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            PatientInfoTable.getColumnModel().getColumn(1).setResizable(false);
            PatientInfoTable.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        javax.swing.GroupLayout SearchPanelLayout = new javax.swing.GroupLayout(SearchPanel);
        SearchPanel.setLayout(SearchPanelLayout);
        SearchPanelLayout.setHorizontalGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LNameSearchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(SearchPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        SearchPanelLayout.setVerticalGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LNameSearchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        ModifyPatientInfo_Button.setText("Сохранить");
        ModifyPatientInfo_Button.setToolTipText("");
        ModifyPatientInfo_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifyPatientInfo_ButtonActionPerformed(evt);
            }
        });

        FNameLabel.setText("Имя : ");

        FNameTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        LNameLabel.setText("Фамилия :");

        LNameTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        SNameLabel.setText("Отчество : ");

        SNameTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        BirthDateLabel.setText("Число, месяц и год рождения(ЧЧ/ММ/ГГГГ)  : ");

        BDayTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        AddressLabel.setText("Адрес проживания  :");

        AddressTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        AddressTextField.setName(""); // NOI18N

        WorkLabel.setText("Место работы  : ");

        WorkTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        Cancel_Button.setText("Отмена");
        Cancel_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelAdd_ButtonActionPerformed(evt);
            }
        });

        PhoneLabel.setText("Телефон : ");

        ProfLabel.setText("Должность : ");

        InsurLabel.setText("Страховая компания : ");

        NumberLabel.setText("Номер страхового полиса : ");

        FileChooserLabel.setText("Файл амбулаторной карты :");

        FileChooser_Button.setText("Обзор");
        FileChooser_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileChooser_ButtonActionPerformed(evt);
            }
        });

        SexLabel.setText("Пол :");

        MenRadioButton.setText("Мужской");
        MenRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenRadioButtonMouseClicked(evt);
            }
        });

        WomenRadioButton.setText("Женский");
        WomenRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WomenRadioButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addComponent(FileChooserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FileChooser_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(ModifyPatientInfo_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Cancel_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(WorkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(WorkTextField)
                            .addComponent(AddressTextField)))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(LNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FNameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(FNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SNameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(SNameTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(ProfLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(27, 27, 27)
                                .addComponent(ProfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(BirthDateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BDayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)))
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(SexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(MenRadioButton)
                                .addGap(30, 30, 30)
                                .addComponent(WomenRadioButton))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(PhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(PhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addComponent(InsurLabel)
                        .addGap(12, 12, 12)
                        .addComponent(InsurTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(InsurNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        MainPanelLayout.setVerticalGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FNameLabel)
                    .addComponent(FNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SNameLabel)
                    .addComponent(SNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LNameLabel))
                .addGap(30, 30, 30)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BirthDateLabel)
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BDayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SexLabel)
                        .addComponent(MenRadioButton)
                        .addComponent(WomenRadioButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddressLabel))
                .addGap(30, 30, 30)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WorkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WorkLabel))
                .addGap(30, 30, 30)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProfLabel)
                    .addComponent(PhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhoneLabel)
                    .addComponent(ProfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(InsurNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(InsurTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(InsurLabel)))
                .addGap(30, 30, 30)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FileChooserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FileChooser_Button)
                    .addComponent(ModifyPatientInfo_Button)
                    .addComponent(Cancel_Button)
                    .addComponent(FileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

                LNameTextField.setEnabled(false);
                FNameTextField.setEnabled(false);
                SNameTextField.setEnabled(false);
                BDayTextField.setEnabled(false);
                AddressTextField.setEnabled(false);
                WorkTextField.setEnabled(false);
                ProfTextField.setEnabled(false);
                PhoneTextField.setEnabled(false);
                InsurTextField.setEnabled(false);
                InsurNumberTextField.setEnabled(false);
                MenRadioButton.setEnabled(false);
                WomenRadioButton.setEnabled(false);
                FileChooser_Button.setEnabled(false);
                ModifyPatientInfo_Button.setEnabled(false);
        
        FillPatientsList();
        
        pack();
    }// </editor-fold>  
    
    //обработчик клика на радиобаттон
    private void WomenRadioButtonMouseClicked(MouseEvent evt) 
    {
        if(WomenRadioButton.isSelected())
        {
            MenRadioButton.setSelected(false);
            patientsInfo[5] = "Женский";
        }       
    }
    
    //обработчик клика на радиобаттон
    private void MenRadioButtonMouseClicked(MouseEvent evt) 
    {
        if(MenRadioButton.isSelected())
        {
            WomenRadioButton.setSelected(false);
            patientsInfo[5] = "Мужской";
        }
    }
    
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
            
            String answer = sender.SendTextToServer("getPatientInfo_" 
                    + PatientInfoTable.getValueAt(PatientInfoTable.getSelectedRow(), 0)
                    + "-" + PatientInfoTable.getValueAt(PatientInfoTable.getSelectedRow(), 1));
        
            if(!answer.equals("null"))
            {
                patientsInfo = answer.split(",");
                int id = Integer.parseInt(patientsInfo[0]);
                LNameTextField.setText(patientsInfo[1]);
                FNameTextField.setText(patientsInfo[2]);
                SNameTextField.setText(patientsInfo[3]);
                BDayTextField.setText(patientsInfo[4]);
                AddressTextField.setText(patientsInfo[6]);
                WorkTextField.setText(patientsInfo[7]);
                ProfTextField.setText(patientsInfo[8]);
                PhoneTextField.setText(patientsInfo[9]);
                InsurTextField.setText(patientsInfo[10]);
                InsurNumberTextField.setText(patientsInfo[11]);

                if(patientsInfo[5].equals("Мужской"))
                {
                    MenRadioButton.setSelected(true);
                    WomenRadioButton.setSelected(false);
                }
                else
                {
                    WomenRadioButton.setSelected(true);
                    MenRadioButton.setSelected(false);
                }
                                
                LNameTextField.setEnabled(true);
                FNameTextField.setEnabled(true);
                SNameTextField.setEnabled(true);
                BDayTextField.setEnabled(true);
                AddressTextField.setEnabled(true);
                WorkTextField.setEnabled(true);
                ProfTextField.setEnabled(true);
                PhoneTextField.setEnabled(true);
                InsurTextField.setEnabled(true);
                InsurNumberTextField.setEnabled(true);
                MenRadioButton.setEnabled(true);
                WomenRadioButton.setEnabled(true);
                FileChooser_Button.setEnabled(true);
                ModifyPatientInfo_Button.setEnabled(true);

            }
            else
                 JOptionPane.showMessageDialog(this, "Ошибка получения данных о выбранном работнике", 
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

    //обработчик выбора файла медкарты для отправки на сервер
    private void FileChooser_ButtonActionPerformed(java.awt.event.ActionEvent evt)
    {                                                   
        JFileChooser fileopen = new JFileChooser();
        fileopen.setFileFilter(new FileNameExtensionFilter("pdf Файлы", "pdf"));
        int res = fileopen.showDialog(this, "Открытие файла");
        if(res == JFileChooser.APPROVE_OPTION)
        {
            file = fileopen.getSelectedFile();
            FileTextField.setText(file.getName());
        }    
    }                                                  

    //обработчик нажатия на кнопку сохранения внесенных данных
    private void ModifyPatientInfo_ButtonActionPerformed(ActionEvent evt) 
    {
        SaveData();
    }
    
    //обработчик нажатия на кнопку отмены
    private void CancelAdd_ButtonActionPerformed(java.awt.event.ActionEvent evt)
    {                                                                     
        this.dispose();
    }                                                                    
    
    //функция сохранения данных на сервере
    private void SaveData()
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            String sex = "";
                if(MenRadioButton.isSelected())
                    sex = "М";
                if(WomenRadioButton.isSelected())
                    sex = "Ж";

            //отправка текстовых данных на сервер
            String infoUpd = sender.SendTextToServer("updatePatientInfo_"
                    + patientsInfo[0] + "," 
                    + LNameTextField.getText().trim() + "," 
                    + FNameTextField.getText().trim() + ","
                    + SNameTextField.getText().trim() + "," 
                    + BDayTextField.getText().trim() + "," 
                    + sex + "," 
                    + AddressTextField.getText().trim() + "," 
                    + WorkTextField.getText().trim() + "," 
                    + ProfTextField.getText().trim() + "," 
                    + PhoneTextField.getText().trim() + "," 
                    + InsurTextField.getText().trim() + "," 
                    + InsurNumberTextField.getText().trim());

            String healthCardUpd = "";
            
            if(!FileTextField.getText().equals(""))
            {
                String fName = "healthCard/" + LNameTextField.getText().trim() 
                        + "-" + BDayTextField.getText().trim() + 
                        (file.getName()).substring((file.getName()).lastIndexOf("."));
                //отправка файла на сервер
                healthCardUpd = sender.SendFileToServer("receiveFile_" + fName + 
                        "_" + file.getAbsolutePath());
            }
            
            if(infoUpd.equals("OK") && healthCardUpd.equals("OK"))
            {
                JOptionPane.showMessageDialog(this, "Сведения успешно обновлены", 
                    "Внимание", JOptionPane.INFORMATION_MESSAGE);
                
                LNameTextField.setText("");
                FNameTextField.setText("");
                SNameTextField.setText("");
                BDayTextField.setText("");
                AddressTextField.setText("");
                WorkTextField.setText("");
                ProfTextField.setText("");
                PhoneTextField.setText("");
                InsurTextField.setText("");
                InsurNumberTextField.setText("");
                FileTextField.setText("");
                MenRadioButton.setSelected(false);
                WomenRadioButton.setSelected(false);
                                
                LNameTextField.setEnabled(false);
                FNameTextField.setEnabled(false);
                SNameTextField.setEnabled(false);
                BDayTextField.setEnabled(false);
                AddressTextField.setEnabled(false);
                WorkTextField.setEnabled(false);
                ProfTextField.setEnabled(false);
                PhoneTextField.setEnabled(false);
                InsurTextField.setEnabled(false);
                InsurNumberTextField.setEnabled(false);
                MenRadioButton.setEnabled(false);
                WomenRadioButton.setEnabled(false);
                FileChooser_Button.setEnabled(false);
                ModifyPatientInfo_Button.setEnabled(false);
            }
            else
            JOptionPane.showMessageDialog(this, "Ошибка обновления данных", 
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
