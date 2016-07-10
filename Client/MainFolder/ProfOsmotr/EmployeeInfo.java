package umec.ProfOsmotr;

import TCPConnect.TCPSender;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.ConnectException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmployeeInfo extends javax.swing.JDialog 
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private javax.swing.JLabel AllRiskLabel;
    private javax.swing.JLabel BirthDayLabel;
    private javax.swing.JTextField BirthDayTextField;
    private javax.swing.JButton CancelButton;
    private javax.swing.JComboBox CompanyNameComboBox;
    private javax.swing.JLabel CountLabel;
    private javax.swing.JLabel CountLabel1;
    private javax.swing.JTextField CountTextField;
    private javax.swing.JTextField CountTextField1;
    private javax.swing.JLabel DivisionLabel;
    private javax.swing.JTextField DivisionTextField;
    private javax.swing.JLabel DocListLabel;
    private javax.swing.JTextField DocListTextField;
    private javax.swing.JLabel ExamLabel;
    private javax.swing.JTextField ExamListTextField;
    private javax.swing.JLabel FIOLabel;
    private javax.swing.JTextField FIOTextField;
    private javax.swing.JLabel LisrLabel;
    private javax.swing.JLabel ListReasonLabel;
    private javax.swing.JLabel LivingLabel;
    private javax.swing.JTextField LivingTextField;
    private javax.swing.JLabel MainLabel;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JRadioButton MenRadioButton;
    private javax.swing.JLabel PhoneLabel;
    private javax.swing.JTextField PhoneTextField;
    private javax.swing.JLabel ProfLabel;
    private javax.swing.JTextField ProfTextField;
    private javax.swing.JLabel ReasonLabel;
    private javax.swing.JTextField ReasonTextField;
    private javax.swing.JLabel RiskLabel;
    private javax.swing.JScrollPane RiskScrollPane;
    private javax.swing.JTextArea RiskTextArea;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel SexLabel;
    private javax.swing.JLabel TimeLabel;
    private javax.swing.JTextField TimeTextField;
    private javax.swing.JRadioButton WomenRadioButton;
    private javax.swing.JLabel WorkLabel;
    private TCPSender sender;
    private String companyName;
    
//</editor-fold>
    
    public EmployeeInfo(javax.swing.JDialog parent, boolean modal, TCPSender sn) 
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
        this.setLocation((screenSize.width - frameSize.width) / 600, 
                (screenSize.height - frameSize.height) / 14);
        
        //создание окна без рамки
        this.setUndecorated(true);

        
        MainPanel = new javax.swing.JPanel();
        MainLabel = new javax.swing.JLabel();
        FIOLabel = new javax.swing.JLabel();
        FIOTextField = new javax.swing.JTextField();
        SexLabel = new javax.swing.JLabel();
        WomenRadioButton = new javax.swing.JRadioButton();
        MenRadioButton = new javax.swing.JRadioButton();
        BirthDayLabel = new javax.swing.JLabel();
        BirthDayTextField = new javax.swing.JTextField();
        LivingTextField = new javax.swing.JTextField();
        LivingLabel = new javax.swing.JLabel();
        PhoneLabel = new javax.swing.JLabel();
        PhoneTextField = new javax.swing.JTextField();
        WorkLabel = new javax.swing.JLabel();
        CompanyNameComboBox = new javax.swing.JComboBox();
        DivisionLabel = new javax.swing.JLabel();
        DivisionTextField = new javax.swing.JTextField();
        ProfLabel = new javax.swing.JLabel();
        ProfTextField = new javax.swing.JTextField();
        RiskLabel = new javax.swing.JLabel();
        RiskScrollPane = new javax.swing.JScrollPane();
        RiskTextArea = new javax.swing.JTextArea();
        ReasonLabel = new javax.swing.JLabel();
        ReasonTextField = new javax.swing.JTextField();
        AllRiskLabel = new javax.swing.JLabel();
        ListReasonLabel = new javax.swing.JLabel();
        TimeLabel = new javax.swing.JLabel();
        TimeTextField = new javax.swing.JTextField();
        CountLabel = new javax.swing.JLabel();
        CountTextField = new javax.swing.JTextField();
        CountLabel1 = new javax.swing.JLabel();
        CountTextField1 = new javax.swing.JTextField();
        DocListLabel = new javax.swing.JLabel();
        DocListTextField = new javax.swing.JTextField();
        ExamLabel = new javax.swing.JLabel();
        ExamListTextField = new javax.swing.JTextField();
        LisrLabel = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        MainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        MainLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        MainLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MainLabel.setText("Карточка работника, который подлежит предварительному (периодическому) медицинскому осмотру");
        MainLabel.setFocusTraversalPolicyProvider(true);
        MainLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        FIOLabel.setText("Фамилия И.О.");

        SexLabel.setText("Пол :");

        WomenRadioButton.setText("Женский");

        MenRadioButton.setText("Мужской");

        BirthDayLabel.setText("Дата рождения :");

        LivingLabel.setText("Место жительства :");

        PhoneLabel.setText("Телефон :");

        WorkLabel.setText("Место работы(название предприятия) :");

        CompanyNameComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CompanyNameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompanyNameComboBoxActionPerformed(evt);
            }
        });

        DivisionLabel.setText("Цех, подразделение :");

        ProfLabel.setText("Профессия (должность) по ДК 003:2010 :");

        RiskLabel.setText("Вредные и опасные производственные факторы и трудовые процессы :");

        RiskTextArea.setColumns(20);
        RiskTextArea.setRows(2);
        RiskScrollPane.setViewportView(RiskTextArea);

        ReasonLabel.setText("Основание для предварительного (периодического) медосмотра ");

        AllRiskLabel.setText("(перечислить все факторы)");

        ListReasonLabel.setText("(указать конкретные пункты приложений 4, 5)");

        TimeLabel.setText("Подлежит осмотру ");

        CountLabel.setText("раз");

        CountLabel1.setText("в");

        DocListLabel.setText("Специалистами (докторами) ");

        ExamLabel.setText("Лабораторные, функциональные и иные обследования : ");

        LisrLabel.setText("(перечислить)");

        SaveButton.setText("Сохранить");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Отмена");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
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
                                .addComponent(FIOLabel)
                                .addGap(18, 18, 18)
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(MainPanelLayout.createSequentialGroup()
                                        .addComponent(MainLabel)
                                        .addGap(0, 76, Short.MAX_VALUE))
                                    .addGroup(MainPanelLayout.createSequentialGroup()
                                        .addComponent(FIOTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SexLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(WomenRadioButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(MenRadioButton)
                                        .addGap(70, 70, 70)
                                        .addComponent(BirthDayLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(BirthDayTextField)
                                            .addComponent(PhoneTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(ProfLabel)
                                .addGap(18, 18, 18)
                                .addComponent(ProfTextField))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(WorkLabel)
                                .addGap(18, 18, 18)
                                .addComponent(CompanyNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(DivisionLabel)
                                .addGap(18, 18, 18)
                                .addComponent(DivisionTextField)))
                        .addContainerGap())
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(LivingLabel)
                                .addGap(18, 18, 18)
                                .addComponent(LivingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(PhoneLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(RiskLabel)
                                .addGap(18, 18, 18)
                                .addComponent(RiskScrollPane)))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addComponent(ReasonLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ReasonTextField)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addComponent(TimeLabel)
                        .addGap(18, 18, 18)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGap(0, 413, Short.MAX_VALUE)
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                                        .addComponent(AllRiskLabel)
                                        .addGap(191, 191, 191))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                                        .addComponent(ListReasonLabel)
                                        .addGap(171, 171, 171))))
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addComponent(TimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CountLabel)
                                .addGap(18, 18, 18)
                                .addComponent(CountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CountLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(CountTextField1)
                                .addContainerGap())))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(DocListLabel)
                        .addGap(18, 18, 18)
                        .addComponent(DocListTextField)
                        .addContainerGap())
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(ExamLabel)
                        .addGap(18, 18, 18)
                        .addComponent(ExamListTextField)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LisrLabel)
                .addGap(247, 247, 247))
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainLabel)
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FIOLabel)
                    .addComponent(FIOTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SexLabel)
                    .addComponent(WomenRadioButton)
                    .addComponent(MenRadioButton)
                    .addComponent(BirthDayLabel)
                    .addComponent(BirthDayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LivingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LivingLabel)
                    .addComponent(PhoneLabel)
                    .addComponent(PhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WorkLabel)
                    .addComponent(CompanyNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DivisionLabel)
                    .addComponent(DivisionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProfLabel)
                    .addComponent(ProfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(RiskScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(RiskLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AllRiskLabel)
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ReasonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReasonLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListReasonLabel)
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimeLabel)
                    .addComponent(CountLabel)
                    .addComponent(CountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CountLabel1)
                    .addComponent(CountTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DocListLabel)
                    .addComponent(DocListTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ExamLabel)
                    .addComponent(ExamListTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LisrLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(CancelButton)))
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

        FillCompanyNameComboBox();
        
        pack();
    }// </editor-fold>                        

    private void FillCompanyNameComboBox()
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            String[] companies = sender.SendTextToServer("getCompanyList").split(";");
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            for (String value : companies)
            {
                String[] company = value.split("-");
                model.addElement(company[0]);
            }
            
            CompanyNameComboBox.setModel(model);
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
    
    private void CompanyNameComboBoxActionPerformed(java.awt.event.ActionEvent evt)
    {                                                    
        JComboBox box = (JComboBox)evt.getSource();
        companyName = box.getSelectedItem().toString();
    }                                                   

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {                                           
        // TODO add your handling code here:
    }                                          
    
    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {                                             
        this.dispose();
    }                                            
}