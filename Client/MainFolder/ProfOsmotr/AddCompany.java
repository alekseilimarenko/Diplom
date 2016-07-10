package umec.ProfOsmotr;

import TCPConnect.TCPSender;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.ConnectException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class AddCompany extends javax.swing.JDialog
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private javax.swing.JPanel AddCompanyPanel;
    private javax.swing.JButton AddNewCompanyButton;
    private javax.swing.JButton CancelButton;
    private javax.swing.JLabel CompanyAddressLabel;
    private javax.swing.JTextField CompanyAddressTextField;
    private javax.swing.JLabel CompanyListLabel;
    private javax.swing.JScrollPane CompanyListScrollPane;
    private javax.swing.JTable CompanyListTable;
    private javax.swing.JLabel CompanyNameLabel;
    private javax.swing.JTextField CompanyNameTextField;
    private javax.swing.JLabel CompanyPhoneLabel;
    private javax.swing.JTextField CompanyPhoneTextField;
    private javax.swing.JButton ModifyCompanyInfoButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel StateLabel;
    private TCPSender sender;
    private String[] companies;
    private DefaultTableModel model;
    boolean add, modify;
    //</editor-fold>
                      
    //конструктор окна
    public AddCompany(java.awt.Frame parent, boolean modal, TCPSender sn) 
    {
        super(parent, modal);
        this.sender = sn;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Создание компонентов окна">                          
    private void initComponents()
    {
        //установка Look&Fill
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
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
                (screenSize.height - frameSize.height) / 7);
        
        //создание окна без рамки
        this.setUndecorated(true);
        
        AddCompanyPanel = new javax.swing.JPanel();
        CompanyListLabel = new javax.swing.JLabel();
        CompanyNameLabel = new javax.swing.JLabel();
        CompanyNameTextField = new javax.swing.JTextField();
        CompanyAddressLabel = new javax.swing.JLabel();
        CompanyAddressTextField = new javax.swing.JTextField();
        CompanyPhoneLabel = new javax.swing.JLabel();
        CompanyPhoneTextField = new javax.swing.JTextField();
        SaveButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        CompanyListScrollPane = new javax.swing.JScrollPane();
        CompanyListTable = new javax.swing.JTable();
        AddNewCompanyButton = new javax.swing.JButton();
        ModifyCompanyInfoButton = new javax.swing.JButton();
        StateLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        AddCompanyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        CompanyListLabel.setText("Список компаний :");

        CompanyNameLabel.setText("Название компании : ");

        CompanyAddressLabel.setText("Адрес компании : ");

        CompanyPhoneLabel.setText("Контакные телефоны :");

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

        CompanyListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Название компании", "Телефон"}
        ) {
            boolean[] canEdit = new boolean [] {false, false};
            });
        CompanyListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CompanyListTableMouseClicked(evt);
            }
        });
        CompanyListScrollPane.setViewportView(CompanyListTable);
        if (CompanyListTable.getColumnModel().getColumnCount() > 0) {
            CompanyListTable.getColumnModel().getColumn(0).setResizable(false);
            CompanyListTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            CompanyListTable.getColumnModel().getColumn(1).setResizable(false);
            CompanyListTable.getColumnModel().getColumn(1).setPreferredWidth(5);
        }

        AddNewCompanyButton.setText("Добавить новое");
        AddNewCompanyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewCompanyButtonActionPerformed(evt);
            }
        });

        ModifyCompanyInfoButton.setText("Изменить");
        ModifyCompanyInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifyCompanyInfoButtonActionPerformed(evt);
            }
        });

        StateLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        StateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StateLabel.setText("Выберите дествие");
        StateLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout AddCompanyPanelLayout = new javax.swing.GroupLayout(AddCompanyPanel);
        AddCompanyPanel.setLayout(AddCompanyPanelLayout);
        AddCompanyPanelLayout.setHorizontalGroup(
            AddCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                .addGroup(AddCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CompanyListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(AddCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(AddCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CompanyAddressLabel)
                                    .addComponent(CompanyAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CompanyPhoneLabel)
                                    .addComponent(CompanyPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(CompanyNameLabel))))
                    .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(CompanyListLabel)))
                .addContainerGap())
            .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addGroup(AddCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(StateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                        .addComponent(AddNewCompanyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(ModifyCompanyInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        AddCompanyPanelLayout.setVerticalGroup(
            AddCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                .addGroup(AddCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CompanyListLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                        .addComponent(StateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddNewCompanyButton)
                            .addComponent(ModifyCompanyInfoButton))
                        .addGap(45, 45, 45)))
                .addGroup(AddCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddCompanyPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(CompanyNameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(CompanyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CompanyAddressLabel)
                        .addGap(18, 18, 18)
                        .addComponent(CompanyAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CompanyPhoneLabel)
                        .addGap(18, 18, 18)
                        .addComponent(CompanyPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(AddCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SaveButton)
                            .addComponent(CancelButton)))
                    .addComponent(CompanyListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddCompanyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddCompanyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        DisableComponents();
        
        FillCompanyList();
        
        pack();
    }// </editor-fold>                        

    private void DisableComponents()
    {
        CompanyNameTextField.setEditable(false); 
        CompanyAddressTextField.setEditable(false);
        CompanyPhoneTextField.setEditable(false);
        
        CompanyListTable.setEnabled(false);
        SaveButton.setEnabled(false);
    }
    
    private void FillCompanyList()
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            companies = sender.SendTextToServer("getCompanyList").split(";");
            
            model = (DefaultTableModel)CompanyListTable.getModel();
            
            if(model.getRowCount() > 0)
            {
                while (model.getRowCount() > 0)  model.removeRow(0);
            }

            for(String company : companies)
            {
                String[] value = company.split("-");
                model.addRow(new Object[]{value[0], value[2]});
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
    
    private void AddNewCompanyButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {                                                    
        CompanyNameTextField.setText(""); 
        CompanyAddressTextField.setText("");
        CompanyPhoneTextField.setText("");
        
        CompanyNameTextField.setEditable(true); 
        CompanyAddressTextField.setEditable(true);
        CompanyPhoneTextField.setEditable(true);
        
        StateLabel.setText("Добавление новой компании");
        
        ModifyCompanyInfoButton.setEnabled(true);
        AddNewCompanyButton.setEnabled(false);
        
        CompanyListTable.setEnabled(false);
        SaveButton.setEnabled(true);
        add = true;
        modify = false;
    }                                                   

    private void ModifyCompanyInfoButtonActionPerformed(java.awt.event.ActionEvent evt)
    {                                                        
        CompanyNameTextField.setText(""); 
        CompanyAddressTextField.setText("");
        CompanyPhoneTextField.setText("");
        
        CompanyNameTextField.setEditable(false);
        CompanyAddressTextField.setEditable(true);
        CompanyPhoneTextField.setEditable(true);
        
        StateLabel.setText("Изменение данных о компании");
        
        ModifyCompanyInfoButton.setEnabled(false);
        AddNewCompanyButton.setEnabled(true);
        
        CompanyListTable.setEnabled(true);
        SaveButton.setEnabled(true);
        add = false;
        modify = true;
    }                                                       
    
    private void CompanyListTableMouseClicked(java.awt.event.MouseEvent evt) 
    {          
        String comp_name = CompanyListTable.getValueAt(CompanyListTable.getSelectedRow(), 0).toString();
        
        CompanyNameTextField.setText(comp_name);
        CompanyNameTextField.setEditable(false);
        
        for(String company : companies)
        {
            String[] value = company.split("-");
            if(value[0].equals(comp_name))
            {
                CompanyAddressTextField.setText(value[1]);
                CompanyPhoneTextField.setText(value[2]);
            }
        }
    }                                        

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
        String answer = "";
        int phone = 0;

        try
        {
            phone = Integer.valueOf(CompanyPhoneTextField.getText());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Неверный формат телефонного номера", 
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }

            if(add == true)
            {
                if(CompanyNameTextField.getText().equals("") || 
                   CompanyAddressTextField.getText().equals("") ||
                        CompanyPhoneTextField.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(this, "Вы не заполнили все поля", 
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                answer = sender.SendTextToServer("addCompany_" + 
                    CompanyNameTextField.getText() + "#" +
                    CompanyAddressTextField.getText() + "#" + 
                    CompanyPhoneTextField.getText());
                
                if(answer.equals("OK"))
                {
                    JOptionPane.showMessageDialog(this, "Данные сохранены успешно", 
                        "Внимание", JOptionPane.INFORMATION_MESSAGE);

                    CompanyNameTextField.setText(""); 
                    CompanyAddressTextField.setText("");
                    CompanyPhoneTextField.setText("");

                    FillCompanyList();
                }
                else
                {
                    if(answer.equals("exist"))
                    {
                        if(JOptionPane.showConfirmDialog(this, "Компания с таким названием уже есть в базе, внести изменения?", 
                        "Внимание", JOptionPane.ERROR_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0)
                        {
                            ModifyButtonActionPerformed(false);
                        }
                    }
                    else
                    JOptionPane.showMessageDialog(this, "Ошибка сохранения данных", 
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if(modify == true)
            {
                String company;
                
                try
                {
                    company = CompanyListTable.getValueAt(CompanyListTable.getSelectedRow(), 0).toString();
                }
                catch(java.lang.ArrayIndexOutOfBoundsException exc)
                {
                    company = "null";
                }
                
                if(company.equals("null"))
                {
                     JOptionPane.showMessageDialog(this, "Выберите компанию для внесения изменений", 
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                ModifyButtonActionPerformed(true);
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

    private void ModifyButtonActionPerformed(boolean state) 
    {       
        String com_name;
        
        if(state)
            com_name = CompanyListTable.getValueAt(CompanyListTable.getSelectedRow(), 0).toString();
        else
            com_name = CompanyNameTextField.getText();
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            String answer = sender.SendTextToServer("modifyCompany_" + 
                    com_name + "#" + CompanyAddressTextField.getText() + "#" + 
                    CompanyPhoneTextField.getText());
            
            if(answer.equals("OK"))
            {
                JOptionPane.showMessageDialog(this, "Данные сохранены успешно", 
                    "Внимание", JOptionPane.INFORMATION_MESSAGE);
                
                FillCompanyList();
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

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {                                             
        this.dispose();
    }                                            
}
