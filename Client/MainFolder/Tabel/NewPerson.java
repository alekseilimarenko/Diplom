package umec.Tabel;

import TCPConnect.TCPSender;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.ConnectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class NewPerson extends javax.swing.JDialog
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private javax.swing.JButton AddNewPerson_Button;
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JTextField AddressTextField;
    private javax.swing.JLabel BirthDateLabel;
    private javax.swing.JTextField BirthDateTextField;
    private javax.swing.JButton Cancel_Button;
    private javax.swing.JComboBox GroupComboBox;
    private javax.swing.JLabel CategoryLabel;
    private javax.swing.JLabel FNameLabel;
    private javax.swing.JTextField FNameTextField;
    private com.toedter.calendar.JDateChooser HireDateChooser;
    private javax.swing.JLabel HireLabel;
    private javax.swing.JRadioButton KonsultRadioButton;
    private javax.swing.JLabel LNameLabel;
    private javax.swing.JTextField LNameTextField;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JRadioButton MenRadioButton;
    private javax.swing.JLabel PhoneLabel;
    private javax.swing.JTextField PhoneTextField;
    private javax.swing.JTextField PhoneTextField1;
    private javax.swing.JLabel ProfLabel;
    private javax.swing.JTextField ProfTextField;
    private javax.swing.JComboBox<String> RegionComboBox;
    private javax.swing.JLabel SNameLabel;
    private javax.swing.JTextField SNameTextField;
    private javax.swing.JLabel SalaryLabel;
    private javax.swing.JTextField SalaryTextField;
    private javax.swing.JLabel SexLabel;
    private javax.swing.JRadioButton StateRadioButton;
    private javax.swing.JComboBox<String> TownComboBox;
    private javax.swing.JLabel VUSLabel;
    private javax.swing.JTextField VUSTextField;
    private javax.swing.JLabel WarningLabel1;
    private javax.swing.JRadioButton WomenRadioButton;
    private javax.swing.JLabel kodLabel;
    private javax.swing.JLabel mobPhoneLabel;
    private javax.swing.JLabel typePhoneLabel;
    private TCPSender sender;                                            //объект соединения с сервером
    private String sex = "", category = "";
    private java.awt.Frame parent;
    private SimpleDateFormat sdf;
    private String hireDate, disDate;                                  //даты приема и увольнения
    private String[] townList;
//</editor-fold>
    
    //конструктор формы
    public NewPerson(java.awt.Frame pt, boolean modal, TCPSender sn)
    {
        super(pt, modal);
        parent = pt;
        this.sender = sn;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Создание компонентов окна">                          
    private void initComponents()
    {
        MainPanel = new javax.swing.JPanel();
        LNameLabel = new javax.swing.JLabel();
        LNameTextField = new javax.swing.JTextField();
        FNameLabel = new javax.swing.JLabel();
        FNameTextField = new javax.swing.JTextField();
        SNameLabel = new javax.swing.JLabel();
        SNameTextField = new javax.swing.JTextField();
        BirthDateLabel = new javax.swing.JLabel();
        BirthDateTextField = new javax.swing.JTextField();
        SexLabel = new javax.swing.JLabel();
        AddressLabel = new javax.swing.JLabel();
        AddressTextField = new javax.swing.JTextField();
        ProfLabel = new javax.swing.JLabel();
        PhoneLabel = new javax.swing.JLabel();
        ProfTextField = new javax.swing.JTextField();
        PhoneTextField = new javax.swing.JTextField();
        WarningLabel1 = new javax.swing.JLabel();
        AddNewPerson_Button = new javax.swing.JButton();
        Cancel_Button = new javax.swing.JButton();
        VUSLabel = new javax.swing.JLabel();
        VUSTextField = new javax.swing.JTextField();
        HireLabel = new javax.swing.JLabel();
        SalaryLabel = new javax.swing.JLabel();
        SalaryTextField = new javax.swing.JTextField();
        WomenRadioButton = new javax.swing.JRadioButton();
        MenRadioButton = new javax.swing.JRadioButton();
        CategoryLabel = new javax.swing.JLabel();
        GroupComboBox = new javax.swing.JComboBox();
        RegionComboBox = new javax.swing.JComboBox<>();
        TownComboBox = new javax.swing.JComboBox<>();
        typePhoneLabel = new javax.swing.JLabel();
        mobPhoneLabel = new javax.swing.JLabel();
        kodLabel = new javax.swing.JLabel();
        PhoneTextField1 = new javax.swing.JTextField();
        HireDateChooser = new com.toedter.calendar.JDateChooser();
        KonsultRadioButton = new javax.swing.JRadioButton();
        StateRadioButton = new javax.swing.JRadioButton();
        sdf = new SimpleDateFormat("dd/MM/yyyy");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Заполнение учетной карточки нового работника");
        
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
                (screenSize.height - frameSize.height) / 5);

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
        BirthDateTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                BirthDateTextFieldFocusLost(evt);
            }
        });
        
        BirthDateTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });

        SexLabel.setText("Пол : ");
        
        WomenRadioButton.setText("Женский");
        WomenRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WomenRadioButtonMouseClicked(evt);
            }
        });

        MenRadioButton.setText("Мужской");
        MenRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenRadioButtonMouseClicked(evt);
            }
        });

        AddressLabel.setText("Домашний адрес : ");
        
        FillRegionComboBox();
        
        RegionComboBox.addActionListener((java.awt.event.ActionEvent evt) -> {
            RegionComboBoxActionPerformed(evt);
        });
        
        TownComboBox.addActionListener((ActionEvent evt) -> {
            TownComboBoxActionPerformed(evt);
        });
        
        VUSLabel.setText("Наименование учебного заведенения : ");
        
        PhoneLabel.setText("Телефон : ");
        
        PhoneTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PhoneTextFieldFocusLost(evt);
            }
        });
        
        PhoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });
        
        PhoneTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PhoneTextField1FocusLost(evt);
            }
        });
        
        PhoneTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });
        PhoneTextField1.setText("(XXX)XXXXXXX");
        PhoneTextField1.addFocusListener(new java.awt.event.FocusAdapter(){
           public void focusGained(java.awt.event.FocusEvent evt){
               PhoneTextField1FocusGained(evt);
           }
        });
        
        VUSTextField.addKeyListener(new java.awt.event.KeyAdapter() {
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
        
        CategoryLabel.setText("Категория :");
        
        FillCategoryList();
        GroupComboBox.addActionListener((ActionEvent evt) -> {
            CategoryComboBox_ActionPerformed(evt);
        });

        SalaryLabel.setText("Оклад : ");
        
        SalaryTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });
        
        HireLabel.setText("Дата приема на работу : ");

        WarningLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        WarningLabel1.setText("Все поля обязательны для заполнения");
        
        AddNewPerson_Button.setText("Сохранить");
        AddNewPerson_Button.addActionListener((java.awt.event.ActionEvent evt) -> {
            AddNewPerson_ActionPerformed(evt);
        });

        Cancel_Button.setText("Отмена");
        Cancel_Button.addActionListener((java.awt.event.ActionEvent evt) -> {
            Cancel_ButtonActionPerformed(evt);
        });

        typePhoneLabel.setText("городской : ");

        mobPhoneLabel.setText("мобильный : +38");   
        
        KonsultRadioButton.setText("Консультации");
        KonsultRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KonsultRadioButtonMouseClicked(evt);
            }
        });

        StateRadioButton.setText("Штат");
        StateRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StateRadioButtonMouseClicked(evt);
            }
        });
        
        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(WarningLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(AddNewPerson_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(Cancel_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CategoryLabel)
                                    .addComponent(SalaryLabel))
                                .addGap(18, 18, 18)
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SalaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(GroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(HireLabel)
                                .addGap(18, 18, 18)
                                .addComponent(HireDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(337, 337, 337))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(VUSTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(MainPanelLayout.createSequentialGroup()
                                        .addComponent(KonsultRadioButton)
                                        .addGap(53, 53, 53)
                                        .addComponent(StateRadioButton)
                                        .addGap(47, 47, 47)
                                        .addComponent(ProfLabel)
                                        .addGap(41, 41, 41)
                                        .addComponent(ProfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(MainPanelLayout.createSequentialGroup()
                                        .addComponent(LNameLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(LNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(MainPanelLayout.createSequentialGroup()
                                        .addComponent(AddressLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(RegionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(MainPanelLayout.createSequentialGroup()
                                        .addComponent(TownComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(AddressTextField))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(FNameLabel)
                                        .addGap(46, 46, 46)
                                        .addComponent(FNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(94, 94, 94)
                                        .addComponent(SNameLabel)
                                        .addGap(27, 27, 27)
                                        .addComponent(SNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28))))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(VUSLabel)
                                    .addGroup(MainPanelLayout.createSequentialGroup()
                                        .addGap(572, 572, 572)
                                        .addComponent(mobPhoneLabel)
                                        .addGap(30, 30, 30)
                                        .addComponent(PhoneTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(BirthDateLabel)
                        .addGap(29, 29, 29)
                        .addComponent(BirthDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(205, 205, 205)
                        .addComponent(SexLabel)
                        .addGap(57, 57, 57)
                        .addComponent(MenRadioButton)
                        .addGap(81, 81, 81)
                        .addComponent(WomenRadioButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addComponent(PhoneLabel)
                        .addGap(45, 45, 45)
                        .addComponent(typePhoneLabel)
                        .addGap(27, 27, 27)
                        .addComponent(kodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(516, 516, 516))))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FNameLabel)
                    .addComponent(FNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LNameLabel)
                    .addComponent(SNameLabel)
                    .addComponent(SNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RegionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TownComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BirthDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BirthDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SexLabel)
                    .addComponent(MenRadioButton)
                    .addComponent(WomenRadioButton))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PhoneLabel)
                        .addComponent(typePhoneLabel)
                        .addComponent(PhoneTextField)
                        .addComponent(mobPhoneLabel)
                        .addComponent(PhoneTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VUSLabel)
                    .addComponent(VUSTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CategoryLabel)
                    .addComponent(KonsultRadioButton)
                    .addComponent(StateRadioButton)
                    .addComponent(ProfLabel)
                    .addComponent(ProfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SalaryLabel)
                        .addComponent(SalaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(HireLabel))
                    .addComponent(HireDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WarningLabel1)
                    .addComponent(AddNewPerson_Button)
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
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru"));
        
        cal.roll(Calendar.YEAR, -17);
        Date presentDate = cal.getTime(); 
        
        Date bDay = null;
        
        String strDate = BirthDateTextField.getText();
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            bDay = format.parse(strDate);
        }
        catch(ParseException pe)
        {
            pe.printStackTrace();
        }
        
        if(presentDate.before(bDay))
        {
            JOptionPane.showMessageDialog(this, "Неправильная дата рождения ", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            AddNewPerson_Button.setEnabled(false);
            return;
        }
        
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
                    AddNewPerson_Button.setEnabled(false);                      // only 1,3,5,7,8,10,12 has 31 days
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
                            AddNewPerson_Button.setEnabled(false);
			}
                        else
                        {
                            AddNewPerson_Button.setEnabled(true);
			}
                    }
                    else
                    {
		        if(day.equals("29") || day.equals("30") || day.equals("31"))
                        {
                            JOptionPane.showMessageDialog(this, "Неправильное количество дней", 
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                            AddNewPerson_Button.setEnabled(false);
			}
                        else
                        {
                            AddNewPerson_Button.setEnabled(true);
			}
                    }
                }
                else
                {				 
                    AddNewPerson_Button.setEnabled(true);				 
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Неправильный формат даты рождения", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                AddNewPerson_Button.setEnabled(false);
            }		  
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Неправильный формат даты рождения", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            AddNewPerson_Button.setEnabled(false);
        }			    
    }
    
    //валидация городского номера
    private void PhoneTextFieldFocusLost(FocusEvent evt)
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
            AddNewPerson_Button.setEnabled(false);
        }
        else
            AddNewPerson_Button.setEnabled(true);     
    }
    
    //очистка поля мобильного телефона
    private void PhoneTextField1FocusGained(FocusEvent evt)
    {
        PhoneTextField1.setText("");        
    }    
    
    //валидация мобильного номера
    private void PhoneTextField1FocusLost(FocusEvent evt)
    {
        Pattern pattern = Pattern.compile("\\(\\d{3}\\)\\d{7}");
        Matcher matcher = pattern.matcher(PhoneTextField1.getText().trim());
        
        if(!matcher.matches())
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Неправильный формат мобильного номера телефона", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            AddNewPerson_Button.setEnabled(false);
        }
        else
            AddNewPerson_Button.setEnabled(true);     
    }

    //обработчик клика на радиобаттон
    private void WomenRadioButtonMouseClicked(MouseEvent evt) 
    {
        if(WomenRadioButton.isSelected())
        {
            sex = "Женский";
            MenRadioButton.setSelected(false);
        }       
    }

    //обработчик клика на радиобаттон
    private void MenRadioButtonMouseClicked(MouseEvent evt) 
    {
        if(MenRadioButton.isSelected())
        {
            sex = "Мужской";
            WomenRadioButton.setSelected(false);
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
            RegionComboBox.setModel(new DefaultComboBoxModel(sender.SendTextToServer("getRegions").split("/")));
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
        
        JComboBox box = (JComboBox)evt.getSource();
        String region = box.getSelectedItem().toString();
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //отправка данных на сервер
            townList = sender.SendTextToServer("getTowns_" + region).split("/");
            
            for(String town : townList)
            {
                String[] tmp = town.split(";");
                towns = towns + tmp[0] + ";";
            }
            
            TownComboBox.setModel(new DefaultComboBoxModel(towns.split(";")));
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
        JComboBox box = (JComboBox)evt.getSource();
        String town = box.getSelectedItem().toString();
        
        for(String str : townList)
        {
            String[] tmp = str.split(";");
            if(tmp[0].equals(town))
            {
                kodLabel.setText(tmp[1]);
                break;
            }
        } 
    }
    
    //заполнение комбобокса категориями персонала, полученными из базы данных
    private void FillCategoryList()
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //отправка данных на сервер и заполнение комбобокса со списком профессий
            GroupComboBox.setModel(new DefaultComboBoxModel(sender.SendTextToServer("getCategories").split("/")));
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
    
    //сохранение выбранной категории
    private void CategoryComboBox_ActionPerformed(ActionEvent evt) 
    {
        if(!GroupComboBox.getSelectedItem().equals("Санитарки"))
        {
            try
            {
                //проверка соединения с сервером
                if(sender.SendTextToServer("Hello").equals(""))
                {
                    sender.TryConnent();
                }

                //отправка на сервер логина и пароля для авторизации
                String answer = sender.SendTextToServer("getPass_" + LNameTextField.getText().trim());

                if(answer.equals("null"))
                {
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Создайте для данного работника пароль", 
                        "Внимание", JOptionPane.INFORMATION_MESSAGE);
                   new CreateNewLoginPass(parent, true, sender).setVisible(true);
                }
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
    }
    
    //обработчик клика по кнопке консультации
    private void KonsultRadioButtonMouseClicked(MouseEvent evt) 
    {
        if(KonsultRadioButton.isSelected())
        {
            category = "конс";
            StateRadioButton.setSelected(false);
        }        
    }

    //обработчик клика по кнопке штат
    private void StateRadioButtonMouseClicked(MouseEvent evt)
    {
        if(StateRadioButton.isSelected())
        {
            category = "Штат";
            KonsultRadioButton.setSelected(false);
        }
    }    
    
    //обработчик нажатия на кнопку сохранения в базе данных нового работника
    private void AddNewPerson_ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru"));
        Date presentDate = cal.getTime();
        
        if((HireDateChooser.getDate()).compareTo(presentDate) > 0)
        {   
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Ошибка выбора даты", "Неверная "
                        + "дата прием на работу", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        hireDate = sdf.format(HireDateChooser.getDate());
        disDate = "-";
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            if(LNameTextField.getText().trim().equals("") 
                    || FNameTextField.getText().trim().equals("")
                    || SNameTextField.getText().trim().equals("") 
                    || BirthDateTextField.getText().trim().equals("")
                    || sex.equals("") || RegionComboBox.getSelectedItem().equals("")
                    || TownComboBox.getSelectedItem().equals("")
                    || AddressTextField.getText().trim().equals("") 
                    || PhoneTextField.getText().trim().equals("")
                    || PhoneTextField1.getText().trim().equals("")
                    || GroupComboBox.getSelectedItem().equals("")
                    || category.equals("")
                    || ProfTextField.getText().trim().equals("")
                    || SalaryTextField.getText().trim().equals("")
                    || hireDate.equals(""))
            {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Заполните все поля", 
                    "Ошибка ввода данных", JOptionPane.ERROR_MESSAGE);
            }
            else
                CreateNewPerson();
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

    //обработчик нажатия на кнопку отмены
    private void Cancel_ButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        this.dispose();
    }  
    
    //сохранение данных в базе 
    private void CreateNewPerson()
    {
        String adress = RegionComboBox.getSelectedItem().toString()+ ","
                + TownComboBox.getSelectedItem().toString() + "," + AddressTextField.getText().trim();
                
        String param = LNameTextField.getText().trim() + "#" + FNameTextField.getText().trim()
                + "#" + SNameTextField.getText().trim() + "#" + BirthDateTextField.getText().trim()
                + "#" + sex + "#" + adress + "#" + kodLabel.getText() + "," + PhoneTextField.getText().trim() 
                + "#" + PhoneTextField1.getText().trim() + "#" + VUSTextField.getText().trim() 
                + "#" + ProfTextField.getText().trim() + "#" + category + "#" +  SalaryTextField.getText().trim()
                + "#" + hireDate + "#" + disDate + "#" + GroupComboBox.getSelectedItem();
        
        String answer = sender.SendTextToServer("addPerson_" + param);
                        
        switch (answer)
        {
            case "OK":
                JOptionPane.showMessageDialog(this, "Данные внесены в базу", 
                    "Внимание", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                break;
            case "NO":
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Ошибка внесения данных в базу", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                break;
            case "login_id":
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Создайте логин и пароль для нового работника", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                break;
            case "group_id":
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, " Выбранная категория отсутствует в базе", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}
