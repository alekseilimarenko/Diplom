package umec.MainWindow;

//<editor-fold defaultstate="collapsed" desc="import">
import umec.Login.LoginWindow;
import umec.Registry.*;
import umec.Priem.*;
import umec.Obsledovanie.*;
import umec.Tabel.*;
import umec.ProfOsmotr.*;
import javax.swing.JFrame;
import TCPConnect.TCPSender;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.ConnectException;
import javax.swing.*;
import javax.swing.border.Border;
//</editor-fold>

public class MainWindow extends JFrame
{
    //<editor-fold defaultstate="collapsed" desc="объявление переменных ">
    JDesktopPane desktop;
    String access = "";                             //контроль доступа к ресурсам
    JPanel contentPanel;                            //главная панель окна
    BorderLayout borderLayuot = new BorderLayout(); //рамка для строки состояния
    JLabel statusBar = new JLabel(" ");             //строка статуса
    JMenu menuRegistry;                             //кнопка меню Регистратура
    JMenuItem NewPatientRegistry;                   //кнопка регистрации нового пациента
    JMenuItem ModifyPatientInfo;                    //кнопка редактирования даных о пациенте
    JMenuItem SearchPatientRegistry;                //кнопка поиска пациента
    JMenu menuPriem;                                //кнопка меню Прием
    JMenuItem PriemToday;                           //кнопка приема нового пациента
    JMenuItem SearchPatientPriem;                   //кнопка поиска пациента на прием
    JMenu menuProfOsmotr;                           //кнпока меню Профосмотр
    JMenuItem addCompany;                           //кнопка меню добавления предприятия
    JMenuItem CompanyList;                          //список предприятий
    JMenuItem SearchPerson;                         //поиск работника
    JMenu menuProfOsmotrList;                       //кнопка меню Картотека профосмотра
    JMenu menuTesting;                              //меню внесения анализов
    JMenuItem AddResultTesting;                     //лабораторные исследования
    JMenu menuKadry;                                //кнопка меню Табель
    JMenuItem NewLogin;                     //кнопка добавления в базу нового пароля и логина 
    JMenu NewPersonCard;                      //мкню создания нового работника и редактирования данных                            
    JMenuItem CreateNewPerson;                      //кнопка регистрации нового работника
    JMenuItem ChangePerson;                         //кнопка редактирования данных о работнике
    JMenuItem Sertificate;                          //внесение сертификатов докторов в базу
    JMenuItem WorkList;                         //кнопка ввода графика работы/приема
    JMenuItem CreateNewWorkListTable;               //создание таблицы графика приема на текущий месяц
    JMenuItem FillWorkListTable;                    //заполнение таблицы графика приема
    JMenuItem Tabel;                        //кнопка ввода табеля работы
    JMenu menuEntry;                                //кнопка меню Выход
    JMenuItem ChangeUser;                           //смена пользователя
    JMenuItem CloseApp;                             //завершение работы приложения
    TCPSender sender;                               //объект соединения с сервером
    String login;
    File folder;
    //</editor-fold>
    
    //конструктор формы
    public MainWindow(String title, String str, String log, TCPSender sn)
    {
        super(title);
        login = log;
        access = str;
        sender = sn;
        initComponents();
    }
    
    //<editor-fold defaultstate="collapsed" desc="создание строки меню">
    protected JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        //создание кнопок подменю
        menuRegistry                = new JMenu("Регистратура");
        NewPatientRegistry          = new JMenuItem("Новый пациент");
        ModifyPatientInfo           = new JMenuItem("Редактирование данных о пациенте");
        SearchPatientRegistry       = new JMenuItem("Поиск пациента");
        menuPriem                   = new JMenu("Прием");
        PriemToday                  = new JMenuItem("Прием на сегодня");
        SearchPatientPriem          = new JMenuItem("Поиск пациента");
        menuTesting                 = new JMenu("Обследования");
        AddResultTesting            = new JMenuItem("Ввести результаты обследования");
        menuProfOsmotr              = new JMenu("Профосмотр");
        addCompany                  = new JMenuItem("Добавить предприятие");
        CompanyList                 = new JMenuItem("Список работников");
        menuProfOsmotrList          = new JMenu("Картотека профосмотра");
        menuKadry                   = new JMenu("Кадры");
        NewLogin                    = new JMenuItem("Новый логин/пароль");
        NewPersonCard               = new JMenu("Учетная карточка");
        CreateNewPerson             = new JMenuItem("Заполнение новой учетной карточки");
        ChangePerson                = new JMenuItem("Редактирование данных о работнике");
        Sertificate                 = new JMenuItem("Внесение сертификатов");
        WorkList                    = new JMenuItem("График работы");
        Tabel                       = new JMenuItem("Табель выходов");
        menuEntry                   = new JMenu("Выход");
        ChangeUser                  = new JMenuItem("Смена пользователя");
        CloseApp                    = new JMenuItem("Завершение работы");

        //добавление кнопок меню на строку меню
        menuBar.add(menuRegistry);
        menuRegistry.add(NewPatientRegistry);
        menuRegistry.add(ModifyPatientInfo);
        menuRegistry.add(SearchPatientRegistry);

        menuBar.add(menuPriem);
        menuPriem.add(PriemToday);
        menuPriem.add(SearchPatientPriem);

        menuBar.add(menuTesting);
        menuTesting.add(AddResultTesting);
        
        menuBar.add(menuProfOsmotr);
        menuProfOsmotr.add(addCompany);
        menuProfOsmotr.add(CompanyList);

        menuBar.add(menuProfOsmotrList);

        menuBar.add(Box.createHorizontalGlue());
 
        menuBar.add(menuKadry);
        menuKadry.add(NewLogin);
        menuKadry.add(NewPersonCard);
        NewPersonCard.add(CreateNewPerson);
        NewPersonCard.add(ChangePerson);
        NewPersonCard.add(Sertificate);
        menuKadry.add(WorkList);
        menuKadry.add(Tabel);
        
        menuBar.add(menuEntry);
        menuEntry.add(ChangeUser);
        menuEntry.add(CloseApp);

        //обработчики нажатия кнопок меню
        NewPatientRegistry.addActionListener((ActionEvent evt) -> {
            NewPatientRegistryActionPerformed(evt); 
        });
        
        ModifyPatientInfo.addActionListener((ActionEvent evt) -> {
            ModifyPatientInfoActionPerformed(evt); 
        });

        SearchPatientRegistry.addActionListener((ActionEvent evt) -> {
            SearchPatientRegistryActionPerformed(evt); 
        });

        PriemToday.addActionListener((ActionEvent evt) -> {
            PriemTodayActionPerformed(evt);
        });

        SearchPatientPriem.addActionListener((ActionEvent evt) -> {
            SearchPatientPriemActionPerformed(evt);
        });
        
        AddResultTesting.addActionListener((ActionEvent evt) -> {
            MenuTestingActionListener(evt);
        });
        
        addCompany.addActionListener((ActionEvent evt) -> {
            addCompanyActionPerformed(evt);
        });

        CompanyList.addActionListener((ActionEvent evt) -> {
            CompanyListActionPerformed(evt);
        });

        menuProfOsmotrList.addActionListener((ActionEvent evt) -> {
            menuProfOsmotrListActionPerformed(evt);
        });  

        NewLogin.addActionListener((ActionEvent evt) -> {
            NewLoginMenuItemActionParformed(evt);
        });

        CreateNewPerson.addActionListener((ActionEvent evt) -> {
            NewPersonMenuItemActionPerformed(evt);
        }); 
        
        ChangePerson.addActionListener((ActionEvent evt) -> {
            ChangePersonActionPerformed(evt);
        });
        
        Sertificate.addActionListener((ActionEvent evt) -> {
            SertificateActionPerformed(evt);
        });
        
        WorkList.addActionListener((ActionEvent evt) ->{
            CreateNewWorkListTableActionPerformed(evt);
        });

        Tabel.addActionListener((ActionEvent evt) -> {
            TabelMenuItemActionPerformed(evt);
        });

        ChangeUser.addActionListener((ActionEvent evt) -> {
            ChangeUserActionPerformed(evt);
        }); 

        CloseApp.addActionListener((ActionEvent evt) -> {
            CloseAppActionPerformed(evt); 
        });

        return menuBar;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Создание компонентов окна">
     private void initComponents()
    {
        //установка размеров окна
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(0,0);
        this.setSize(new Dimension(screenSize.width, screenSize.height - 40));
        
        //установка иконки главного окна
        ImageIcon mainIcon = new ImageIcon(getToolkit().createImage("images/icon.png"));
        Image img = mainIcon.getImage();
        this.setIconImage(img);
        
        //Set up the GUI.
        desktop = new JDesktopPane(); //a specialized layered pane

        setContentPane(desktop);
        setJMenuBar(createMenuBar());

        //Make dragging a little faster but perhaps uglier.
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        
        //установка Look&Fill
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //обработчик закрытия окна
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event)
            {    
                CloseAppActionPerformed(null);
            }
        });
        
        //создание строки состояния
        Border borderStatusBar = BorderFactory.createEtchedBorder(Color.WHITE, new Color(178, 178, 178));
        statusBar.setBorder(borderStatusBar);
        statusBar.setOpaque(true);
        desktop.add(statusBar, BorderLayout.SOUTH);
        
        VerifyLogin(); 
        
        //sender.OnLine();
        
        folder = new File("Patients/");
        folder.mkdir();
    }
//</editor-fold>
   
    //проверка группы доступа
    private void VerifyLogin()
    {
        switch (access)
        {
            case "1":
                menuRegistry.setEnabled(true);
                menuPriem.setEnabled(true);
                menuProfOsmotr.setEnabled(true);
                menuProfOsmotrList.setEnabled(true);
                menuTesting.setEnabled(true);
                menuKadry.setEnabled(true);
                break;
            case "2":
                menuRegistry.setEnabled(false);
                menuPriem.setEnabled(false);
                menuProfOsmotr.setEnabled(false);
                menuProfOsmotrList.setEnabled(false);
                menuTesting.setEnabled(false);
                menuKadry.setEnabled(true);
                break;
           case "3":
                menuRegistry.setEnabled(false);
                menuPriem.setEnabled(true);
                menuProfOsmotr.setEnabled(true);
                menuProfOsmotrList.setEnabled(true);
                menuTesting.setEnabled(false);
                menuKadry.setEnabled(false);
                break;
            case "4":
                menuRegistry.setEnabled(false);
                menuPriem.setEnabled(false);
                menuProfOsmotr.setEnabled(false);
                menuProfOsmotrList.setEnabled(false);
                menuTesting.setEnabled(true);
                menuKadry.setEnabled(false);
                break;
            case "5":
                menuRegistry.setEnabled(true);
                menuPriem.setEnabled(false);
                menuProfOsmotr.setEnabled(false);
                menuProfOsmotrList.setEnabled(false);
                menuTesting.setEnabled(false);
                menuKadry.setEnabled(false);
                break;
        }
    }
    
    //обработчик нажатия на кнопку добавления нового пациента в регистратуре
    private void NewPatientRegistryActionPerformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            new NewPatientRegistry(this, true, sender).setVisible(true);

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
    
    //обработчик нажатия на кнопку изменеия информации о пациенте
    private void ModifyPatientInfoActionPerformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            new ModifyPatientsInfo(this, true, sender).setVisible(true);

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
    
    //обработчик нажатия на кнопку поиска пациента в регистратуре
    private void SearchPatientRegistryActionPerformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            new SearchPatientsRegistry(this, true, sender).setVisible(true);
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
    
    //обработчик нажатия на кнопку добавления нового пациента на приеме
    private void PriemTodayActionPerformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            new PriemToday(this, true, sender, login).setVisible(true);
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
    
    //обработчик нажатия на кнопку поиска пациента на приеме
    private void SearchPatientPriemActionPerformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            new PriemSearchPatients(this, true, sender, login).setVisible(true);
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
    
    //обработчик клика на кнопку внесения результатов обследования в базу
     private void MenuTestingActionListener(ActionEvent evt)
     {
         try
         {
             //проверка соединения с сервером
             if(sender.SendTextToServer("Hello").equals(""))
             {
                 sender.TryConnent();
             }
            
             new AddResult(this, true, sender).setVisible(true);
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
    
    //обработчик нажатия на кнопку добавления предприятия для профосмотра
    private void addCompanyActionPerformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            new AddCompany(this, true, sender).setVisible(true);
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
    
    //обработчик нажатия на кнопку поиска предприятия для профосмотра
    private void CompanyListActionPerformed(ActionEvent evt)
    {
        try
        {
           //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            new CompanyList(this, true, sender).setVisible(true);
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
    
    //обработчик нажатия на кнопку Картотека профосмотра
    private void menuProfOsmotrListActionPerformed(ActionEvent evt)
    {
        try
        {
           //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
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
    
    //обработчик нажатия на кнопку внесения нового пароля и логина в базу
    private void NewLoginMenuItemActionParformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            new CreateNewLoginPass(this, true, sender).setVisible(true);
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
        
    //обработчик нажатия на кнопку ввода данных нового работника
    private void NewPersonMenuItemActionPerformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            new NewPerson(this, true, sender).setVisible(true);
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
    
    //обработчик нажатия на кнопку изменения данных работника
    private void ChangePersonActionPerformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //вызов окна для редактирования данных о работнике
            new ModifyPersonalInfo(this, true, sender).setVisible(true);
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
    
    //обработчик нажатия на кнопку внесения сертификатов в базу данных
    private void SertificateActionPerformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //вызов окна для внесения сертификатов докторов в базу данных
            new Sertificates(this, true, sender).setVisible(true);
            
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
             
    //обработчик нажатия кнопки создания новой таблицы графика приема
    private void CreateNewWorkListTableActionPerformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //вызов окна для создания графика работы на новый месяц
            new WorkSchedule(this, true, sender).setVisible(true);
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
    
    //обработчик нажатия на кнопку ввода табеля работы
    private void TabelMenuItemActionPerformed(ActionEvent evt)
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            //окно выавода графика работы персонала на текущий месяц
            
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
        
    //обработчик нажатия на кнопку смена пользователя
    private void ChangeUserActionPerformed(ActionEvent evt)
    {
        new LoginWindow("Украинский Медицинский Экспертный Центр");
        sender.AppExit("exit_" + login);
        sender = null;
        folder.delete();
        this.setVisible(false);
    }
    
     //обработчик нажатия на кнопку завершение работы
    private void CloseAppActionPerformed(ActionEvent evt)
    {
        sender.AppExit("exit_" + login);
        sender = null;
        folder.delete();        
        System.exit(0);
    }
}
