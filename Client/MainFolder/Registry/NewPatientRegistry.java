package umec.Registry;

import TCPConnect.TCPSender;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.ConnectException;
import java.net.SocketException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class NewPatientRegistry extends JDialog 
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private JButton AddNewPatient_Button;                       //кнопка добавления нового пациента в базу
    private JButton CancelAdd_Button;                           //кнопка отмены
    private JLabel AddressLabel;                                //лейбл адреса
    private JTextField AddressTextField;                        //текстовое поле ввода адреса
    private JLabel BirthDateLabel;                              //лейбл даты рождения
    private JTextField BirthDateTextField;                      //текстовое поле ввода даты рождения
    private JLabel FNameLabel;                                  //лейбл имени
    private JTextField FNameTextField;                          //текстовое поле ввода имени
    private JLabel LNameLabel;                                  //лейбл фамилии
    private JTextField LNameTextField;                          //текствое поле ввода фамилии
    private JPanel MainPanel;                                   //главная панель окна
    private JLabel SNameLabel;                                  //лейбл отчества
    private JTextField SNameTextField;                          //текстовое поле ввода отчества
    private JLabel WorkLabel;                                   //лейбл места работы
    private JTextField WorkTextField;                           //текстовое поле ввода места работы
    private JTable GraficTable;                                 //таблица графика работы
    private TableModel graficTableModel;                        //модель таблицы GraficTable
    private JLabel InsurLabel;                                  //лейбл страховки
    private JTextField InsurTextField;                          //текстовое поле страховки
    private JLabel NumberLabel;                                 //лейбл номера страховки
    private JLabel PhoneLabel;                                  //лейбл телефонного номера
    private JTextField PhoneTextField;                          //текстовое поле телефонного номера
    private JLabel ProfLabel;                                   //лейбл профессии
    private JTextField ProfTextField;                           //текстовое поле профессии
    private JTextField InsurNumberTextField;                    //текстовое поле номера страхового полиса
    private com.toedter.calendar.JDateChooser DateChooser;      //выбор дня работы доктора
    private JScrollPane jScrollPane1;
    private JLabel FileChooserLabel;                            //лейбл выбора файлов
    private JButton FileChooser_Button;                         //кнопка обзора для выбора файлов
    private JButton AddKonsult_Button;                          //кнопка добавления консультации
    private JButton CancelKonsult_Button;                       //кнопка отмены добавления консультации
    private JPanel KonsultPanel;                                //панель добавления консультации
    private JTabbedPane PatientsTabbedPane;                     //вкладка внесения нового пациента в базу данных
    private JLabel WarningLabel1;                               //лейбл предупреждения
    private JLabel SexLabel;                                    //лейбл для указания пола пациента
    private JRadioButton MenRadioButton;                        //радиобаттон для мужчин
    private JRadioButton WomenRadioButton;                      //радиобаттон для женщин
    private javax.swing.JLabel DateKosultLabel;
    private javax.swing.JComboBox LNameDoctorComboBox;
    private javax.swing.JLabel PriemLabel;
    private javax.swing.JComboBox ProfessionComboBox;
    private javax.swing.JLabel ProfessionLabel;
    private javax.swing.JTable DoctorTable;
    private javax.swing.JLabel cityPhoneLabel;
    private javax.swing.JTextField cityPhoneTextField;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel kodLabel;
    private javax.swing.JLabel mobPhoneLabel;
    private javax.swing.JTextField mobPhoneTextField;
    private javax.swing.JComboBox<String> regionComboBox;
    private javax.swing.JComboBox<String> townComboBox;
    private SimpleDateFormat sdf, m_y;
    private final TCPSender sender;                                                   //объект связи с сервером
    private String dateChoose;
    private DefaultTableModel defTableModel;
    private String priem;
    private String DocLName;                                                    //фамилия доктора
    private String workStart = "", workEnd = "";
    private javax.swing.JTextField FileTextField;
    private File file;
    private String[] townList;
    //</editor-fold>
        
    //конструктор формы
    public NewPatientRegistry(Frame parent, boolean modal, TCPSender sn)
    {
        super(parent, modal);
        this.sender = sn;
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Создание компонентов окна">                          
    private void initComponents()
    {
        PatientsTabbedPane = new javax.swing.JTabbedPane();
        MainPanel = new javax.swing.JPanel();
        AddNewPatient_Button = new javax.swing.JButton();
        FNameLabel = new javax.swing.JLabel();
        FNameTextField = new javax.swing.JTextField();
        LNameLabel = new javax.swing.JLabel();
        LNameTextField = new javax.swing.JTextField();
        SNameLabel = new javax.swing.JLabel();
        SNameTextField = new javax.swing.JTextField();
        BirthDateLabel = new javax.swing.JLabel();
        BirthDateTextField = new javax.swing.JTextField();
        AddressLabel = new javax.swing.JLabel();
        AddressTextField = new javax.swing.JTextField();
        WorkLabel = new javax.swing.JLabel();
        WorkTextField = new javax.swing.JTextField();
        CancelAdd_Button = new javax.swing.JButton();
        PhoneLabel = new javax.swing.JLabel();
        mobPhoneTextField = new javax.swing.JTextField();
        ProfTextField = new javax.swing.JTextField();
        ProfLabel = new javax.swing.JLabel();
        InsurLabel = new javax.swing.JLabel();
        InsurTextField = new javax.swing.JTextField();
        NumberLabel = new javax.swing.JLabel();
        InsurNumberTextField = new javax.swing.JTextField();
        FileChooserLabel = new javax.swing.JLabel();
        FileChooser_Button = new javax.swing.JButton();
        WarningLabel1 = new javax.swing.JLabel();
        SexLabel = new javax.swing.JLabel();
        MenRadioButton = new javax.swing.JRadioButton();
        WomenRadioButton = new javax.swing.JRadioButton();
        FileTextField = new javax.swing.JTextField();
        regionComboBox = new javax.swing.JComboBox<>();
        townComboBox = new javax.swing.JComboBox<>();
        cityPhoneTextField = new javax.swing.JTextField();
        mobPhoneLabel = new javax.swing.JLabel();
        cityPhoneLabel = new javax.swing.JLabel();
        kodLabel = new javax.swing.JLabel();
        KonsultPanel = new javax.swing.JPanel();
        ProfessionLabel = new javax.swing.JLabel();
        ProfessionComboBox = new javax.swing.JComboBox();
        DateKosultLabel = new javax.swing.JLabel();
        DateChooser = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        GraficTable = new javax.swing.JTable();
        AddKonsult_Button = new javax.swing.JButton();
        CancelKonsult_Button = new javax.swing.JButton();
        PriemLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DoctorTable = new javax.swing.JTable();
        sdf = new SimpleDateFormat("dd#MM#yyyy");
        m_y = new SimpleDateFormat("MMYYYY");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Регистрация нового клиента");
        
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
        this.setLocation((screenSize.width - frameSize.width) / 7, 
                (screenSize.height - frameSize.height) / 6);

        PatientsTabbedPane.addTab("Данные о клиенте", MainPanel);

        MainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        LNameLabel.setText("Фамилия : ");
        LNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextFieldKeyTyped(evt);
            }
        });

        FNameLabel.setText("Имя : ");
        FNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextFieldKeyTyped(evt);
            }
        });

        SNameLabel.setText("Отчество : ");
        SNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextFieldKeyTyped(evt);
            }
        });

        BirthDateLabel.setText("Число, месяц и год рождения(ЧЧ/ММ/ГГГГ) : ");
        BirthDateTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });
        BirthDateTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                BirthDateTextFieldFocusLost(evt);
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

        AddressLabel.setText("Адрес проживания : ");

        FillRegionComboBox();
        
        regionComboBox.addActionListener((java.awt.event.ActionEvent evt) -> {
            RegionComboBoxActionPerformed(evt);
        });
        
        townComboBox.addActionListener((ActionEvent evt) -> {
            TownComboBoxActionPerformed(evt);
        });


        PhoneLabel.setText("Телефон : ");
                
        cityPhoneLabel.setText("городской :");
        cityPhoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });
        cityPhoneTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cityPhoneTextFieldFocusLost(evt);
            }
        });
        
        mobPhoneLabel.setText("мобильный : +38");
        mobPhoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });
        mobPhoneTextField.addFocusListener(new java.awt.event.FocusAdapter(){
           public void focusGained(java.awt.event.FocusEvent evt){
               mobPhoneTextFieldFocusGained(evt);
           }
        });
        mobPhoneTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                mobPhoneTextFieldFocusLost(evt);
            }
        });
        
        WorkLabel.setText("Место работы : ");
        WorkTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextFieldKeyTyped(evt);
            }
        });

        ProfLabel.setText("Должность : ");
        ProfTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextFieldKeyTyped(evt);
            }
        });
                
        InsurLabel.setText("Страховая компания : ");
        InsurTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextFieldKeyTyped(evt);
            }
        });
                
        NumberLabel.setText("Номер страхового полиса : ");
        InsurNumberTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });
                
        FileChooserLabel.setText("Файл амбулаторной карты : ");

        FileChooser_Button.setText("Обзор");
        FileChooser_Button.addActionListener((ActionEvent evt) -> {
            FileChooser_ButtonActionPerformed(evt);
        });

        WarningLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        WarningLabel1.setText(" Все поля обязательны для заполнения");

        AddNewPatient_Button.setText("Сохранить");
        AddNewPatient_Button.addActionListener((ActionEvent evt) -> {
            AddPatientButtonActionPerformed(evt);
        });

        CancelAdd_Button.setText("Отмена");
        CancelAdd_Button.addActionListener((ActionEvent evt) -> {
            CancelButtonActionPerformed(evt);
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addComponent(WarningLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddNewPatient_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CancelAdd_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(InsurLabel)
                        .addGap(12, 12, 12)
                        .addComponent(InsurTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NumberLabel)
                        .addGap(18, 18, 18)
                        .addComponent(InsurNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addComponent(WorkLabel)
                        .addGap(18, 18, 18)
                        .addComponent(WorkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(ProfLabel)
                        .addGap(20, 20, 20)
                        .addComponent(ProfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(PhoneLabel)
                        .addGap(79, 79, 79)
                        .addComponent(cityPhoneLabel)
                        .addGap(18, 18, 18)
                        .addComponent(kodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cityPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mobPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mobPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(AddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(regionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(townComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AddressTextField))
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                            .addComponent(FileChooserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(FileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(FileChooser_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(MainPanelLayout.createSequentialGroup()
                            .addComponent(BirthDateLabel)
                            .addGap(18, 18, 18)
                            .addComponent(BirthDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(151, 151, 151)
                            .addComponent(SexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(MenRadioButton)
                            .addGap(30, 30, 30)
                            .addComponent(WomenRadioButton))))
                .addContainerGap())
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FNameLabel)
                    .addComponent(FNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SNameLabel)
                    .addComponent(SNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LNameLabel))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BirthDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SexLabel)
                    .addComponent(MenRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WomenRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BirthDateLabel))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddressLabel)
                    .addComponent(AddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(townComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(mobPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PhoneLabel)
                        .addComponent(cityPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cityPhoneLabel)
                        .addComponent(mobPhoneLabel)))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WorkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WorkLabel)
                    .addComponent(ProfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProfLabel))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NumberLabel)
                        .addComponent(InsurNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(InsurTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(InsurLabel)))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FileChooserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FileChooser_Button))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelAdd_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNewPatient_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WarningLabel1))
                .addContainerGap())
        );

        PatientsTabbedPane.addTab("Консультация специалиста", KonsultPanel);

        KonsultPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        DateKosultLabel.setText("Дата консультации : ");
        DateChooser.setLocale(new Locale("ru"));
        
        ProfessionLabel.setText("Cпециалист : ");
        ProfessionComboBox.addActionListener((java.awt.event.ActionEvent evt) -> {
                ProfessionComboBoxActionPerformed(evt);
        });

        DoctorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Фамилия доктора", "Категория", "График работы", "№ моб. телефона"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DoctorTable.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(DoctorTable);
        DoctorTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        DoctorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DoctorTableMouseClicked(evt);
            }
        });
        
        if (DoctorTable.getColumnModel().getColumnCount() > 0) {
            DoctorTable.getColumnModel().getColumn(0).setResizable(false);
            DoctorTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            DoctorTable.getColumnModel().getColumn(1).setResizable(false);
            DoctorTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            DoctorTable.getColumnModel().getColumn(2).setResizable(false);
            DoctorTable.getColumnModel().getColumn(2).setPreferredWidth(80);
            DoctorTable.getColumnModel().getColumn(3).setResizable(false);
            DoctorTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        }

        PriemLabel.setText("График приема клиентов : ");

        GraficTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Фамилия клиента", "Дата рождения", "Время посещения"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true
            };
        });
        GraficTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(GraficTable);
        GraficTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (GraficTable.getColumnModel().getColumnCount() > 0) {
            GraficTable.getColumnModel().getColumn(0).setResizable(false);
            GraficTable.getColumnModel().getColumn(0).setPreferredWidth(15);
            GraficTable.getColumnModel().getColumn(1).setMinWidth(100);
            GraficTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            GraficTable.getColumnModel().getColumn(1).setMaxWidth(100);
            GraficTable.getColumnModel().getColumn(2).setResizable(false);
            GraficTable.getColumnModel().getColumn(2).setPreferredWidth(5);
        }
        
        graficTableModel = GraficTable.getModel();
        graficTableModel.addTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent e)
            {
                VerifyInput(e);
            }
        });
        
        GraficTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(GraficTable);
        GraficTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (GraficTable.getColumnModel().getColumnCount() > 0) {
            GraficTable.getColumnModel().getColumn(0).setResizable(false);
            GraficTable.getColumnModel().getColumn(0).setPreferredWidth(15);
            GraficTable.getColumnModel().getColumn(1).setResizable(false);
            GraficTable.getColumnModel().getColumn(1).setPreferredWidth(5);
        }

        AddKonsult_Button.setText("Сохранить");
        AddKonsult_Button.addActionListener((java.awt.event.ActionEvent evt) -> {
                AddKonsultActionPerformed(evt);
        });
        CancelKonsult_Button.setText("Отмена");
        CancelKonsult_Button.addActionListener((java.awt.event.ActionEvent evt) -> {
                CancelKonsult_ButtonActionPerformed(evt);
        });

        javax.swing.GroupLayout KonsultPanelLayout = new javax.swing.GroupLayout(KonsultPanel);
        KonsultPanel.setLayout(KonsultPanelLayout);
        KonsultPanelLayout.setHorizontalGroup(
            KonsultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KonsultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(KonsultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KonsultPanelLayout.createSequentialGroup()
                        .addComponent(DateKosultLabel)
                        .addGap(18, 18, 18)
                        .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(KonsultPanelLayout.createSequentialGroup()
                        .addComponent(ProfessionLabel)
                        .addGap(28, 28, 28)
                        .addComponent(ProfessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(KonsultPanelLayout.createSequentialGroup()
                        .addComponent(AddKonsult_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(CancelKonsult_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
                .addGroup(KonsultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KonsultPanelLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KonsultPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PriemLabel)
                        .addGap(159, 159, 159))))
        );
        KonsultPanelLayout.setVerticalGroup(
            KonsultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KonsultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(KonsultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KonsultPanelLayout.createSequentialGroup()
                        .addComponent(PriemLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(KonsultPanelLayout.createSequentialGroup()
                        .addGroup(KonsultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DateKosultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(KonsultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProfessionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ProfessionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(KonsultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CancelKonsult_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddKonsult_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PatientsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PatientsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
//        DateChooser.setEnabled(false);
//        ProfessionComboBox.setEnabled(false);
//        LNameComboBox.setEnabled(false);
//        GraficTable.setEnabled(false);
//        AddKonsult_Button.setEnabled(false);
//        CancelKonsult_Button.setEnabled(false);
        
        FillDoctorList();
        
        pack();
    }// </editor-fold>  

    //проверка вводимых букв
    private void TextFieldKeyTyped(KeyEvent evt) 
    {
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c) || (c == KeyEvent.VK_DELETE))
        {
            getToolkit().beep();
            evt.consume();
        }
    }    
    
    //проверка вводимых цифр
    private void TextField1KeyTyped(KeyEvent evt) 
    {
        char c = evt.getKeyChar();
        
        if((c == (char)'/') || (c == (char)'+') || (c == (char)'(') || (c == (char)')'))
        {
            return;
        }
        
        if(!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) )
        {
            getToolkit().beep();
            evt.consume();
        }
    }    
    
    //валидация дня рождения
    private void BirthDateTextFieldFocusLost(java.awt.event.FocusEvent evt)
    {
        String strDate = BirthDateTextField.getText();
        
        String Date_Pattern = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
        
        Pattern pattern = Pattern.compile(Date_Pattern);
        
        Matcher matcher = pattern.matcher(strDate);

        if(matcher.matches())
        {
	    matcher.reset();
			  
            if(matcher.find())
            {
	        String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));
				 
                if (day.equals("31") && (month.equals("4") || month .equals("6")
                        || month.equals("9") || month.equals("11") || month.equals("04")
                        || month .equals("06") || month.equals("09")))
                {
                    JOptionPane.showMessageDialog(this, "Неправильное количество дней", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                    AddNewPatient_Button.setEnabled(false);                      // only 1,3,5,7,8,10,12 has 31 days
                } 
                else if (month.equals("2") || month.equals("02")) 
                {
                    //leap year
                    if(year % 4==0)
                    {
                        if(day.equals("30") || day.equals("31"))
                        {
                            JOptionPane.showMessageDialog(this, "Неправильное количество дней", 
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                            AddNewPatient_Button.setEnabled(false);
			}
                        else
                        {
                            AddNewPatient_Button.setEnabled(true);
			}
                    }
                    else
                    {
		        if(day.equals("29") || day.equals("30") || day.equals("31"))
                        {
                            JOptionPane.showMessageDialog(this, "Неправильное количество дней", 
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                            AddNewPatient_Button.setEnabled(false);
			}
                        else
                        {
                            AddNewPatient_Button.setEnabled(true);
			}
                    }
                }
                else
                {				 
                    AddNewPatient_Button.setEnabled(true);				 
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Неправильный формат даты рождения", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                AddNewPatient_Button.setEnabled(false);
            }		  
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Неправильный формат даты рождения", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            AddNewPatient_Button.setEnabled(false);
        }			    
    }
    
    //заполнение списка областей
    private void FillRegionComboBox()
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //отправка данных на сервер и заполнение комбобокса со списком областей
            regionComboBox.setModel(new DefaultComboBoxModel(sender.SendTextToServer("getRegions").split("/")));
        }
        catch(ConnectException conExc)
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Связь с сервером отсутствует", 
                    "Ошибка соединения", JOptionPane.ERROR_MESSAGE);
            System.out.println(conExc.getMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //заполнение списка городов выбранной области
    private void RegionComboBoxActionPerformed(ActionEvent evt)
    {
        String towns = "";
        
        JComboBox region = (JComboBox)evt.getSource();
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //отправка данных на сервер
            townList = sender.SendTextToServer("getTowns_" + region.getSelectedItem().toString()).split("/");
            
            for(String town : townList)
            {
                String[] tmp = town.split(";");
                towns = towns + tmp[0] + ";";
            }
            
            townComboBox.setModel(new DefaultComboBoxModel(towns.split(";")));
        }
        catch(ConnectException conExc)
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Связь с сервером отсутствует", 
                    "Ошибка соединения", JOptionPane.ERROR_MESSAGE);
            System.out.println(conExc.getMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //получение кода города для стационарного телефона
    private void TownComboBoxActionPerformed(ActionEvent evt)
    {
        JComboBox town = (JComboBox)evt.getSource();
        
        for(String str : townList)
        {
            String[] tmp = str.split(";");
            if(tmp[0].equals(town.getSelectedItem().toString()))
            {
                kodLabel.setText(tmp[1]);
                break;
            }
        } 
    }
    
    //валидация городского номера
    private void cityPhoneTextFieldFocusLost(FocusEvent evt)
    {
        String kod = kodLabel.getText().substring(kodLabel.getText().lastIndexOf(')') + 1, kodLabel.getText().lastIndexOf('X')); 
        String num = PhoneTextField.getText();
        
        String numPattern = "\\d{" + kod.length() + "}";
        
        Pattern pattern = Pattern.compile(numPattern);
        Matcher matcher = pattern.matcher(num);
        
        if(!matcher.matches())
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Неправильный формат городского номера телефона", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            AddNewPatient_Button.setEnabled(false);
        }
        else
            AddNewPatient_Button.setEnabled(true);     
    }
    
    //очистка поля мобильного телефона
    private void mobPhoneTextFieldFocusGained(FocusEvent evt)
    {
        mobPhoneTextField.setText("");        
    }    
    
    //валидация мобильного номера
    private void mobPhoneTextFieldFocusLost(FocusEvent evt)
    {
        Pattern pattern = Pattern.compile("\\(\\d{3}\\)\\d{7}");
        Matcher matcher = pattern.matcher(mobPhoneTextField.getText().trim());
        
        if(!matcher.matches())
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Неправильный формат мобильного номера телефона", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            AddNewPatient_Button.setEnabled(false);
        }
        else
            AddNewPatient_Button.setEnabled(true);     
    }
    
    //проверка вводимых в таблицу значений на соответствие графику работы
    private void VerifyInput(TableModelEvent e)
    {
       if(e.getColumn() == 0)
        {
            AddKonsult_Button.setEnabled(true);
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
                AddKonsult_Button.setEnabled(true);

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
                    AddKonsult_Button.setEnabled(true);
                    return;
                }
                
                //отправка данных на сервер и заполнение комбобокса со списком профессий
                String answer = sender.SendTextToServer("getPatientId_" + lName + "-" + bDay);
                
                if(answer.equals("null"))
                {
                    JOptionPane.showMessageDialog(this, "Пациент с данной фамилией отсутствует в базе", 
                        "Ошибка доступа", JOptionPane.ERROR_MESSAGE);
                    AddKonsult_Button.setEnabled(false);
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
                AddKonsult_Button.setEnabled(true);
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
                AddKonsult_Button.setEnabled(false);
            }
        }
    }
    
    //обработчик нажатия на кнопку выбора файла для загрузки на сервер
    private void FileChooser_ButtonActionPerformed(ActionEvent evt)
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
      
    //обработчик клика на радиобаттон
    private void MenRadioButtonMouseClicked(java.awt.event.MouseEvent evt)
    {                                            
        if(MenRadioButton.isSelected())
        {
            WomenRadioButton.setSelected(false);
        }
    }                                           

    //обработчик клика на радиобаттон
    private void WomenRadioButtonMouseClicked(java.awt.event.MouseEvent evt)
    {                                              
        if(WomenRadioButton.isSelected())
        {
            MenRadioButton.setSelected(false);
        }       
    }                                             
    
    //заполнение комбобокса со списком профессий докторов
    private void FillDoctorList() 
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //отправка запроса на сервер и заполнение комбобокса со списком профессий
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
            Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru"));

            System.out.println(DateChooser.getDate());
            
            if(cal.getTime().compareTo(DateChooser.getDate()) <= 0)
            {
                //проверка соединения с сервером
                if(sender.SendTextToServer("Hello").equals(""))
                {
                    sender.TryConnent();
                }

                //заполнение таблицы докторов
                LNameDoctorComboBox.setModel(new DefaultComboBoxModel(sender.SendTextToServer("getLName_" + 
                    ProfessionComboBox.getSelectedItem().toString()).split("/")));
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
        catch(SocketException conExc)
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
    
    //обработчик клика по строке таблицы докторов
    private void DoctorTableMouseClicked(MouseEvent evt) 
    {
        DocLName = DoctorTable.getValueAt(DoctorTable.getSelectedRow(), 0).toString();
    }
    
    //заполнение таблицы графика работы специалиста
    private void FillWorkTable()
    {
        AddKonsult_Button.setEnabled(true);
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            dateChoose = sdf.format(DateChooser.getDate());
            
            //отправка запроса на сервер с фамилией доктора
            String[] resultGrafic =  sender.SendTextToServer("getGrafic_" + DocLName 
                    + "%" + m_y.format(DateChooser.getDate())).split("%");

            String workTime = resultGrafic[0];
            priem = resultGrafic[1];
            String[] curr_day = dateChoose.split("#");

            if(!workTime.equals("null"))
            {
                String[] date_workTime = workTime.split("I");
                for(String days : date_workTime)
                {
                    String[] day = days.split(";");
                    
                    if(day[1].equals(curr_day[0]))
                    {    
                        workStart = day[2];
                        workEnd = day[3];
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "График работы данного доктора отсутствует", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                AddKonsult_Button.setEnabled(false);
                return;
            }
            
            //сохранение графика приема:    дата:пациент-время,пациент-время;//дата:пациент-время,пациент-время;
            
            if(!workStart.equals("null") && !workEnd.equals("null"))
            {
                defTableModel = (DefaultTableModel) GraficTable.getModel();
            
                if(defTableModel.getRowCount() > 0)
                {
                    while (defTableModel.getRowCount() > 0)  defTableModel.removeRow(0);
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
                                    f = p[0];
                                    dr = p[1];
                                    v = p[2];

                                defTableModel.addRow(new Object[]{f, dr, v});
                            }
                            for(int i = 0; i < 15 - time_patient.length; i++ )
                                defTableModel.addRow(new Object[]{null, null, null});
                        }
                    }
                    if(defTableModel.getRowCount() == 0)
                    {
                        for(int i = 0; i < 15; i++)
                        {
                            defTableModel.addRow(new Object[]{null, null, null}); 
                        }
                    }
                }
                else
                {
                    for(int i = 0; i < 15; i++)
                    {
                        defTableModel.addRow(new Object[]{null, null, null}); 
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
    
    //обработчик нажатия на кнопку добавления в базу нового пациента
    private void AddPatientButtonActionPerformed(ActionEvent evt)
    {             
        AddPatient();
    }
    
    //обработчик нажатия на кнопку отмены добавления нового пациента
    private void CancelButtonActionPerformed(ActionEvent evt)
    {
        this.dispose();
    }
    
     //обработчик нажатия на кнопку добавления консультации
    private void AddKonsultActionPerformed(ActionEvent evt)
    {
        AddVisit();
    }
    
    //обработчик нажатия на кнопку отмены назначения консультации
    private void CancelKonsult_ButtonActionPerformed(ActionEvent evt)
    {
        this.dispose();
    }
    
    //добавление нового пациента в базу данных
    private void AddPatient()
    {
        //проверка на незаполненные поля
        if(LNameTextField.getText().trim().equals("") 
                || FNameTextField.getText().trim().equals("")
                || SNameTextField.getText().trim().equals("")
                || BirthDateTextField.getText().trim().equals("") 
                || (!MenRadioButton.isSelected() && !WomenRadioButton.isSelected())
                || AddressTextField.getText().trim().equals("") 
                || WorkTextField.getText().trim().equals("")
                || ProfTextField.getText().trim().equals("")
                || PhoneTextField.getText().trim().equals("")
                || InsurTextField.getText().trim().equals("")
                || InsurNumberTextField.getText().trim().equals("")
                || FileTextField.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Вы не заполнили все поля",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String sex = "";
        if(MenRadioButton.isSelected())
            sex = "М";
        if(WomenRadioButton.isSelected())
            sex = "Ж";

        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }

            //отправка текстовых данных на сервер
            String infoUpd = sender.SendTextToServer("addPatient_" + LNameTextField.getText().trim() + ","
                + FNameTextField.getText().trim() + "," + SNameTextField.getText().trim() + "," 
                + BirthDateTextField.getText().trim() + "," + sex + ","
                + AddressTextField.getText().trim() + "," + WorkTextField.getText().trim()
                + "," + ProfTextField.getText().trim() + "," + PhoneTextField.getText().trim()
                + "," + InsurTextField.getText().trim() + "," + InsurNumberTextField.getText().trim());
            
            String healthCardUpd = "";

            if(!FileTextField.getText().equals(""))
            {
                String fName = "healthCard/" + LNameTextField.getText().trim().trim() +
                        "-" + BirthDateTextField.getText().trim().trim() + 
                        (file.getName()).substring((file.getName()).lastIndexOf("."));
                
                //отправка файла на сервер
                healthCardUpd = sender.SendFileToServer("receiveFile_" + fName +
                        "_" + file.getAbsolutePath());
            }

            if(infoUpd.equals("OK") && healthCardUpd.equals("OK"))
            {
                JOptionPane.showMessageDialog(this, "Данные сохранены успешно", 
                    "Внимание", JOptionPane.INFORMATION_MESSAGE);

                DateChooser.setEnabled(true);
                ProfessionComboBox.setEnabled(true);
                ProfessionComboBox.setEnabled(true);
                GraficTable.setEnabled(true);
                AddKonsult_Button.setEnabled(true);
                CancelKonsult_Button.setEnabled(true);
            }
            else
                JOptionPane.showMessageDialog(this, "Ошибка сохранения данных", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException sqlex)
        {
            JOptionPane.showMessageDialog(this, "Связь с базой данных отсутствует", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            System.out.println(sqlex.getMessage());
        }
        catch(SocketException conExc)
        {
            JOptionPane.showMessageDialog(this, "Связь с сервером отсутствует", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
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
        //сохранение графика приема:    число:пациент-время,пациент-время;число:пациент-время,пациент-время;

        int columnCount = defTableModel.getColumnCount();
        int rowCount = defTableModel.getRowCount();
        String konsult = "";
        for(int i = 0; i < rowCount; i++)
        {
            for(int j = 0; j < columnCount; j++)
            {
                if(j == (columnCount - 1) && i == (rowCount - 1))
                {
                    if(defTableModel.getValueAt(i,j) != null)
                        konsult = konsult + defTableModel.getValueAt(i,j).toString().replace(',', '.');
                }
                else
                    if(j == (columnCount - 1))
                    {
                        if(defTableModel.getValueAt(i,j) != null)
                            konsult = konsult + defTableModel.getValueAt(i,j).toString().replace(',', '.') + ";";
                    }
                    else
                    {
                        if(defTableModel.getValueAt(i,j) != null)                        
                            konsult = konsult + defTableModel.getValueAt(i,j).toString().replace(',', '.') + "-";
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
        
//     konsult = efwefwf-14.30;null-13.30;null-14.30;null-15.00;null-15.30;null-16.00;null-16.30;null-17.00;null-17.30;null-18.00;null-18.30;
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
            String answer = sender.SendTextToServer("addKonsult_" + DocLName + "#" + m_y.format(DateChooser.getDate()) + "#" + priem);

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