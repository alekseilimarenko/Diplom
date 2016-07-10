package umec.ProfOsmotr;

import TCPConnect.TCPSender;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.ConnectException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CompanyList extends javax.swing.JDialog 
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private javax.swing.JButton AddEmployeeButton;
    private javax.swing.JLabel CompanyLabel;
    private javax.swing.JComboBox CompanyNameComboBox;
    private javax.swing.JLabel CompanyNameLabel;
    private javax.swing.JLabel EmployeeLabel;
    private javax.swing.JTable EmployeeTable;
    private javax.swing.JScrollPane EmployeeTableScrollPane;
    private javax.swing.JButton FileChooserButton;
    private javax.swing.JTextField FileTextField;
    private javax.swing.JPanel MainPanel;
    private TCPSender sender;
    private File file;
    private String company_name;
//</editor-fold>

    public CompanyList(java.awt.Frame parent, boolean modal, TCPSender sn)
    {
        super(parent, modal);
        this.sender = sn;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
        
        MainPanel = new javax.swing.JPanel();
        CompanyNameLabel = new javax.swing.JLabel();
        CompanyNameComboBox = new javax.swing.JComboBox();
        CompanyLabel = new javax.swing.JLabel();
        AddEmployeeButton = new javax.swing.JButton();
        EmployeeLabel = new javax.swing.JLabel();
        FileTextField = new javax.swing.JTextField();
        FileChooserButton = new javax.swing.JButton();
        EmployeeTableScrollPane = new javax.swing.JScrollPane();
        EmployeeTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        MainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        CompanyNameLabel.setText("Выберите компанию :");

        CompanyNameComboBox.addActionListener((java.awt.event.ActionEvent evt) -> {
                CompanyNameComboBoxActionPerformed(evt);} );
        
        CompanyLabel.setText("Название предприятия");

        AddEmployeeButton.setText("Форма");
        AddEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddEmployeeButtonActionPerformed(evt);
            }
        });

        EmployeeLabel.setText("Создание списка работников");

        FileTextField.setEditable(false);

        FileChooserButton.setText("Файл");
        FileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileChooserButtonActionPerformed(evt);
            }
        });

        EmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Фамилия", "Дата рождения"}
        ) {
            boolean[] canEdit = new boolean [] {false, false};
        });
        
        EmployeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EmployeeTableMouseClicked(evt);
            }
        });
        EmployeeTableScrollPane.setViewportView(EmployeeTable);
        if (EmployeeTable.getColumnModel().getColumnCount() > 0) {
            EmployeeTable.getColumnModel().getColumn(0).setResizable(false);
            EmployeeTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            EmployeeTable.getColumnModel().getColumn(1).setResizable(false);
            EmployeeTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        }

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CompanyNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CompanyNameComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(CompanyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AddEmployeeButton)
                        .addGap(33, 33, 33)
                        .addComponent(FileTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FileChooserButton)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(EmployeeLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(EmployeeTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        MainPanelLayout.setVerticalGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CompanyNameLabel)
                    .addComponent(CompanyNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(CompanyLabel)
                .addGap(19, 19, 19)
                .addComponent(EmployeeLabel)
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddEmployeeButton)
                    .addComponent(FileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FileChooserButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmployeeTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
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
            for(String value : companies)
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
        company_name = box.getSelectedItem().toString();
        CompanyLabel.setText(company_name);
        
        GetEmloyeeList(box.getSelectedItem().toString());
    }
    
    private void GetEmloyeeList(String c_name)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            String employees = sender.SendTextToServer("getEmployeeList_" + c_name);
            
            if(!employees.equals("null"))
            {
                String[] listEmployees = employees.split(";");
                DefaultTableModel tModel = (DefaultTableModel)EmployeeTable.getModel();
                for(String employee : listEmployees)
                {
                    String[] value = employee.split("-");
                    tModel.addRow(new Object[]{value[0], value[1]});
                }
            }
            if(employees.equals("null"))
            {    
                JOptionPane.showMessageDialog(this, "Список работников данного предприятия отсутствует", 
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
    
    private void FileChooserButtonActionPerformed(java.awt.event.ActionEvent evt)
    {                                                  
        JFileChooser fileopen = new JFileChooser();
        int res = fileopen.showDialog(this, "Открытие файла");
        if(res == JFileChooser.APPROVE_OPTION)
        {
            file = fileopen.getSelectedFile();
            FileTextField.setText(file.getName());
            
            FillEmployeeTable(file);
        }
        
        //<editor-fold defaultstate="collapsed" desc="парсинг файла xls">
        
//</editor-fold>
    }
    
    private void FillEmployeeTable(File f)
    {
        String fName = file.getName();
    }

    private void AddEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {                                                  
        new EmployeeInfo(this, true, sender).setVisible(true);
        System.out.println("OK");
        GetEmloyeeList(company_name);
    }                                                 

    private void EmployeeTableMouseClicked(java.awt.event.MouseEvent evt)
    {                                           
        // TODO add your handling code here:
    }                                          
}