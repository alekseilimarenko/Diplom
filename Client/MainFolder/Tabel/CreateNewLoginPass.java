package umec.Tabel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import TCPConnect.TCPSender;
import java.net.ConnectException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import umec.EncriptPass.EncriptClass;

public class CreateNewLoginPass extends javax.swing.JDialog 
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private javax.swing.JButton CancelButton;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel NewLoginLabel;
    private javax.swing.JTextField NewLoginTextField;
    private javax.swing.JLabel NewPassLabel;
    private javax.swing.JTextField NewPassTextField;
    private javax.swing.JButton SaveButton;
    TCPSender sender;                                            //объект соединения с сервером
//</editor-fold>
    
    //конструктор окна
    public CreateNewLoginPass(java.awt.Frame parent, boolean modal, TCPSender sn)
    {
        super(parent, modal);
        this.sender = sn;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Создание компонентов окна">                          
    private void initComponents()
    {
        MainPanel = new javax.swing.JPanel();
        NewLoginLabel = new javax.swing.JLabel();
        NewLoginTextField = new javax.swing.JTextField();
        NewPassLabel = new javax.swing.JLabel();
        NewPassTextField = new javax.swing.JTextField();
        SaveButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Создание пароля и логина для нового работника");
        
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
        this.setLocation((screenSize.width - frameSize.width) / 3, 
                (screenSize.height - frameSize.height) / 3);
        
        NewLoginLabel.setText("Введите новый логин(только буквы)");
        
        NewLoginTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                LoginTextFieldKeyTyped(evt);
            }
        });
        
        NewLoginTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                LoginTextFieldFocusLost(evt);
            }
        });

        NewPassLabel.setText("Введите новый пароль(4 цифры)");
        
        NewPassTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt){
                PassTextFieldKeyTyped(evt);
            }
        });

        SaveButton.setText("Сохранить");
        SaveButton.addActionListener((ActionEvent evt) -> {
            SaveButtonActionPerformed(evt);
        });
        
        CancelButton.setText("Отмена");
        CancelButton.addActionListener((ActionEvent evt) -> {
            CancelButtonActionPerformed(evt);
        });
        

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(SaveButton)
                        .addGap(155, 155, 155)
                        .addComponent(CancelButton))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NewPassLabel)
                            .addComponent(NewLoginLabel))
                        .addGap(18, 18, 18)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NewLoginTextField)
                            .addComponent(NewPassTextField))))
                .addGap(25, 25, 25))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NewLoginLabel)
                    .addComponent(NewLoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NewPassLabel)
                    .addComponent(NewPassTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(CancelButton))
                .addGap(25, 25, 25))
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

        pack();
    }// </editor-fold>                        

    //контроль вводимых символов в поле логин
    private void LoginTextFieldKeyTyped(KeyEvent evt) 
    {
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE))
        {
            getToolkit().beep();
            evt.consume();
        }
    }
    
    //обработчик потери фокуса текстового поля с логином
    private void LoginTextFieldFocusLost(java.awt.event.FocusEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            if(NewLoginTextField.getText().trim().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Логин не корректен", 
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                getToolkit().beep();
                return;
            }    
            
            VerifyLogin();
        }
        catch(ConnectException conExc)
        {
            JOptionPane.showMessageDialog(this, "Связь с сервером отсутствует", 
                    "Ошибка соединения", JOptionPane.ERROR_MESSAGE);
            getToolkit().beep();
            System.out.println(conExc.getMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }  
    
    //контроль вводимых символов в поле пароль
    private void PassTextFieldKeyTyped(KeyEvent evt) 
    {
        char c = evt.getKeyChar();
        
        if(!Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE))
        {
            getToolkit().beep();
            evt.consume();
        }  
    }
    
    //проверка присутствия введенного логина в базе данных
    private void VerifyLogin()
    {
        String answer = sender.SendTextToServer("verifyLogin_" + NewLoginTextField.getText().trim());
        
        if(!answer.equals("null"))
        {
            JOptionPane.showMessageDialog(this, "Введенный логин уже зарегистирован в базе", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            SaveButton.setEnabled(false);
            NewPassTextField.setEnabled(false);
            getToolkit().beep();
        }
        else
        {
            SaveButton.setEnabled(true);
            NewPassTextField.setEnabled(true);
        }
    }
        
    //валидация введенного пароля
    private boolean VerifyPassword()
    {
        //проверка длины введенных в поле пароль символов
        String numPattern = "\\d{4}";
        String num = NewPassTextField.getText();
        
        Pattern pattern = Pattern.compile(numPattern);
        Matcher matcher = pattern.matcher(num);
        
        if(!matcher.matches())
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog(this, "Неправильная длина пароля", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
            return true;  
    }
    
    //обработчик нажатия на кнопку сохранения данных
    private void SaveButtonActionPerformed(ActionEvent evt) 
    {
        if(!VerifyPassword())
        {
            SaveButton.setEnabled(false);
            return;
        }
            
        SaveButton.setEnabled(true);
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            CreateNewLogin();
        }
        catch(ConnectException conExc)
        {
            JOptionPane.showMessageDialog(this, "Связь с сервером отсутствует", 
                    "Ошибка соединения", JOptionPane.ERROR_MESSAGE);
            getToolkit().beep();
            System.out.println(conExc.getMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //обаботчик нажатия на кнопку отмены
    private void CancelButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    //функция отправки данных на сервер для сохранения в таблице loginpass
    private void CreateNewLogin()
    {
        if(NewPassTextField.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Пароль не корректен", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            getToolkit().beep();
            return;
        }    
        
        //шифрование пароля
        String encPass = EncriptClass.md5(NewPassTextField.getText().trim());
        
        String answer = sender.SendTextToServer("createLogin_" +
                                        NewLoginTextField.getText().trim() + "/" +
                                        encPass);
        
        if(answer.equals("OK"))
        {
            JOptionPane.showMessageDialog(this, "Логин и пароль внесены в базу", 
                    "Внимание", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
        
        if(answer.equals("NO"))
        {
            JOptionPane.showMessageDialog(this, "Ошибка внесения данных в базу", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            getToolkit().beep();
        }
        
        if(answer.equals("loginExist"))
        {
            JOptionPane.showMessageDialog(this, "Введенный логин уже зарегистрирован в базе", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            getToolkit().beep();
        }
    }
}
