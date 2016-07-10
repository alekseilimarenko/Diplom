package umec.Priem;

//<editor-fold defaultstate="collapsed" desc="import">
import TCPConnect.TCPSender;
import com.google.common.base.CharMatcher;
import static com.google.common.base.Strings.isNullOrEmpty;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.ConnectException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
//</editor-fold>

public class PatientInfo extends javax.swing.JDialog
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private javax.swing.JLabel BdayLabel;
    private javax.swing.JTextField BdayTextField;
    private javax.swing.JButton CancelButton;
    private javax.swing.JPanel CurrentDiagnosPanel;
    private javax.swing.JTextArea CurrentDiagnozTextArea;
    private javax.swing.JPanel CurrentObjectivPanel;
    private javax.swing.JTextArea CurrentObjectivnoTextArea;
    private javax.swing.JPanel CurrentTreatmentPanel;
    private javax.swing.JTextArea CurrentTreatmentTextArea;
    private javax.swing.JPanel CurrentVisitPanel;
    private javax.swing.JPanel CurrentWalobyPanel;
    private javax.swing.JTextArea CurrentWalobyTextArea;
    private javax.swing.JList DateChooserList;
    private javax.swing.JPanel DiagnosPanel;
    private javax.swing.JTextField ExpaineTextField;
    private javax.swing.JLabel ExplainLabel;
    private javax.swing.JLabel FNameLabel;
    private javax.swing.JTextField FNameTextField;
    private javax.swing.JLabel HealthCardPagePanel;
    private javax.swing.JPanel HealthCardPanel;
    private javax.swing.JTextArea HistoryDiagnozTextArea;
    private javax.swing.JTextArea HistoryObjectivnoTextArea;
    private javax.swing.JPanel HistoryPanel;
    private javax.swing.JTextArea HistoryTreatmentTextArea;
    private javax.swing.JLabel HistoryVisitLabel;
    private javax.swing.JTable HistoryVisitTable;
    private javax.swing.JTextArea HistoryWalobyTextArea;
    private javax.swing.JLabel InsCompanyLabel;
    private javax.swing.JTextField InsCompanyTextField;
    private javax.swing.JLabel LNameLabel;
    private javax.swing.JTextField LNameTextField;
    private javax.swing.JPanel ObjectivPanel;
    private javax.swing.JPanel ObsledPanel;
    private javax.swing.JTabbedPane ObsledTabbedPane;
    private javax.swing.JScrollPane PDFScrollPane;
    private javax.swing.JList PageNumberList;
    private javax.swing.JLabel PageNumbersLabel;
    private javax.swing.JPanel PatientInfoPanel;
    private javax.swing.JTabbedPane PatientInfoTabbedPane;
    private javax.swing.JTabbedPane PatientInfoTabbedPane1;
    private javax.swing.JScrollPane ResultPageListScrollPane;
    private javax.swing.JList ResultPageNumberList;
    private javax.swing.JLabel ResultPageNumbersLabel;
    private javax.swing.JLabel ResultPagePanel;
    private javax.swing.JPanel ResultPanel;
    private javax.swing.JScrollPane ResultScrollPane;
    private javax.swing.JLabel ResultZoomInLabel;
    private javax.swing.JLabel ResultZoomOutLabel;
    private javax.swing.JButton ResultbtnFirstPage;
    private javax.swing.JButton ResultbtnLastPage;
    private javax.swing.JButton ResultbtnNextPage;
    private javax.swing.JButton ResultbtnPreviousPage;
    private javax.swing.JLabel SNameLabel;
    private javax.swing.JTextField SNameTextField;
    private javax.swing.JButton SaveButton;
    private javax.swing.JPanel TreatmentPanel;
    private javax.swing.JComboBox TypeObsledovComboBox;
    private javax.swing.JList VidObsledList;
    private javax.swing.JPanel WalobyPanel;
    private javax.swing.JLabel ZoomInLabel;
    private javax.swing.JLabel ZoomOutLabel;
    private javax.swing.JButton btnFirstPage;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnNextPage;
    private javax.swing.JButton btnPreviousPage;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private TCPSender sender;
    private String param, DocLName, patientLName, patientBDay, typeObsledov;
    private DefaultListModel<String> dateModel;
    private static enum Navigation {GO_FIRST_PAGE, FORWARD, BACKWARD, GO_LAST_PAGE, GO_N_PAGE, GO_TO_PAGE}
    private static final CharMatcher POSITIVE_DIGITAL = CharMatcher.anyOf("0123456789");
    private static final String GO_PAGE_TEMPLATE = "%s из %s";
    private static final int FIRST_PAGE = 1;
    private int currentPage = FIRST_PAGE;
    private PDFFile HealthFile, ResultFile;
    private DefaultListModel<Integer> model = new DefaultListModel<Integer>();
    private DefaultListModel<Integer> resPage = new DefaultListModel<Integer>();
    private DefaultListModel<Integer> healthPage = new DefaultListModel<Integer>();
    private boolean start = false;
    private double scale = 70;
    private File health, test;
    private RandomAccessFile rafTest;
    private RandomAccessFile rafHealth;
//</editor-fold>
    
    //конструктор формы
    public PatientInfo(javax.swing.JDialog aThis, boolean modal, TCPSender sn, String pr) 
    {
        super(aThis, modal);
        this.param = pr;
        this.sender = sn;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="создание компонентов формы">                          
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
        
        PatientInfoPanel = new javax.swing.JPanel();
        LNameLabel = new javax.swing.JLabel();
        LNameTextField = new javax.swing.JTextField();
        FNameLabel = new javax.swing.JLabel();
        FNameTextField = new javax.swing.JTextField();
        SNameLabel = new javax.swing.JLabel();
        SNameTextField = new javax.swing.JTextField();
        InsCompanyLabel = new javax.swing.JLabel();
        InsCompanyTextField = new javax.swing.JTextField();
        SaveButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        BdayLabel = new javax.swing.JLabel();
        BdayTextField = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        HistoryVisitTable = new javax.swing.JTable();
        HistoryVisitLabel = new javax.swing.JLabel();
        ResultPanel = new javax.swing.JPanel();
        ObsledTabbedPane = new javax.swing.JTabbedPane();
        HistoryPanel = new javax.swing.JPanel();
        PatientInfoTabbedPane = new javax.swing.JTabbedPane();
        WalobyPanel = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        HistoryWalobyTextArea = new javax.swing.JTextArea();
        ObjectivPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        HistoryObjectivnoTextArea = new javax.swing.JTextArea();
        DiagnosPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        HistoryDiagnozTextArea = new javax.swing.JTextArea();
        TreatmentPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        HistoryTreatmentTextArea = new javax.swing.JTextArea();
        CurrentVisitPanel = new javax.swing.JPanel();
        PatientInfoTabbedPane1 = new javax.swing.JTabbedPane();
        CurrentWalobyPanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        CurrentWalobyTextArea = new javax.swing.JTextArea();
        CurrentObjectivPanel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        CurrentObjectivnoTextArea = new javax.swing.JTextArea();
        CurrentDiagnosPanel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        CurrentDiagnozTextArea = new javax.swing.JTextArea();
        CurrentTreatmentPanel = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        CurrentTreatmentTextArea = new javax.swing.JTextArea();
        ObsledPanel = new javax.swing.JPanel();
        TypeObsledovComboBox = new javax.swing.JComboBox();
        jScrollPane12 = new javax.swing.JScrollPane();
        VidObsledList = new javax.swing.JList();
        jScrollPane13 = new javax.swing.JScrollPane();
        DateChooserList = new javax.swing.JList();
        ExpaineTextField = new javax.swing.JTextField();
        ExplainLabel = new javax.swing.JLabel();
        ResultScrollPane = new javax.swing.JScrollPane();
        ResultPagePanel = new javax.swing.JLabel();
        ResultZoomInLabel = new javax.swing.JLabel();
        ResultZoomOutLabel = new javax.swing.JLabel();
        ResultbtnFirstPage = new javax.swing.JButton();
        ResultbtnPreviousPage = new javax.swing.JButton();
        ResultbtnNextPage = new javax.swing.JButton();
        ResultbtnLastPage = new javax.swing.JButton();
        ResultPageNumbersLabel = new javax.swing.JLabel();
        ResultPageListScrollPane = new javax.swing.JScrollPane();
        ResultPageNumberList = new javax.swing.JList();
        HealthCardPanel = new javax.swing.JPanel();
        PageNumbersLabel = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        PageNumberList = new javax.swing.JList();
        btnFirstPage = new javax.swing.JButton();
        btnPreviousPage = new javax.swing.JButton();
        btnNextPage = new javax.swing.JButton();
        btnLastPage = new javax.swing.JButton();
        PDFScrollPane = new javax.swing.JScrollPane();
        HealthCardPagePanel = new javax.swing.JLabel();
        ZoomInLabel = new javax.swing.JLabel();
        ZoomOutLabel = new javax.swing.JLabel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        PatientInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Сведения о клиенте"));

        LNameLabel.setText("Фамилия :");

        FNameLabel.setText("Имя :");

        SNameLabel.setText("Отчество :");

        InsCompanyLabel.setText("Страховая компания : ");

        SaveButton.setText("Сохранить");
        SaveButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                SaveButtonActionPerformed(evt);
        });

        CancelButton.setText("Отмена");
        CancelButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                CancelButtonActionPerformed(evt);
        });

        BdayLabel.setText("Дата рождения : ");

        HistoryVisitTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Фамилия доктора", "Дата"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };
        });
        HistoryVisitTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HistoryVisitTableMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(HistoryVisitTable);
        if (HistoryVisitTable.getColumnModel().getColumnCount() > 0) {
            HistoryVisitTable.getColumnModel().getColumn(1).setResizable(false);
            HistoryVisitTable.getColumnModel().getColumn(1).setPreferredWidth(25);
        }

        HistoryVisitLabel.setText("История посещений : ");

        javax.swing.GroupLayout PatientInfoPanelLayout = new javax.swing.GroupLayout(PatientInfoPanel);
        PatientInfoPanel.setLayout(PatientInfoPanelLayout);
        PatientInfoPanelLayout.setHorizontalGroup(
            PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PatientInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InsCompanyTextField)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(PatientInfoPanelLayout.createSequentialGroup()
                        .addGroup(PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PatientInfoPanelLayout.createSequentialGroup()
                                .addComponent(BdayLabel)
                                .addGap(12, 12, 12)
                                .addComponent(BdayTextField))
                            .addGroup(PatientInfoPanelLayout.createSequentialGroup()
                                .addGroup(PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(FNameLabel)
                                    .addComponent(LNameLabel))
                                .addGap(50, 50, 50)
                                .addGroup(PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LNameTextField)
                                    .addComponent(FNameTextField)
                                    .addComponent(SNameTextField, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(PatientInfoPanelLayout.createSequentialGroup()
                                .addGroup(PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HistoryVisitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InsCompanyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SNameLabel))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PatientInfoPanelLayout.createSequentialGroup()
                        .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        PatientInfoPanelLayout.setVerticalGroup(
            PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PatientInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LNameLabel)
                    .addComponent(LNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FNameLabel)
                    .addComponent(FNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SNameLabel)
                    .addComponent(SNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BdayLabel)
                    .addComponent(BdayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InsCompanyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InsCompanyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HistoryVisitLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(PatientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelButton)
                    .addComponent(SaveButton))
                .addContainerGap())
        );

        ResultPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        HistoryWalobyTextArea.setColumns(20);
        HistoryWalobyTextArea.setLineWrap(true);
        HistoryWalobyTextArea.setRows(5);
        HistoryWalobyTextArea.setWrapStyleWord(true);
        jScrollPane.setViewportView(HistoryWalobyTextArea);

        javax.swing.GroupLayout WalobyPanelLayout = new javax.swing.GroupLayout(WalobyPanel);
        WalobyPanel.setLayout(WalobyPanelLayout);
        WalobyPanelLayout.setHorizontalGroup(
            WalobyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE)
        );
        WalobyPanelLayout.setVerticalGroup(
            WalobyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WalobyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
        );

        PatientInfoTabbedPane.addTab("Жалобы", WalobyPanel);

        HistoryObjectivnoTextArea.setColumns(20);
        HistoryObjectivnoTextArea.setLineWrap(true);
        HistoryObjectivnoTextArea.setRows(5);
        HistoryObjectivnoTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(HistoryObjectivnoTextArea);

        javax.swing.GroupLayout ObjectivPanelLayout = new javax.swing.GroupLayout(ObjectivPanel);
        ObjectivPanel.setLayout(ObjectivPanelLayout);
        ObjectivPanelLayout.setHorizontalGroup(
            ObjectivPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE)
        );
        ObjectivPanelLayout.setVerticalGroup(
            ObjectivPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ObjectivPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
        );

        PatientInfoTabbedPane.addTab("Объективно", ObjectivPanel);

        HistoryDiagnozTextArea.setColumns(20);
        HistoryDiagnozTextArea.setLineWrap(true);
        HistoryDiagnozTextArea.setRows(5);
        HistoryDiagnozTextArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(HistoryDiagnozTextArea);

        javax.swing.GroupLayout DiagnosPanelLayout = new javax.swing.GroupLayout(DiagnosPanel);
        DiagnosPanel.setLayout(DiagnosPanelLayout);
        DiagnosPanelLayout.setHorizontalGroup(
            DiagnosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1052, Short.MAX_VALUE)
            .addGroup(DiagnosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE))
        );
        DiagnosPanelLayout.setVerticalGroup(
            DiagnosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
            .addGroup(DiagnosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DiagnosPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)))
        );

        PatientInfoTabbedPane.addTab("Диагноз", DiagnosPanel);

        HistoryTreatmentTextArea.setColumns(20);
        HistoryTreatmentTextArea.setLineWrap(true);
        HistoryTreatmentTextArea.setRows(5);
        HistoryTreatmentTextArea.setWrapStyleWord(true);
        jScrollPane3.setViewportView(HistoryTreatmentTextArea);

        javax.swing.GroupLayout TreatmentPanelLayout = new javax.swing.GroupLayout(TreatmentPanel);
        TreatmentPanel.setLayout(TreatmentPanelLayout);
        TreatmentPanelLayout.setHorizontalGroup(
            TreatmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1052, Short.MAX_VALUE)
            .addGroup(TreatmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE))
        );
        TreatmentPanelLayout.setVerticalGroup(
            TreatmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
            .addGroup(TreatmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TreatmentPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)))
        );

        PatientInfoTabbedPane.addTab("Лечение", TreatmentPanel);

        javax.swing.GroupLayout HistoryPanelLayout = new javax.swing.GroupLayout(HistoryPanel);
        HistoryPanel.setLayout(HistoryPanelLayout);
        HistoryPanelLayout.setHorizontalGroup(
            HistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HistoryPanelLayout.createSequentialGroup()
                .addComponent(PatientInfoTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1052, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        HistoryPanelLayout.setVerticalGroup(
            HistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PatientInfoTabbedPane))
        );

        ObsledTabbedPane.addTab("История посещений", HistoryPanel);

        CurrentWalobyTextArea.setColumns(20);
        CurrentWalobyTextArea.setLineWrap(true);
        CurrentWalobyTextArea.setRows(5);
        CurrentWalobyTextArea.setWrapStyleWord(true);
        jScrollPane7.setViewportView(CurrentWalobyTextArea);

        javax.swing.GroupLayout CurrentWalobyPanelLayout = new javax.swing.GroupLayout(CurrentWalobyPanel);
        CurrentWalobyPanel.setLayout(CurrentWalobyPanelLayout);
        CurrentWalobyPanelLayout.setHorizontalGroup(
            CurrentWalobyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
        );
        CurrentWalobyPanelLayout.setVerticalGroup(
            CurrentWalobyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CurrentWalobyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
        );

        PatientInfoTabbedPane1.addTab("Жалобы", CurrentWalobyPanel);

        CurrentObjectivnoTextArea.setColumns(20);
        CurrentObjectivnoTextArea.setLineWrap(true);
        CurrentObjectivnoTextArea.setRows(5);
        CurrentObjectivnoTextArea.setWrapStyleWord(true);
        jScrollPane8.setViewportView(CurrentObjectivnoTextArea);

        javax.swing.GroupLayout CurrentObjectivPanelLayout = new javax.swing.GroupLayout(CurrentObjectivPanel);
        CurrentObjectivPanel.setLayout(CurrentObjectivPanelLayout);
        CurrentObjectivPanelLayout.setHorizontalGroup(
            CurrentObjectivPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
        );
        CurrentObjectivPanelLayout.setVerticalGroup(
            CurrentObjectivPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CurrentObjectivPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
        );

        PatientInfoTabbedPane1.addTab("Объективно", CurrentObjectivPanel);

        CurrentDiagnozTextArea.setColumns(20);
        CurrentDiagnozTextArea.setLineWrap(true);
        CurrentDiagnozTextArea.setRows(5);
        CurrentDiagnozTextArea.setWrapStyleWord(true);
        jScrollPane9.setViewportView(CurrentDiagnozTextArea);

        javax.swing.GroupLayout CurrentDiagnosPanelLayout = new javax.swing.GroupLayout(CurrentDiagnosPanel);
        CurrentDiagnosPanel.setLayout(CurrentDiagnosPanelLayout);
        CurrentDiagnosPanelLayout.setHorizontalGroup(
            CurrentDiagnosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
            .addGroup(CurrentDiagnosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE))
        );
        CurrentDiagnosPanelLayout.setVerticalGroup(
            CurrentDiagnosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
            .addGroup(CurrentDiagnosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CurrentDiagnosPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)))
        );

        PatientInfoTabbedPane1.addTab("Диагноз", CurrentDiagnosPanel);

        CurrentTreatmentTextArea.setColumns(20);
        CurrentTreatmentTextArea.setLineWrap(true);
        CurrentTreatmentTextArea.setRows(5);
        CurrentTreatmentTextArea.setWrapStyleWord(true);
        jScrollPane10.setViewportView(CurrentTreatmentTextArea);

        javax.swing.GroupLayout CurrentTreatmentPanelLayout = new javax.swing.GroupLayout(CurrentTreatmentPanel);
        CurrentTreatmentPanel.setLayout(CurrentTreatmentPanelLayout);
        CurrentTreatmentPanelLayout.setHorizontalGroup(
            CurrentTreatmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
            .addGroup(CurrentTreatmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE))
        );
        CurrentTreatmentPanelLayout.setVerticalGroup(
            CurrentTreatmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
            .addGroup(CurrentTreatmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CurrentTreatmentPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)))
        );

        PatientInfoTabbedPane1.addTab("Лечение", CurrentTreatmentPanel);

        javax.swing.GroupLayout CurrentVisitPanelLayout = new javax.swing.GroupLayout(CurrentVisitPanel);
        CurrentVisitPanel.setLayout(CurrentVisitPanelLayout);
        CurrentVisitPanelLayout.setHorizontalGroup(
            CurrentVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PatientInfoTabbedPane1)
        );
        CurrentVisitPanelLayout.setVerticalGroup(
            CurrentVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CurrentVisitPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PatientInfoTabbedPane1))
        );

        ObsledTabbedPane.addTab("Текущий прием", CurrentVisitPanel);

        TypeObsledovComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Типы обследования", "Инструментальные", "Лабораторные" }));
        TypeObsledovComboBox.addActionListener((java.awt.event.ActionEvent evt) -> {
                TypeObsledovComboBoxActionPerformed(evt);
        });

        VidObsledList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        VidObsledList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VidObsledListMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(VidObsledList);

        DateChooserList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        DateChooserList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DateChooserListMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(DateChooserList);

        ExplainLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExplainLabel.setText("Описание : ");
        ExplainLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout ResultPagePanelLayout = new javax.swing.GroupLayout(ResultPagePanel);
        ResultPagePanel.setLayout(ResultPagePanelLayout);
        ResultPagePanelLayout.setHorizontalGroup(
            ResultPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );
        ResultPagePanelLayout.setVerticalGroup(
            ResultPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 557, Short.MAX_VALUE)
        );

        ResultPagePanel.setVerticalAlignment(JLabel.WIDTH);
        ResultScrollPane.setViewportView(ResultPagePanel);

        ResultZoomInLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("zoom-in.png"))); // NOI18N
        ResultZoomInLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ZoomInLabelMouseClicked("result");
            }
        });

        ResultZoomOutLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("zoom-out.png"))); // NOI18N
        ResultZoomOutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ZoomOutLabelMouseClicked("result");
            }
        });

        ResultbtnFirstPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("first.png"))); // NOI18N
        ResultbtnFirstPage.setMaximumSize(new java.awt.Dimension(24, 24));
        ResultbtnFirstPage.setMinimumSize(new java.awt.Dimension(24, 24));
        ResultbtnFirstPage.setPreferredSize(new java.awt.Dimension(24, 24));

        ResultbtnPreviousPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("prev.png"))); // NOI18N
        ResultbtnPreviousPage.setMaximumSize(new java.awt.Dimension(24, 24));
        ResultbtnPreviousPage.setMinimumSize(new java.awt.Dimension(24, 24));
        ResultbtnPreviousPage.setPreferredSize(new java.awt.Dimension(24, 24));

        ResultbtnNextPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("next.png"))); // NOI18N
        ResultbtnNextPage.setMaximumSize(new java.awt.Dimension(24, 24));
        ResultbtnNextPage.setMinimumSize(new java.awt.Dimension(24, 24));
        ResultbtnNextPage.setPreferredSize(new java.awt.Dimension(24, 24));

        ResultbtnLastPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("last.png"))); // NOI18N
        ResultbtnLastPage.setMaximumSize(new java.awt.Dimension(24, 24));
        ResultbtnLastPage.setMinimumSize(new java.awt.Dimension(24, 24));
        ResultbtnLastPage.setPreferredSize(new java.awt.Dimension(24, 24));

        ResultPageNumbersLabel.setText("Страницы : ");

        ResultPageNumberList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ResultPageNumberList.setModel(resPage);
        ResultPageListScrollPane.setViewportView(ResultPageNumberList);

        javax.swing.GroupLayout ObsledPanelLayout = new javax.swing.GroupLayout(ObsledPanel);
        ObsledPanel.setLayout(ObsledPanelLayout);
        ObsledPanelLayout.setHorizontalGroup(
            ObsledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ObsledPanelLayout.createSequentialGroup()
                .addGroup(ObsledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TypeObsledovComboBox, 0, 155, Short.MAX_VALUE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ObsledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ResultPageListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ObsledPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(ResultPageNumbersLabel))
                    .addGroup(ObsledPanelLayout.createSequentialGroup()
                        .addComponent(ResultbtnFirstPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(ResultbtnPreviousPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(ResultbtnNextPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(ResultbtnLastPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ObsledPanelLayout.createSequentialGroup()
                        .addComponent(ResultZoomInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ResultZoomOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addComponent(ExplainLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ObsledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExpaineTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                    .addComponent(ResultScrollPane)))
        );
        ObsledPanelLayout.setVerticalGroup(
            ObsledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ObsledPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ObsledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TypeObsledovComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExpaineTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExplainLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ObsledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ObsledPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ResultScrollPane)
                    .addGroup(ObsledPanelLayout.createSequentialGroup()
                        .addGroup(ObsledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ResultZoomInLabel)
                            .addComponent(ResultZoomOutLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ObsledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ResultbtnFirstPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ResultbtnPreviousPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ResultbtnNextPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ResultbtnLastPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ResultPageNumbersLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ResultPageListScrollPane))))
        );

        ObsledTabbedPane.addTab("Обследования", ObsledPanel);

        PageNumbersLabel.setText("Страницы : ");

        PageNumberList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        PageNumberList.setModel(healthPage);
        jScrollPane6.setViewportView(PageNumberList);

        btnFirstPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("first.png"))); // NOI18N
        btnFirstPage.setMaximumSize(new java.awt.Dimension(24, 24));
        btnFirstPage.setMinimumSize(new java.awt.Dimension(24, 24));
        btnFirstPage.setPreferredSize(new java.awt.Dimension(24, 24));

        btnPreviousPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("prev.png"))); // NOI18N
        btnPreviousPage.setMaximumSize(new java.awt.Dimension(24, 24));
        btnPreviousPage.setMinimumSize(new java.awt.Dimension(24, 24));
        btnPreviousPage.setPreferredSize(new java.awt.Dimension(24, 24));

        btnNextPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("next.png"))); // NOI18N
        btnNextPage.setMaximumSize(new java.awt.Dimension(24, 24));
        btnNextPage.setMinimumSize(new java.awt.Dimension(24, 24));
        btnNextPage.setPreferredSize(new java.awt.Dimension(24, 24));

        btnLastPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("last.png"))); // NOI18N
        btnLastPage.setMaximumSize(new java.awt.Dimension(24, 24));
        btnLastPage.setMinimumSize(new java.awt.Dimension(24, 24));
        btnLastPage.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout HealthCardPagePanelLayout = new javax.swing.GroupLayout(HealthCardPagePanel);
        HealthCardPagePanel.setLayout(HealthCardPagePanelLayout);
        HealthCardPagePanelLayout.setHorizontalGroup(
            HealthCardPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
        );
        HealthCardPagePanelLayout.setVerticalGroup(
            HealthCardPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );

        HealthCardPagePanel.setVerticalAlignment(JLabel.WIDTH);
        PDFScrollPane.setViewportView(HealthCardPagePanel);

        ZoomInLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("zoom-in.png"))); // NOI18N
        ZoomInLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ZoomInLabelMouseClicked("health");
            }
        });

        ZoomOutLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("zoom-out.png"))); // NOI18N
        ZoomOutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ZoomOutLabelMouseClicked("health");
            }
        });

        javax.swing.GroupLayout HealthCardPanelLayout = new javax.swing.GroupLayout(HealthCardPanel);
        HealthCardPanel.setLayout(HealthCardPanelLayout);
        HealthCardPanelLayout.setHorizontalGroup(
            HealthCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HealthCardPanelLayout.createSequentialGroup()
                .addGroup(HealthCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HealthCardPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HealthCardPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(HealthCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HealthCardPanelLayout.createSequentialGroup()
                                .addComponent(ZoomInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ZoomOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HealthCardPanelLayout.createSequentialGroup()
                                .addGroup(HealthCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(HealthCardPanelLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(PageNumbersLabel))
                                    .addGroup(HealthCardPanelLayout.createSequentialGroup()
                                        .addComponent(btnFirstPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnPreviousPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnNextPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnLastPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))))
                .addComponent(PDFScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE))
        );
        HealthCardPanelLayout.setVerticalGroup(
            HealthCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HealthCardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HealthCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PDFScrollPane)
                    .addGroup(HealthCardPanelLayout.createSequentialGroup()
                        .addGroup(HealthCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ZoomInLabel)
                            .addComponent(ZoomOutLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(HealthCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFirstPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPreviousPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNextPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLastPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PageNumbersLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))))
        );

        ObsledTabbedPane.addTab("Амбулаторная карта", HealthCardPanel);

        javax.swing.GroupLayout ResultPanelLayout = new javax.swing.GroupLayout(ResultPanel);
        ResultPanel.setLayout(ResultPanelLayout);
        ResultPanelLayout.setHorizontalGroup(
            ResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ResultPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ObsledTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ResultPanelLayout.setVerticalGroup(
            ResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ObsledTabbedPane)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PatientInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ResultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PatientInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ResultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        
        disableAllNavigationButton();
 
        HistoryWalobyTextArea.setEditable(false);
        HistoryObjectivnoTextArea.setEditable(false);
        HistoryDiagnozTextArea.setEditable(false);
        HistoryTreatmentTextArea.setEditable(false);
        
        FillHistoryVisitTable();
                
        pack();
    }// </editor-fold>  
        
    //увеличение документа
    private void ZoomInLabelMouseClicked(String panel)
    {
        if(panel.equals("health"))
            HealthCardPagePanel.setIcon(new ImageIcon(GetImageFromPDF("health", currentPage, -10.0)));
        else
            ResultPagePanel.setIcon(new ImageIcon(GetImageFromPDF("result", currentPage, -10.0)));   
    }
    
    //уменьшение документа
    private void ZoomOutLabelMouseClicked(String panel)
    {
        if(panel.equals("health"))
            HealthCardPagePanel.setIcon(new ImageIcon(GetImageFromPDF("health", currentPage, 10.0)));
        else
            ResultPagePanel.setIcon(new ImageIcon(GetImageFromPDF("result", currentPage, 10.0)));
    }
    
    //заполнение истории посещений центра клиентом
    private void FillHistoryVisitTable()
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            String[] value = param.split("-");
            DocLName = value[0];
            patientLName = value[1];
            patientBDay = value[2];
            
            //отправка запроса на сервер с фамилией доктора
            String[] info =  sender.SendTextToServer("getKonsultList_" + patientLName + "-" + patientBDay).split("#");    
            
            String[] patientinf = info[0].split(":");
            LNameTextField.setText(patientLName);
            FNameTextField.setText(patientinf[0]);
            SNameTextField.setText(patientinf[1]);
            BdayTextField.setText(patientBDay);
            InsCompanyTextField.setText(patientinf[2]);
                
            DefaultTableModel visitListmodel = (DefaultTableModel) HistoryVisitTable.getModel();
            while(visitListmodel.getRowCount() > 0)
                visitListmodel.removeRow(0);
            
            if(!info[1].equals("null"))
            {
                String[] konsList = info[1].split(";");
            
                for(String kons : konsList)
                {
                    String[] k = kons.split("-");
                    visitListmodel.addRow(new Object[]{k[0], k[1]});
                }
            }
            
            GetFile("health");
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
    
    //получение файла
    private void GetFile(String fileType)
    {
        String[] path;
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }

            //new ReceiveThread(sender, LNameTextField.getText() + "-" + BdayTextField.getText()).start();
            if(fileType.equals("health"))
            {    
                path = sender.ReceiveDataFromServer("sendData_" + fileType + ":" + LNameTextField.getText() + "-" + BdayTextField.getText()).split(":");
                System.out.println();
                if(path[0].equals("file"))
                {
                    health = new File(path[1]);
                    
                    //load a pdf from a byte buffer
                    rafHealth = new RandomAccessFile(health, "r");

                    HealthCardPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
                        public void componentShown(java.awt.event.ComponentEvent evt) {
                            HealthCardPanelComponentShown(evt);
                        }
                    });
                }
                
                if(path[0].equals("no_med"))
                {
                    HealthCardPagePanel.setText("Медицинская карта отсутствует");
                }
                    
            }

            if(fileType.equals("test"))
            {
                path = sender.ReceiveDataFromServer("sendData_" + 
                        fileType + ":" + LNameTextField.getText() + "-" + 
                        BdayTextField.getText() + "-" + 
                        DateChooserList.getSelectedValue().toString() + "-" + 
                        VidObsledList.getSelectedValue().toString()).split(":");
                if(path[0].equals("text"))
                {
                    if(resPage.getSize() > 0)
                        resPage.clear();
                    
                    ResultPagePanel.setIcon(new ImageIcon(""));
                    ResultPagePanel.setText(path[1]);
                }
                else
                {
                    ResultPagePanel.setText("");
                    
                    test = new File(path[1]);
                    
                    //load a pdf from a byte buffer
                    rafTest = new RandomAccessFile(test, "r");
                    
                    ShowTestFile();
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
    
    //обработчик клика по строке HistoryVisitTable
    private void HistoryVisitTableMouseClicked(java.awt.event.MouseEvent evt)
    {                                            
        String doc = HistoryVisitTable.getValueAt(HistoryVisitTable.getSelectedRow(), 0).toString();
        String date = HistoryVisitTable.getValueAt(HistoryVisitTable.getSelectedRow(), 1).toString();
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //отправка запроса на сервер с фамилией доктора
            String[] konsult =  sender.SendTextToServer("getKonsult_" + patientLName + "-" + patientBDay + "-" + doc + "-" + date).split("#");
            
            HistoryWalobyTextArea.setText(konsult[0]);
            HistoryObjectivnoTextArea.setText(konsult[1]);
            HistoryDiagnozTextArea.setText(konsult[2]);
            HistoryTreatmentTextArea.setText(konsult[3]);
            
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
    
    //обработчик выбора значения комбобокса с типами обследования
    private void TypeObsledovComboBoxActionPerformed(ActionEvent evt)
    {
        JComboBox box = (JComboBox)evt.getSource();
        if(box.getSelectedItem().toString().equals("Виды обследования"))
        {
            JOptionPane.showMessageDialog(this, "Выберите вид обследования", 
                    "Ошибка доступа", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            VidObsledList.setModel(new javax.swing.AbstractListModel()
            {
                String[] strings = sender.SendTextToServer("getVidObsled_" + box.getSelectedItem().toString()).split("-");
                @Override
                public int getSize() { return strings.length; }
                @Override
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
    
    //обработчик клика по строке VidObsledList
    private void VidObsledListMouseClicked(MouseEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            dateModel = new DefaultListModel<String>();
            
            ResultPagePanel.setIcon(new ImageIcon(""));
            ResultPagePanel.setText("");
            model.clear();
            
            String answer = sender.SendTextToServer("getDateResObsled_" + LNameTextField.getText()
                    + "-" + BdayTextField.getText()
                    + "-" + VidObsledList.getSelectedValue().toString());
            System.out.println(answer);
            
            if(!answer.equals("null"))
            {
                if(dateModel.getSize() > 0)
                {
                    dateModel.removeAllElements();
                }
                
                String[] dates = answer.split("-");
                for(String date : dates)
                {
                    dateModel.addElement(date);
                }
            }
            else
            {
                dateModel.clear();
            }
            
            DateChooserList.setModel(dateModel);
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
    
    //обработчик клика по строке в таблице с датами анализов
    private void DateChooserListMouseClicked(MouseEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            GetFile("test");
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
    
    //деактивация кнопок навигации
    private void disableAllNavigationButton()
    {
        btnFirstPage.setEnabled(false);
        btnPreviousPage.setEnabled(false);
        btnNextPage.setEnabled(false);
        btnLastPage.setEnabled(false);
        
        ZoomInLabel.setEnabled(false);
        ZoomOutLabel.setEnabled(false);
                
        ResultbtnFirstPage.setEnabled(false);
        ResultbtnPreviousPage.setEnabled(false);
        ResultbtnNextPage.setEnabled(false);
        ResultbtnLastPage.setEnabled(false);
    }
 
    //проверка количества страниц документа
    private boolean isMoreThanOnePage(PDFFile pdfFile)
    {
        return pdfFile.getNumPages() > 1;
    }

    //прослушивание вызова событий навигации по страницам документа
    private class PageNavigationListener implements MouseListener 
    {
        private final Navigation navigation;
        private final PDFFile file;
        private final int numpage;
        private String type;
 
        private PageNavigationListener(Navigation navigation, PDFFile file, String t) 
        {
            this.navigation = navigation;
            this.file = file;
            this.numpage = file.getNumPages();
            this.type = t;
        }
 
        @Override
        public void mouseClicked(MouseEvent e) 
        {
            if (file == null) 
            {
                return;
            }
 
            if (numpage <= 1)
            {
                disableAllNavigationButton();
            } else 
            {
                if (navigation == Navigation.FORWARD && hasNextPage(numpage))
                {
                    goPage(currentPage, numpage);
                }
 
                if (navigation == Navigation.GO_LAST_PAGE)
                {
                    goPage(numpage, numpage);
                }
 
                if (navigation == Navigation.BACKWARD && hasPreviousPage()) 
                {
                    goPage(currentPage, numpage);
                }
 
                if (navigation == Navigation.GO_FIRST_PAGE)
                {
                    goPage(FIRST_PAGE, numpage);
                }
 
                if (navigation == Navigation.GO_TO_PAGE)
                {
                    String text;
                    
                    if(type.equals("health"))
                        text = PageNumberList.getSelectedValue().toString();
                    else
                        text = ResultPageNumberList.getSelectedValue().toString();
                    
                    if (!isNullOrEmpty(text)) 
                    {
                        boolean isNumber = POSITIVE_DIGITAL.matchesAllOf(text);
                        if (isNumber)
                        {
                            int pageNumber = Integer.valueOf(text);
                            if (pageNumber >= 1 && pageNumber <= numpage)
                            {
                                goPage(Integer.valueOf(text), numpage);
                            }
                        }
                    }
                }
            }
        }
 
        private void goPage(int pageNumber, int numpages)
        {
            currentPage = pageNumber;
            boolean notFirstPage = isNotFirstPage();
            boolean notLastPage = isNotLastPage(numpages);
            if(type.equals("health"))
            {
                btnFirstPage.setEnabled(notFirstPage);
                btnPreviousPage.setEnabled(notFirstPage);
                btnNextPage.setEnabled(notLastPage);
                btnLastPage.setEnabled(notLastPage);
                PageNumberList.setSelectedValue(currentPage, true);

                HealthCardPagePanel.setIcon(new ImageIcon(GetImageFromPDF("health", currentPage, 0)));
            }
            else
            {
                ResultbtnFirstPage.setEnabled(notFirstPage);
                ResultbtnPreviousPage.setEnabled(notFirstPage);
                ResultbtnNextPage.setEnabled(notLastPage);
                ResultbtnLastPage.setEnabled(notLastPage);
                ResultPageNumberList.setSelectedValue(currentPage, true);

                ResultPagePanel.setIcon(new ImageIcon(GetImageFromPDF("result", currentPage, 0)));
            }
        }
 
        private boolean hasNextPage(int numpages)
        {
            return (++currentPage) <= numpages;
        }
 
        private boolean hasPreviousPage()
        {
            return (--currentPage) >= FIRST_PAGE;
        }
 
        private boolean isNotLastPage(int numpages)
        {
            return currentPage != numpages;
        }
 
        private boolean isNotFirstPage() 
        {
            return currentPage != FIRST_PAGE;
        }
        //<editor-fold defaultstate="collapsed" desc="comment">
        @Override
        public void mousePressed(MouseEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
//</editor-fold>
    }
 
    //получение текущей панели
    public javax.swing.JLabel getPagePanel() 
    {
        //if(panel == HealthCardPagePanel)
            return HealthCardPagePanel;
    }
    
    //получение изображения из pdf документа
    private Image GetImageFromPDF(String panel, int page, double delta)
    {
        Image image;
        PDFPage pdfPage;
        
        if(panel.equals("health"))
            if(page < HealthFile.getNumPages())
                pdfPage = HealthFile.getPage(page);
            else
                pdfPage = HealthFile.getPage(1);
        else
            if(page < ResultFile.getNumPages())
                pdfPage = ResultFile.getPage(page);
            else
                pdfPage = ResultFile.getPage(1);

        Rectangle2D r2d = pdfPage.getBBox();

        double width = r2d.getWidth();
        double height = r2d.getHeight();
       
        scale = scale + delta;
        if(scale >= 100)
        {
            ZoomInLabel.setEnabled(true);                
            ZoomOutLabel.setEnabled(false);
            scale = 100; 
        }
        else
            if(scale <= 50)
            {
                ZoomInLabel.setEnabled(false);
                ZoomOutLabel.setEnabled(true);
                scale = 50;
            }
        width /= scale;
        height /= scale;
        int res = Toolkit.getDefaultToolkit().getScreenResolution();
        width *= res;
        height *= res;

        return image = pdfPage.getImage ((int) width, (int) height, r2d, null, true, true);
    }

    //обработчик события отображения панели с медицинской картой
    private void HealthCardPanelComponentShown(java.awt.event.ComponentEvent evt)
    {                                               
        try 
        {
            if(!start)
            {
                long heapSize = Runtime.getRuntime().totalMemory();
                System.out.println("Heap Size = " + heapSize);

                try (FileChannel channel = rafHealth.getChannel())
                {
                    ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                    HealthFile = new PDFFile(buf);
                    
                    btnFirstPage.addMouseListener(new PageNavigationListener(Navigation.GO_FIRST_PAGE, HealthFile, "health"));
                    btnPreviousPage.addMouseListener(new PageNavigationListener(Navigation.BACKWARD, HealthFile, "health"));
                    btnNextPage.addMouseListener(new PageNavigationListener(Navigation.FORWARD, HealthFile, "health"));
                    btnLastPage.addMouseListener(new PageNavigationListener(Navigation.GO_LAST_PAGE, HealthFile, "health"));
                    PageNumberList.addMouseListener(new PageNavigationListener(Navigation.GO_TO_PAGE, HealthFile, "health"));
                    
                    setPDFFile(HealthFile, PageNumberList);
                    channel.close();
                }
                
                rafHealth.close();
            }
                FillPageList(HealthFile, healthPage);
                PageNumberList.setSelectedValue(currentPage, true);
                HealthCardPagePanel.setIcon(new ImageIcon(GetImageFromPDF("health", currentPage, 0)));

                start = true;
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //отображение файла с результатами анализов
    private void ShowTestFile()
    {                                               
         try 
        {
            long heapSize = Runtime.getRuntime().totalMemory();
            System.out.println("Heap Size = " + heapSize);
 
             try (FileChannel channel = rafTest.getChannel()) {
                 ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                 ResultFile = new PDFFile(buf);
                 
                 ResultbtnFirstPage.addMouseListener(new PageNavigationListener(Navigation.GO_FIRST_PAGE, ResultFile, "result"));
                 ResultbtnPreviousPage.addMouseListener(new PageNavigationListener(Navigation.BACKWARD, ResultFile, "result"));
                 ResultbtnNextPage.addMouseListener(new PageNavigationListener(Navigation.FORWARD, ResultFile, "result"));
                 ResultbtnLastPage.addMouseListener(new PageNavigationListener(Navigation.GO_LAST_PAGE, ResultFile, "result"));
                 ResultPageNumberList.addMouseListener(new PageNavigationListener(Navigation.GO_TO_PAGE, ResultFile, "result"));
                 
                 FillPageList(ResultFile, resPage);
                 setPDFFile(ResultFile, ResultPageNumberList);
                 ResultPageNumberList.setSelectedValue(currentPage, true);
                 
                 channel.close();
                 rafTest.close();
             }
            ResultPagePanel.setIcon(new ImageIcon(GetImageFromPDF("result", currentPage, 0)));            
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //активация кнопок навигации 
    public void setPDFFile(PDFFile file, JList list)
    {
        disableAllNavigationButton();
        
        boolean moreThanOnePage = isMoreThanOnePage(file);
        if(file.equals(HealthFile))
        {
            btnNextPage.setEnabled(moreThanOnePage);
            btnLastPage.setEnabled(moreThanOnePage);
        }
        else
        {
            ResultbtnNextPage.setEnabled(moreThanOnePage);
            ResultbtnLastPage.setEnabled(moreThanOnePage);
        }
        list.setSelectedValue(currentPage, true);
    }
    
    //заполнение списка страниц pdf файла
    private void FillPageList(PDFFile file, DefaultListModel<Integer> mod)
    {
        try
        {
            for(int i = 0; i < file.getNumPages(); i++)
            {
                mod.addElement(i+1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //обработчик клика по кнопке Save
    private void SaveButtonActionPerformed(ActionEvent evt) 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
         try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            String[] value = param.split("-");
            DocLName = value[0];
            patientLName = value[1];
            patientBDay = value[2];
            
            //INSERT INTO `konsultacii`(`date`, `walobi`, `objective`, 
            //            `diagnose`, `treatment`, `patient_id`, `doctor_id`) 
            //      VALUES ([value-2],[value-3],[value-4],[value-5],[value-6],
            //              [value-7],[value-8])
            
            
            String que = "saveResultOsmotra_" + 
                    sdf.format(Calendar.getInstance(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru")).getTime()) + "#"
                    + CurrentWalobyTextArea.getText() + "#" 
                    + CurrentObjectivnoTextArea.getText() + "#"
                    + CurrentDiagnozTextArea.getText() + "#"
                    + CurrentTreatmentTextArea.getText() + "#"
                    + patientLName + "-" + patientBDay + "#" + DocLName;
            
            //отправка запроса на сервер с фамилией доктора
            String answer =  sender.SendTextToServer(que);    
            
            if(answer.equals("OK"))
            {
                JOptionPane.showMessageDialog(this, "Данные сохранены", 
                    "Ошибка соединения", JOptionPane.INFORMATION_MESSAGE);
                
                CurrentWalobyTextArea.setText("");
                CurrentObjectivnoTextArea.setText("");
                CurrentDiagnozTextArea.setText("");
                CurrentTreatmentTextArea.setText("");
                
                FillHistoryVisitTable();
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
       
    //обработчик клика по кнопке Cancel
    private void CancelButtonActionPerformed(ActionEvent evt)
    {
        ResultFile = null;
        HealthFile = null;
        
        health = null;
        
        test = null;
        
        this.dispose();
    }          
}
