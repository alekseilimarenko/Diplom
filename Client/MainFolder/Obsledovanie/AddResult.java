package umec.Obsledovanie;

import TCPConnect.TCPSender;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class AddResult extends javax.swing.JDialog 
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private javax.swing.JPanel AddResultPanel;
    private javax.swing.JTabbedPane AddResultTabbedPane;
    private javax.swing.JButton CancelAddNewVidButton;
    private javax.swing.JButton CancelResultButton;
    private com.toedter.calendar.JDateChooser DateChooser;
    private javax.swing.JLabel DateObsledLabel;
    private javax.swing.JRadioButton ExplainRadioButton;
    private javax.swing.JScrollPane ExplainScrollPane;
    private javax.swing.JTextArea ExplainTextArea;
    private javax.swing.JButton FileChooserButton;
    private javax.swing.JTextField FilePathTextField;
    private javax.swing.JRadioButton FileRadioButton;
    private javax.swing.JRadioButton InstrumentalRadioButton;
    private javax.swing.JRadioButton InstrumentalRadioButton1;
    private javax.swing.JScrollPane LNameScrollPane;
    private javax.swing.JTable LNameTable;
    private javax.swing.JRadioButton LaboratoryRadioButton;
    private javax.swing.JRadioButton LaboratoryRadioButton1;
    private javax.swing.JPanel NewITemPanel;
    private javax.swing.JPanel NewVidPanel;
    private javax.swing.JLabel ResultObsledLabel;
    private javax.swing.JPanel ResultTestingPanel;
    private javax.swing.JButton SaveNewVidButton;
    private javax.swing.JButton SaveResultButton;
    private javax.swing.JLabel TypeLabel;
    private javax.swing.JScrollPane Vid1ScrollPane;
    private javax.swing.JLabel VidLabel;
    private javax.swing.JList VidList;
    private javax.swing.JScrollPane VidScrollPane;
    private javax.swing.JLabel newvidLabel;
    private javax.swing.JTextField newvidTextField;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JLabel vidLabel;
    private javax.swing.JList vidList;
    private SimpleDateFormat sdf;
    private TCPSender sender;
    private String lName, bDay, typeTesting, vidTesting = "", dateObsled = "";
    private File file;
    private Date date;
//</editor-fold>

    //конструктор окна
    public AddResult(java.awt.Frame parent, boolean modal, TCPSender sn)
    {
        super(parent, modal);
        this.sender = sn;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Создание компоненттов окна">                          
    private void initComponents() 
    {
        setTitle("Внесение результатов обследования");
        
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
        this.setLocation((screenSize.width - frameSize.width) / 5, 
                (screenSize.height - frameSize.height) / 5);
        
        AddResultTabbedPane = new javax.swing.JTabbedPane();
        NewVidPanel = new javax.swing.JPanel();
        NewITemPanel = new javax.swing.JPanel();
        vidLabel = new javax.swing.JLabel();
        VidScrollPane = new javax.swing.JScrollPane();
        vidList = new javax.swing.JList();
        typeLabel = new javax.swing.JLabel();
        InstrumentalRadioButton = new javax.swing.JRadioButton();
        LaboratoryRadioButton = new javax.swing.JRadioButton();
        newvidLabel = new javax.swing.JLabel();
        newvidTextField = new javax.swing.JTextField();
        SaveNewVidButton = new javax.swing.JButton();
        CancelAddNewVidButton = new javax.swing.JButton();
        AddResultPanel = new javax.swing.JPanel();
        ResultTestingPanel = new javax.swing.JPanel();
        DateObsledLabel = new javax.swing.JLabel();
        VidLabel = new javax.swing.JLabel();
        Vid1ScrollPane = new javax.swing.JScrollPane();
        VidList = new javax.swing.JList();
        TypeLabel = new javax.swing.JLabel();
        InstrumentalRadioButton1 = new javax.swing.JRadioButton();
        LaboratoryRadioButton1 = new javax.swing.JRadioButton();
        FileChooserButton = new javax.swing.JButton();
        SaveResultButton = new javax.swing.JButton();
        CancelResultButton = new javax.swing.JButton();
        ResultObsledLabel = new javax.swing.JLabel();
        LNameScrollPane = new javax.swing.JScrollPane();
        LNameTable = new javax.swing.JTable();
        FilePathTextField = new javax.swing.JTextField();
        ExplainRadioButton = new javax.swing.JRadioButton();
        FileRadioButton = new javax.swing.JRadioButton();
        ExplainScrollPane = new javax.swing.JScrollPane();
        ExplainTextArea = new javax.swing.JTextArea();
        DateChooser = new com.toedter.calendar.JDateChooser();
        sdf = new SimpleDateFormat("dd-MM-yyyy");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        NewITemPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        vidLabel.setText("Виды обследований:");

        vidList.setEnabled(false);
        VidScrollPane.setViewportView(vidList);

        typeLabel.setText("Выберите тип обследования : ");

        InstrumentalRadioButton.setText("Инструментальные");
        InstrumentalRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InstrumentalRadioButtonMouseClicked(evt);
            }
        });

        LaboratoryRadioButton.setText("Лабораторные");
        LaboratoryRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LaboratoryRadioButtonMouseClicked(evt);
            }
        });

        newvidLabel.setText("Введите название нового обследования :");
        
        newvidTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextFieldKeyTyped(evt);
            }
        });

        SaveNewVidButton.setText("Сохранить");
        SaveNewVidButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveNewVidButtonActionPerformed(evt);
            }
        });

        CancelAddNewVidButton.setText("Отмена");
        CancelAddNewVidButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelAddNewVidButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NewITemPanelLayout = new javax.swing.GroupLayout(NewITemPanel);
        NewITemPanel.setLayout(NewITemPanelLayout);
        NewITemPanelLayout.setHorizontalGroup(
            NewITemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewITemPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(NewITemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeLabel)
                    .addComponent(InstrumentalRadioButton)
                    .addComponent(LaboratoryRadioButton))
                .addGap(18, 18, 18)
                .addGroup(NewITemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NewITemPanelLayout.createSequentialGroup()
                        .addComponent(VidScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(NewITemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NewITemPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(newvidTextField))
                            .addGroup(NewITemPanelLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(SaveNewVidButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CancelAddNewVidButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(NewITemPanelLayout.createSequentialGroup()
                        .addComponent(vidLabel)
                        .addGap(48, 48, 48)
                        .addComponent(newvidLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(35, 35, 35))
        );
        NewITemPanelLayout.setVerticalGroup(
            NewITemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewITemPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(NewITemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel)
                    .addComponent(newvidLabel)
                    .addComponent(vidLabel))
                .addGroup(NewITemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NewITemPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(VidScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(NewITemPanelLayout.createSequentialGroup()
                        .addGroup(NewITemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NewITemPanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(InstrumentalRadioButton)
                                .addGap(33, 33, 33)
                                .addComponent(LaboratoryRadioButton))
                            .addGroup(NewITemPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(newvidTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(NewITemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SaveNewVidButton)
                            .addComponent(CancelAddNewVidButton))
                        .addGap(15, 15, 15))))
        );

        javax.swing.GroupLayout NewVidPanelLayout = new javax.swing.GroupLayout(NewVidPanel);
        NewVidPanel.setLayout(NewVidPanelLayout);
        NewVidPanelLayout.setHorizontalGroup(
            NewVidPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewVidPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NewITemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        NewVidPanelLayout.setVerticalGroup(
            NewVidPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewVidPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NewITemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddResultTabbedPane.addTab("Добавить новый вид обследования", NewVidPanel);

        ResultTestingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        DateObsledLabel.setText("Выберите дату обследования  :");

        VidLabel.setText("Вид обследования :");

        VidList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VidListMouseClicked(evt);
            }
        });
        Vid1ScrollPane.setViewportView(VidList);

        TypeLabel.setText("Тип обследования :");

        InstrumentalRadioButton1.setText("Инструментальные");
        InstrumentalRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InstrumentalRadioButtonMouseClicked(evt);
            }
        });

        LaboratoryRadioButton1.setText("Лабораторные");
        LaboratoryRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LaboratoryRadioButtonMouseClicked(evt);
            }
        });

        FileChooserButton.setText("Обзор");
        FileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileChooserButtonActionPerformed(evt);
            }
        });

        SaveResultButton.setText("Сохранить");
        SaveResultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveResultButtonActionPerformed(evt);
            }
        });

        CancelResultButton.setText("Отмена");
        CancelResultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelResultButtonActionPerformed(evt);
            }
        });

        ResultObsledLabel.setText("Результаты обследования : ");

        LNameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Фамилия клиента", "Дата рождения"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };
        });
        LNameTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LNameTableMouseClicked(evt);
            }
        });
        LNameScrollPane.setViewportView(LNameTable);
        if (LNameTable.getColumnModel().getColumnCount() > 0) {
            LNameTable.getColumnModel().getColumn(0).setResizable(false);
            LNameTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            LNameTable.getColumnModel().getColumn(1).setResizable(false);
            LNameTable.getColumnModel().getColumn(1).setPreferredWidth(5);
        }

        FilePathTextField.setEditable(false);

        ExplainRadioButton.setText("Описание :");
        ExplainRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExplainRadioButtonMouseClicked(evt);
            }
        });

        FileRadioButton.setText("Файл :");
        FileRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FileRadioButtonMouseClicked(evt);
            }
        });

        ExplainTextArea.setColumns(20);
        ExplainTextArea.setLineWrap(true);
        ExplainTextArea.setRows(2);
        ExplainTextArea.setWrapStyleWord(true);
        ExplainScrollPane.setViewportView(ExplainTextArea);
        
        ExplainTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout ResultTestingPanelLayout = new javax.swing.GroupLayout(ResultTestingPanel);
        ResultTestingPanel.setLayout(ResultTestingPanelLayout);
        ResultTestingPanelLayout.setHorizontalGroup(
            ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LNameScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SaveResultButton)
                        .addGap(148, 148, 148)
                        .addComponent(CancelResultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)) //эту строку нужно поставить взамен нижней
  //.addGap(javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE))
                    .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                                .addComponent(Vid1ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                                        .addGap(116, 116, 116)
                                        .addComponent(ResultObsledLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                                                .addComponent(FileRadioButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(FilePathTextField)
                                                .addGap(18, 18, 18)
                                                .addComponent(FileChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                                                .addComponent(ExplainRadioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(ExplainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                                            .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(InstrumentalRadioButton1)
                                                .addGap(29, 29, 29)
                                                .addComponent(LaboratoryRadioButton1)
                                                .addContainerGap())
                                            .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                                                .addComponent(DateObsledLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(DateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())))))
                            .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                                .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(VidLabel)
                                    .addComponent(TypeLabel))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        ResultTestingPanelLayout.setVerticalGroup(
            ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                        .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(InstrumentalRadioButton1)
                            .addComponent(LaboratoryRadioButton1)
                            .addComponent(TypeLabel))
                        .addGap(18, 18, 18)
                        .addComponent(VidLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Vid1ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                                .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(DateObsledLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(39, 39, 39)
                                .addComponent(ResultObsledLabel)
                                .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ResultTestingPanelLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(ExplainScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ResultTestingPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ExplainRadioButton)
                                        .addGap(28, 28, 28)))
                                .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(FileChooserButton)
                                    .addComponent(FilePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FileRadioButton))
                                .addGap(37, 37, 37)
                                .addGroup(ResultTestingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(SaveResultButton)
                                    .addComponent(CancelResultButton))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(LNameScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout AddResultPanelLayout = new javax.swing.GroupLayout(AddResultPanel);
        AddResultPanel.setLayout(AddResultPanelLayout);
        AddResultPanelLayout.setHorizontalGroup(
            AddResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddResultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ResultTestingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        AddResultPanelLayout.setVerticalGroup(
            AddResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddResultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ResultTestingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        AddResultTabbedPane.addTab("Внести результаты обследования", AddResultPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddResultTabbedPane))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddResultTabbedPane))
        );
        
        VidList.setEnabled(false);
        InstrumentalRadioButton1.setEnabled(false);
        LaboratoryRadioButton1.setEnabled(false);
        SaveResultButton.setEnabled(false);
        DateChooser.setEnabled(false);
        ExplainTextArea.setEnabled(false);
        ExplainRadioButton.setEnabled(false);
        FileRadioButton.setEnabled(false);
        FileChooserButton.setEnabled(false);
        
        FillPatientsList();

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
            
            DefaultTableModel model = (DefaultTableModel) LNameTable.getModel();
            
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
    
    //обработчик выбора строки в списке пациентов
    private void LNameTableMouseClicked(java.awt.event.MouseEvent evt) 
    {                                        
        lName = LNameTable.getValueAt(LNameTable.getSelectedRow(), 0).toString();
        bDay = LNameTable.getValueAt(LNameTable.getSelectedRow(), 1).toString();
        
        VidList.setEnabled(true);
        InstrumentalRadioButton1.setEnabled(true);
        LaboratoryRadioButton1.setEnabled(true);
        SaveResultButton.setEnabled(true);
        ExplainRadioButton.setEnabled(true);
        FileRadioButton.setEnabled(true);
        DateChooser.setEnabled(true);
    }

    //обработчик клика по радиокнопке выбора инструментального вида обследования
    private void InstrumentalRadioButtonMouseClicked(java.awt.event.MouseEvent evt)
    {                                                     
        if(InstrumentalRadioButton.isSelected() || InstrumentalRadioButton1.isSelected())
        {
            InstrumentalRadioButton.setSelected(true);
            InstrumentalRadioButton1.setSelected(true);
            
            LaboratoryRadioButton.setSelected(false);
            typeTesting = InstrumentalRadioButton.getText();
            
            LaboratoryRadioButton1.setSelected(false);
            typeTesting = InstrumentalRadioButton1.getText();
            
            FillVidList(typeTesting);
        }
    }                                                    

    //обработчик клика по радиокнопке выбора лабораторного вида обследования
    private void LaboratoryRadioButtonMouseClicked(java.awt.event.MouseEvent evt)
    {                                                   
        if(LaboratoryRadioButton.isSelected() || LaboratoryRadioButton1.isSelected())
        {
            LaboratoryRadioButton.setSelected(true);
            LaboratoryRadioButton1.setSelected(true);
            
            InstrumentalRadioButton.setSelected(false);
            typeTesting = LaboratoryRadioButton.getText();
            
            InstrumentalRadioButton1.setSelected(false);
            typeTesting = LaboratoryRadioButton1.getText();
            
            FillVidList(typeTesting);
        }
    }   
    
    //обработчик клика по радиокнопке выбора описания анализов обследования
    private void ExplainRadioButtonMouseClicked(java.awt.event.MouseEvent evt)
    {                          
        if(!VerifyDateObsled())
        {
            ExplainRadioButton.setSelected(false);
            return;
        }
        
        if(ExplainRadioButton.isSelected())
        {
            ExplainTextArea.setEnabled(true);
            
            FileRadioButton.setSelected(false);
            FileChooserButton.setEnabled(false);
            FilePathTextField.setText("");
            file = null;
        }
    }                                                    

    //обработчик клика по радиокнопке выбора файла анализов обследования
    private void FileRadioButtonMouseClicked(java.awt.event.MouseEvent evt)
    {         
        if(!VerifyDateObsled())
        {
            FileRadioButton.setSelected(false);
            return;
        }

        if(FileRadioButton.isSelected())
        {
            ExplainRadioButton.setSelected(false);
            ExplainTextArea.setEnabled(false);
            
            FileChooserButton.setEnabled(true);
            
            ExplainTextArea.setText("");
        }
    } 
    
    //проверка выбранной даты обследования
    private boolean VerifyDateObsled()
    {
        date = DateChooser.getDate();
        
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru"));
        Date presentDay = calendar.getTime();
        
        if(date.after(presentDay))
        {
            JOptionPane.showMessageDialog(this, "Выбрана неверная дата обследования", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
            dateObsled = sdf.format(date);
        
        return true;
    }
        
    //заполнение списка существующих видов обследования
    private void FillVidList(String type)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            String answer = sender.SendTextToServer("getVidObsled_" + type);
            
            vidList.setModel(new javax.swing.AbstractListModel() 
            {
                String[] strings = answer.split("-");
                public int getSize() { return strings.length; }
                public Object getElementAt(int i) { return strings[i]; }
            });
            
            VidList.setModel(new javax.swing.AbstractListModel()
            {
                String[] strings1 =  answer.split("-"); 
                public int getSize() { return strings1.length; }
                public Object getElementAt(int i) { return strings1[i]; }
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

    //обработчик клика по кнопке сохранения нового вида обследования в базе
    private void SaveNewVidButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {                                                 
        try
        {
             //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            if(!InstrumentalRadioButton.isSelected() && !LaboratoryRadioButton.isSelected())
            {
                JOptionPane.showMessageDialog(this, "Выберите тип обследования", 
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(!newvidTextField.getText().equals(""))
            {
                String answer = sender.SendTextToServer("addNewVidTesting_" + newvidTextField.getText() + "#" + typeTesting);
                
                if(answer.equals("OK"))
                {
                    JOptionPane.showMessageDialog(this, "Данные добавлены в базу", 
                        "Внимание", JOptionPane.INFORMATION_MESSAGE);
                        FillVidList(typeTesting);
                        newvidTextField.setText("");
                }
                if(answer.equals("null"))
                    JOptionPane.showMessageDialog(this, "Ошибка добавления данных", 
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            else
               JOptionPane.showMessageDialog(this, "Введите новый вид обследования", 
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
    
    //обработчик выбора строки в списке видов обследования
    private void VidListMouseClicked(java.awt.event.MouseEvent evt) 
    {
        vidTesting = (String)VidList.getSelectedValue();
    } 
    
    //обработчик клика по кнопке выбора файла с обследованиями для внесения в базу
    private void FileChooserButtonActionPerformed(java.awt.event.ActionEvent evt)
    {                                                 
        try
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("pdf Файлы", "pdf"));
            int res = fileChooser.showDialog(this, "Открыть файл");
            if(res == JFileChooser.APPROVE_OPTION)
            {
                file = fileChooser.getSelectedFile();
                FilePathTextField.setText(file.getName());
            } 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    } 

    //обработчик клика по кнопке отмены добавления нового вида обследования
    private void CancelAddNewVidButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
        this.dispose();
    }                                                     

    //обработчик клика по кнопке добавления результатов обследования
    private void SaveResultButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        String result = "";
        
        if(lName.equals("") || bDay.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Выберите пациента", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!InstrumentalRadioButton1.isSelected() && !LaboratoryRadioButton1.isSelected())
        {
            JOptionPane.showMessageDialog(this, "Выберите тип обследования", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(vidTesting.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Выберите вид обследования", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(dateObsled.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Выберите дату обследования", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(ExplainTextArea.getText().equals("") && FilePathTextField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Внесите результаты обследования "
                    + "или выберите файл с результатами обследования", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!ExplainTextArea.getText().equals(""))
        {
            String data = vidTesting + "#" + dateObsled + "#" + lName + "-" + bDay + "#" + ExplainTextArea.getText();
            result = sender.SendTextToServer("addResultObsled_" + data);
        }
        
        if(!FilePathTextField.getText().equals(""))
        {
            String fName = vidTesting + "/" + lName + "-" + bDay + file.getName().substring((file.getName()).lastIndexOf(".")) + "/" + dateObsled;
            String sendFile = "receiveFile_" + fName + "_" + file.getAbsolutePath();
            try
            {
                result = sender.SendFileToServer(sendFile);
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
        
        if(result.equals("OK"))
        {
            JOptionPane.showMessageDialog(this, "Данные сохранены", 
                    "Внимание", JOptionPane.INFORMATION_MESSAGE);
        
            lName ="";
            bDay = "";
            InstrumentalRadioButton1.setSelected(false);
            LaboratoryRadioButton1.setSelected(false);
            vidTesting  = "";
            dateObsled  = "";
            ExplainTextArea.setText("");
            FilePathTextField.setText("");
            ExplainRadioButton.setSelected(false);
            FileRadioButton.setSelected(false);
            ExplainTextArea.setText("");
            FilePathTextField.setText("");
            file = null;
        }
        else
            JOptionPane.showMessageDialog(this, "Ошибка сохранения данных", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
    }                                                

    //обработчик клика по кнопке отмены внесения нового обследования в базу
    private void CancelResultButtonActionPerformed(java.awt.event.ActionEvent evt)
    {                                                   
        this.dispose();
    }                                                  
}
