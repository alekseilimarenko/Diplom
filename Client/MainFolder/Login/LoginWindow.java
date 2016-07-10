package umec.Login;

import TCPConnect.TCPSender;
import java.awt.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import umec.EncriptPass.EncriptClass;
import umec.MainWindow.MainWindow;

public class LoginWindow extends JFrame
{
    //<editor-fold defaultstate="collapsed" desc="объявление переменных">
    private JButton Cancel_Button;
    private JTextField LoginField;
    private JLabel LoginLbl;
    private JPanel MainPanel;
    private JButton Ok_Button;
    private JPasswordField PasswordField;
    private JLabel PasswordLbl;
    private javax.swing.JLabel IconLabel;
    private static String Title = "Украинский Медицинский Экспертный Центр";
    private String answer = "";
    private TCPSender sender;
    
    //</editor-fold>
    
    //конструктор формы
    public LoginWindow(String title)
    {
        super(title);
        initComponents();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Создание компонентов окна">
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
        
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
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
        
        //создание окна без рамки
        this.setUndecorated(true);
        
        //обработчик закрытия окна
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent event)
            {                
                dispose();
            }
        });
        
        //установка иконки главного окна
        ImageIcon mainIcon = new ImageIcon(getToolkit().createImage("images/icon.png"));
        Image img = mainIcon.getImage();
        this.setIconImage(img);
        
        MainPanel = new javax.swing.JPanel();
        LoginLbl = new javax.swing.JLabel();
        LoginField = new javax.swing.JTextField("Рудай");
        PasswordLbl = new javax.swing.JLabel();
        Cancel_Button = new javax.swing.JButton();
        Ok_Button = new javax.swing.JButton();
        PasswordField = new javax.swing.JPasswordField("");
        IconLabel = new JLabel(new ImageIcon(getClass().getResource("icon.png")));

        PasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        
        MainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Вход в систему"));

        LoginLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LoginLbl.setText("Логин");

        LoginField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        LoginField.setToolTipText("Введите логин");
        
        LoginField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NewLoginTextFieldFocusLost(evt);
            }
        });
        
        LoginField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                LoginTextFieldKeyTyped(evt);
            }
        });
        
        PasswordLbl.setText("Пароль");

        Ok_Button.setText("Войти");
        
        Ok_Button.addActionListener((java.awt.event.ActionEvent evt) -> {
                Ok_ButtonActionPerformed(evt);
        });
        
        Cancel_Button.setText("Отмена");
        Cancel_Button.addActionListener((java.awt.event.ActionEvent evt) -> {
                Cancel_ButtonActionPerformed(evt);
        });

        PasswordField.setForeground(new java.awt.Color(204, 204, 204));
        PasswordField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        PasswordField.setToolTipText("Введите пароль");
        PasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt){
                PassTextFieldKeyTyped(evt);
            }
        });
        
        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Ok_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancel_Button))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(10)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LoginLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PasswordLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LoginField, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(PasswordField))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginLbl)
                    .addComponent(LoginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordLbl)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGap(10, 10, 10)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel_Button)
                    .addComponent(Ok_Button))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(IconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        this.setVisible(true);
        
        pack();
    }
//</editor-fold>
       
    //проверка вводимых букв
    private void LoginTextFieldKeyTyped(KeyEvent evt) 
    {
        char c = evt.getKeyChar();
        
        if(Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE))
        {
            getToolkit().beep();
            evt.consume();
        }
    }

    //проверка вводимых цифр
    private void PassTextFieldKeyTyped(KeyEvent evt) 
    {
        char c = evt.getKeyChar();
        
        if(c == KeyEvent.VK_ENTER)
            return;
        
        if(!Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE))
        {
            getToolkit().beep();
            evt.consume();
        }  
    }
    
    //обработчик потери фокуса текстового пол с логином
    private void NewLoginTextFieldFocusLost(java.awt.event.FocusEvent evt)
    {
        VerifyLogin();
    }
    
    //проверка присутствия введенного логина в базе
    private void VerifyLogin()
    {
        sender = new TCPSender();
        
        try
        {
            //проверка соединения с сервером
            if(!sender.TryConnent()) throw new ConnectException();
            
            if(LoginField.getText().trim().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Введенный логин не корректен", 
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String pass = sender.SendTextToServer("verifyLogin_" + LoginField.getText());

            if(pass.equals("null"))
            {
                JOptionPane.showMessageDialog(this, "Введенный логин отсутствует в базе", 
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                Ok_Button.setEnabled(false);
                PasswordField.setEnabled(false);
            }
            else
            {
                Ok_Button.setEnabled(true);
                PasswordField.setEnabled(true);            
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
    
    //валидация введеного пароля
    private boolean VerifyPassword()
    {
        //проверка длины введенных в поле пароль символов
        String numPattern = "\\d{4}";
        String num = PasswordField.getText();
        
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
     
    //обработчик нажатия кнопки ОК
    private void Ok_ButtonActionPerformed(ActionEvent evt) 
    {
        //проверка на пустые поля логина и пароля
        if(LoginField.getText().trim().equals("") && PasswordField.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Введите логин и пароль", 
                    "Ошибка введения логина или пароля", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!VerifyPassword())
        {
            Ok_Button.setEnabled(false);
            return;
        }
        
        VerifyAccess(LoginField.getText().trim(), PasswordField.getText().trim());
    }
    
    //обработчик клика на клавишу Enter
    private void formKeyPressed(KeyEvent evt)
    {
        //проверка на пустые поля логина и пароля
        if(LoginField.getText().trim().equals("") && PasswordField.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Введите логин и пароль", 
                    "Ошибка введения логина или пароля", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(evt.getKeyCode() == 10)
        {    
            if(VerifyPassword())
            {
                VerifyAccess(LoginField.getText().trim(), PasswordField.getText().trim());
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Неправильная длина пароля", 
                    "Ошибка введения логина или пароля", 
                    JOptionPane.ERROR_MESSAGE);
                Ok_Button.setEnabled(false);
            }
        }
    }
    
    //обработчик нажатия кнопки Отмена
    private void Cancel_ButtonActionPerformed(ActionEvent evt) 
    {                                              
        this.dispose();
        System.exit(0);
    }        
   
    //отправка запроса на сервер с логином и паролем
    private void VerifyAccess(String log, String pass)
    {
        try
        {
            //проверка соединения с сервером
            if(!sender.TryConnent()) throw new ConnectException();
            
            //шифрование пароля
            String encPass = EncriptClass.md5(pass);
            
            //отправка на сервер логина и пароля для авторизации
            answer = sender.SendTextToServer("login_" + log + "/" +
                    encPass);
            
            //проверка полученных с сервера данных
            switch (answer)
            {
                case "busy":
                    JOptionPane.showMessageDialog(this, 
                        "Пользователь с таким логином уже вошел в систему", 
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                    break;
                case "pass_null":
                    JOptionPane.showMessageDialog(this, 
                        "Вы ввели неверный пароль", 
                        "Ошибка авторизации", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    new MainWindow(Title, answer, LoginField.getText(), sender).setVisible(true);
                    this.setVisible(false);
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
    
    //старт приложения
    public static void main(String[] args)
    {
        //создание окна авторизации
        new LoginWindow(Title);
    }
}
