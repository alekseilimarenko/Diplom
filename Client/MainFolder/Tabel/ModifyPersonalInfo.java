package umec.Tabel;

import TCPConnect.TCPSender;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.net.ConnectException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ModifyPersonalInfo extends JDialog 
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JTextField AddressTextField;
    private javax.swing.JLabel BirthDateLabel;
    private javax.swing.JTextField BirthDateTextField;
    private javax.swing.JButton Cancel_Button;
    private javax.swing.JComboBox CategoryComboBox;
    private javax.swing.JLabel CategoryLabel;
    private javax.swing.JLabel DismissLabel;
    private javax.swing.JTextField DismissTextField;
    private javax.swing.JLabel FNameLabel;
    private javax.swing.JTextField FNameTextField;
    private javax.swing.JLabel HireLabel;
    private javax.swing.JTextField HireTextField;
    private javax.swing.JRadioButton KonsultRadioButton;
    private javax.swing.JLabel LNameLabel;
    private javax.swing.JLabel LNameSearchLabel;
    private javax.swing.JTextField LNameTextField;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JRadioButton MenRadioButton;
    private javax.swing.JList PersonalList;
    private javax.swing.JLabel PhoneLabel;
    private javax.swing.JLabel ProfLabel;
    private javax.swing.JTextField ProfTextField;
    private javax.swing.JLabel SNameLabel;
    private javax.swing.JTextField SNameTextField;
    private javax.swing.JLabel SalaryLabel;
    private javax.swing.JTextField SalaryTextField;
    private javax.swing.JButton Save_Button;
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JLabel SexLabel;
    private javax.swing.JRadioButton StateRadioButton;
    private javax.swing.JLabel VUSLabel;
    private javax.swing.JTextField VUSTextField;
    private javax.swing.JRadioButton WomenRadioButton;
    private javax.swing.JLabel cityPhoneLabel;
    private javax.swing.JTextField cityPhoneTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel kodLabel;
    private javax.swing.JLabel mobPhoneLabel;
    private javax.swing.JTextField mobPhoneTextField;
    private javax.swing.JComboBox<String> regionComboBox;
    private javax.swing.JComboBox<String> townComboBox;
    private TCPSender sender;
    private javax.swing.JComboBox Category, Region, Town;
    private String[] personInfo = null, townList;
    private String RadioButton = "", category = "";
    private String сategoryState = "", regionState = "", townState = "";
//</editor-fold>
    
    //конструктор формы
    public ModifyPersonalInfo(java.awt.Frame parent, boolean modal, TCPSender sn)
    {
        super(parent, modal);
        this.sender = sn;
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Создание компонентов окна">  
    private void initComponents()
    {
        SearchPanel = new javax.swing.JPanel();
        LNameSearchLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PersonalList = new javax.swing.JList();
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
        cityPhoneTextField = new javax.swing.JTextField();
        Save_Button = new javax.swing.JButton();
        Cancel_Button = new javax.swing.JButton();
        VUSLabel = new javax.swing.JLabel();
        VUSTextField = new javax.swing.JTextField();
        HireLabel = new javax.swing.JLabel();
        HireTextField = new javax.swing.JTextField();
        DismissLabel = new javax.swing.JLabel();
        DismissTextField = new javax.swing.JTextField();
        SalaryLabel = new javax.swing.JLabel();
        SalaryTextField = new javax.swing.JTextField();
        WomenRadioButton = new javax.swing.JRadioButton();
        MenRadioButton = new javax.swing.JRadioButton();
        CategoryLabel = new javax.swing.JLabel();
        CategoryComboBox = new javax.swing.JComboBox();
        cityPhoneLabel = new javax.swing.JLabel();
        mobPhoneLabel = new javax.swing.JLabel();
        mobPhoneTextField = new javax.swing.JTextField();
        regionComboBox = new javax.swing.JComboBox<>();
        townComboBox = new javax.swing.JComboBox<>();
        kodLabel = new javax.swing.JLabel();
        KonsultRadioButton = new javax.swing.JRadioButton();
        StateRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Внесение изменений в личную карточку");

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
        
        SearchPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        LNameSearchLabel.setText("Выберите фамилию :");
        PersonalList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                PersonalListMouseClicked(evt);
            }
        });
        FillPersonList();
        
        jScrollPane1.setViewportView(PersonalList);

        javax.swing.GroupLayout SearchPanelLayout = new javax.swing.GroupLayout(SearchPanel);
        SearchPanel.setLayout(SearchPanelLayout);
        SearchPanelLayout.setHorizontalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(LNameSearchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addContainerGap())
        );
        SearchPanelLayout.setVerticalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LNameSearchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

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
        BirthDateTextField.setEditable(false);
        
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
        
        PhoneLabel.setText("Телефон : ");
        
        cityPhoneLabel.setText("городской:");

        cityPhoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });
        
        cityPhoneTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PhoneTextFieldFocusLost(evt);
            }
        });
        
        mobPhoneLabel.setText("мобильный: +38");
        
        mobPhoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });
        
        mobPhoneTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PhoneTextField1FocusLost(evt);
            }
        });

        VUSLabel.setText("Наименование учебного заведенения : ");
        
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
        
        CategoryLabel.setText("Категория : ");
        
        FillCategoryList();
        CategoryComboBox.addActionListener((ActionEvent evt) -> {
            CategoryComboBox_ActionPerformed(evt);
        });

        SalaryLabel.setText("Оклад : ");
        
        SalaryTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });
        
        HireLabel.setText("Дата приема на работу : ");
        HireTextField.setEditable(false);

        DismissLabel.setText("Дата увольнения : ");
        
        DismissTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextField1KeyTyped(evt);
            }
        });

        Save_Button.setText("Сохранить");
        Save_Button.addActionListener((java.awt.event.ActionEvent evt) -> {
            Save_ButtonActionPerformed(evt);
        });

        Cancel_Button.setText("Отмена");
        Cancel_Button.addActionListener((java.awt.event.ActionEvent evt) -> {
            Cancel_ButtonActionPerformed(evt);
        });

        FillRegionList();
        regionComboBox.addActionListener((ActionEvent evt) ->{
            RegionComboBox_ActionPerformed(evt);
        });

        townComboBox.addActionListener((ActionEvent evt) -> {
            TownComboBox_ActionPerformed(evt); 
        });
        
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Save_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Cancel_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(BirthDateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BirthDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SexLabel)
                                .addGap(35, 35, 35)
                                .addComponent(MenRadioButton)
                                .addGap(40, 40, 40))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(PhoneLabel)
                                .addGap(30, 30, 30)
                                .addComponent(cityPhoneLabel)
                                .addGap(18, 18, 18)
                                .addComponent(kodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cityPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mobPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(WomenRadioButton)
                            .addComponent(mobPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(SalaryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SalaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(HireLabel)
                        .addGap(0, 0, 0)
                        .addComponent(HireTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(DismissLabel)
                        .addGap(18, 18, 18)
                        .addComponent(DismissTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(CategoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(KonsultRadioButton)
                                .addGap(34, 34, 34)
                                .addComponent(StateRadioButton)
                                .addGap(37, 37, 37)
                                .addComponent(ProfLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(ProfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, MainPanelLayout.createSequentialGroup()
                                .addComponent(VUSLabel)
                                .addGap(18, 18, 18)
                                .addComponent(VUSTextField))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(LNameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(LNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(FNameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(FNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SNameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(SNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(AddressLabel)
                                .addGap(18, 18, 18)
                                .addComponent(regionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(townComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(AddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FNameLabel)
                    .addComponent(FNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SNameLabel)
                    .addComponent(LNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LNameLabel)
                    .addComponent(SNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(AddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(regionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(townComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SexLabel)
                    .addComponent(BirthDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BirthDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WomenRadioButton)
                    .addComponent(MenRadioButton))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kodLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PhoneLabel)
                        .addComponent(cityPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cityPhoneLabel)
                        .addComponent(mobPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mobPhoneLabel)))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VUSLabel)
                    .addComponent(VUSTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CategoryLabel)
                    .addComponent(KonsultRadioButton)
                    .addComponent(StateRadioButton)
                    .addComponent(ProfLabel))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SalaryLabel)
                    .addComponent(SalaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HireLabel)
                    .addComponent(HireTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DismissTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DismissLabel))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addComponent(SearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        pack();
    }// </editor-fold>     
    
    //контроль вводимых букв
    private void TextFieldKeyTyped(java.awt.event.KeyEvent evt) 
    {
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c) || (c == java.awt.event.KeyEvent.VK_DELETE))
        {
            getToolkit().beep();
            evt.consume();
        }
    }    
    
    //проверка вводимых цифр
    private void TextField1KeyTyped(java.awt.event.KeyEvent evt) 
    {
        char c = evt.getKeyChar();
        
        if((c == (char)'/') || (c == (char)'+') || (c == (char)'(') || (c == (char)')'))
        {
            return;
        }
        
        if(!Character.isDigit(c) || (c == java.awt.event.KeyEvent.VK_BACK_SPACE) || (c == java.awt.event.KeyEvent.VK_DELETE) )
        {
            getToolkit().beep();
            evt.consume();
        }
    }
    
    //проверка вводимого городского номера
    private void PhoneTextFieldFocusLost(FocusEvent evt) 
    {
        String kod = kodLabel.getText().substring(kodLabel.getText().lastIndexOf(')') + 1, kodLabel.getText().lastIndexOf('X')); 
        String num = cityPhoneTextField.getText();
        
        String numPattern = "\\d{" + kod.length() + "}";
        
        Pattern pattern = Pattern.compile(numPattern);
        Matcher matcher = pattern.matcher(num);
        
        if(!matcher.matches())
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Неправильный формат городского номера телефона", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            Save_Button.setEnabled(false);
        }
        else
            Save_Button.setEnabled(true);     
    }

    //проверка вводимого мобильного номера
    private void PhoneTextField1FocusLost(FocusEvent evt) 
    {
        String numPattern = "\\(\\d{3}\\)\\d{7}";
        String phone = mobPhoneTextField.getText().trim();
        
        Pattern pattern = Pattern.compile(numPattern);
        Matcher matcher = pattern.matcher(phone);
        
        if(!matcher.matches())
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Неправильный формат мобильного номера телефона", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            Save_Button.setEnabled(false);
        }
        else
            Save_Button.setEnabled(true);     
    }

    //обработчик клика по кнопке консультации
    private void KonsultRadioButtonMouseClicked(MouseEvent evt) 
    {
       if(KonsultRadioButton.isSelected())
        {
            personInfo[11] = "конс";
            StateRadioButton.setSelected(false);
        }        
    }

    //обработчик клика по кнопке штат
    private void StateRadioButtonMouseClicked(MouseEvent evt)
    {
        if(StateRadioButton.isSelected())
        {
            personInfo[11] = "Штат";
            KonsultRadioButton.setSelected(false);
        } 
    }

    //заполнение списка работников
    private void FillPersonList()
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            String[] lNames = sender.SendTextToServer("getLName_All").split("/");
            
            String empl = "";
            
            for(String lName : lNames )
            {
                String[] tmp = lName.split("-");
                empl = empl + tmp[0] + ";";
            }
            
            final String[] str = empl.split(";");
            
            //отправка запроса на сервер для заполнения jList со списком профессий
            PersonalList.setModel(new javax.swing.AbstractListModel()
            {
                String[] strings = str;
                public int getSize() { return strings.length; }
                public Object getElementAt(int i) { return strings[i]; }
            });
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
    
    //обработчик клика на чекбокса
    private void WomenRadioButtonMouseClicked(MouseEvent evt) 
    {
        if(WomenRadioButton.isSelected())
        {
            MenRadioButton.setSelected(false);
            personInfo[5] = "Женский";
        }       
    }
    
    //обработчик клика на чекбокса
    private void MenRadioButtonMouseClicked(MouseEvent evt) 
    {
        if(MenRadioButton.isSelected())
        {
            WomenRadioButton.setSelected(false);
            personInfo[5] = "Мужской";
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
            
            //отправка данных на сервер и заполнение комбобокса со списком категорий
            CategoryComboBox.setModel(new DefaultComboBoxModel(sender.SendTextToServer("getCategories").split("/")));
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
    
    //заполнение списка названий областей
    private void FillRegionList()
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
    
    //сохранение выбранной области
    private void RegionComboBox_ActionPerformed(ActionEvent evt)
    {
        Region = (javax.swing.JComboBox)evt.getSource();
        regionState = Region.getSelectedItem().toString();
        
        String towns = "";
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //отправка данных на сервер
            townList = sender.SendTextToServer("getTowns_" + regionState).split("/");
            
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
    
    //сохранение выбранного города
    private void TownComboBox_ActionPerformed(ActionEvent evt)
    {
        Town = (javax.swing.JComboBox)evt.getSource();
        townState = Town.getSelectedItem().toString();
        
        for(String str : townList)
        {
            String[] tmp = str.split(";");
            if(tmp[0].equals(townState))
            {
                kodLabel.setText(tmp[1]);
                break;
            }
        } 
    }
    
    //сохранение выбранной категории
    private void CategoryComboBox_ActionPerformed(ActionEvent evt) 
    {
        Category = (javax.swing.JComboBox)evt.getSource();
        personInfo[14] =  Category.getSelectedItem().toString();
    }
    
    //обработчик выбора в списке персонала
    private void PersonalListMouseClicked(MouseEvent evt) 
    {
        //String person = (String) PersonalList.getSelectedValue();
        //System.out.println(person);
        
        String towns = "";
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            String answer = sender.SendTextToServer("getPersonInfo_" + (String)PersonalList.getSelectedValue());
        
            if(!answer.equals("null"))
            {
                //Запорожская область, Запорожье, иииииииииииииииии
                personInfo = answer.split("#");
                
                String[] tmpAdr = personInfo[6].split(",");
                String[] tmpPhone = personInfo[7].split(",");
                
                //отправка данных на сервер для получения списка городов
                String[] townList = sender.SendTextToServer("getTowns_" + tmpAdr[0]).split("/");
            
                for(String town : townList)
                {
                    String[] tmp = town.split(";");
                    towns = towns + tmp[0] + ";";
                }
            
                townComboBox.setModel(new DefaultComboBoxModel(towns.split(";")));
                                
                LNameTextField.setText(personInfo[1]);
                FNameTextField.setText(personInfo[2]);
                SNameTextField.setText(personInfo[3]);
                BirthDateTextField.setText(personInfo[4]);
                regionComboBox.setSelectedItem(tmpAdr[0]);
                townComboBox.setSelectedItem(tmpAdr[1]);
                AddressTextField.setText(tmpAdr[2]);
                kodLabel.setText(tmpPhone[0]);
                cityPhoneTextField.setText(tmpPhone[1]);
                mobPhoneTextField.setText(personInfo[8]);
                VUSTextField.setText(personInfo[9]);
                ProfTextField.setText(personInfo[10]);
                SalaryTextField.setText(personInfo[12]);
                HireTextField.setText(personInfo[13]);
                DismissTextField.setText(personInfo[14]);
                CategoryComboBox.setSelectedItem(personInfo[15]);

                if(personInfo[5].equals("Мужской"))
                {
                    MenRadioButton.setSelected(true);
                    WomenRadioButton.setSelected(false);
                }
                else
                {
                    WomenRadioButton.setSelected(true);
                    MenRadioButton.setSelected(false);
                } 
                if(personInfo[11].equals("конс"))
                {
                    KonsultRadioButton.setSelected(true);
                    StateRadioButton.setSelected(false);
                }        
                else
                {
                    StateRadioButton.setSelected(true);
                    KonsultRadioButton.setSelected(false);
                }
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

    //обработчик нажатия на кнопку сохранения внесенных данных
    private void Save_ButtonActionPerformed(ActionEvent evt) 
    {
        VerifyDateInput();
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            SaveData();
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
    
    //проверка введенной даты
    private void VerifyDateInput()
    {
        String date = DismissTextField.getText();
        
        if(date.equals("-"))
            return;
        
        String Date_Pattern = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
        
        Pattern pattern = Pattern.compile(Date_Pattern);
        
        Matcher matcher = pattern.matcher(date);
        

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
                    Save_Button.setEnabled(false);                      // only 1,3,5,7,8,10,12 has 31 days
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
                            Save_Button.setEnabled(false);
			}
                        else
                        {
                            Save_Button.setEnabled(true);
			}
                    }
                    else
                    {
		        if(day.equals("29") || day.equals("30") || day.equals("31"))
                        {
                            JOptionPane.showMessageDialog(this, "Неправильное количество дней", 
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                            Save_Button.setEnabled(false);
			}
                        else
                        {
                            Save_Button.setEnabled(true);
			}
                    }
                }
                else
                {				 
                    Save_Button.setEnabled(true);				 
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Неправильный формат даты", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                Save_Button.setEnabled(false);
            }		  
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Неправильный формат даты", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            Save_Button.setEnabled(false);
        }			
    }
    
    //обработчик нажатия на кнопку отмены
    private void Cancel_ButtonActionPerformed(ActionEvent evt) 
    {
        this.dispose();
    }
    
    //функция сохранения данных на сервере
    private void SaveData()
    {
        String address = regionState + "," + townState + "," + AddressTextField.getText();
        
        String str = "updatePersonInfo_" + personInfo[0] + "#" + 
                LNameTextField.getText().trim() + "#" + FNameTextField.getText().trim()+ "#" + 
                SNameTextField.getText().trim() + "#" + BirthDateTextField.getText().trim() + "#" + 
                personInfo[5] + "#" + address + "#" + kodLabel.getText() + "," + cityPhoneTextField.getText() + "#" +
                mobPhoneTextField.getText().trim() + "#" + VUSTextField.getText().trim() + "#" + 
                ProfTextField.getText().trim() + "#" + personInfo[11] + "#" + SalaryTextField.getText().trim() + "#" + 
                HireTextField.getText().trim() + "#" + DismissTextField.getText().trim() + "#" + 
                personInfo[15];
        
        String answer = sender.SendTextToServer(str);

        if(answer.equals("OK"))
        {
            JOptionPane.showMessageDialog(this, "Сведения успешно обновлены", 
                    "Внимание", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();      
        }
        else
            JOptionPane.showMessageDialog(this, "Ошибка обновления данных", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}