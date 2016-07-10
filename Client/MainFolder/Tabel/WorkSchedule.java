package umec.Tabel;

import TCPConnect.TCPSender;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class WorkSchedule extends JDialog
{
    //<editor-fold defaultstate="collapsed" desc="Объявление переменных">
    private javax.swing.JButton CancelButton;
    private javax.swing.JTable DateTable;
    private javax.swing.table.TableModel dateTableModel; 
    private javax.swing.JComboBox MonthComboBox;
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel Panel2;
    private javax.swing.JList PersonalList;
    private javax.swing.JButton SaveButton;
    private javax.swing.JComboBox YearComboBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private TCPSender sender;
    private String DocName;
    private Integer month, year;
    private DefaultTableModel model; 
    double valueStart = 0, valueEnd = 0;
//</editor-fold>
    
    //конструктор формы
    public WorkSchedule(java.awt.Frame parent, boolean modal, TCPSender sn)
    {
        super(parent, modal);
        this.sender = sn;
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Создание компонентов окна">                          
        private void initComponents() {

        Panel1 = new javax.swing.JPanel();
        MonthComboBox = new javax.swing.JComboBox();
        YearComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        PersonalList = new javax.swing.JList();
        Panel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DateTable = new javax.swing.JTable();
        SaveButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("График работы");

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
        this.setLocation((screenSize.width - frameSize.width) / 4, 
                (screenSize.height - frameSize.height) / 7);
        
        Panel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        MonthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]
        { "Месяц", "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
         "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"}));
        MonthComboBox.addActionListener((java.awt.event.ActionEvent evt) -> {
                MonthComboBoxActionPerformed(evt);
        });

        FillYearComboBox();
        YearComboBox.addActionListener((java.awt.event.ActionEvent evt) -> {
                YearComboBoxActionPerformed(evt);
        });

        FillPersonalList();
        PersonalList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PersonalListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(PersonalList);

        javax.swing.GroupLayout Panel1Layout = new javax.swing.GroupLayout(Panel1);
        Panel1.setLayout(Panel1Layout);
        Panel1Layout.setHorizontalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(YearComboBox, 0, 169, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(MonthComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Panel1Layout.setVerticalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(YearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        Panel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        DateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] 
            {
               "День недели", "Число", "Начало приема", "Конец приема"
            } ) 
        {
            boolean[] canEdit = new boolean []
            {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        
        dateTableModel = DateTable.getModel();
        dateTableModel.addTableModelListener(new TableModelListener(){ 
            @Override
            public void tableChanged(TableModelEvent e)
            {
                VerifyInput(e);
            }
        });
       
        jScrollPane2.setViewportView(DateTable);
        if (DateTable.getColumnModel().getColumnCount() > 0) {
            DateTable.getColumnModel().getColumn(0).setMinWidth(85);
            DateTable.getColumnModel().getColumn(0).setPreferredWidth(85);
            DateTable.getColumnModel().getColumn(0).setMaxWidth(85);
            DateTable.getColumnModel().getColumn(1).setMinWidth(50);
            DateTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            DateTable.getColumnModel().getColumn(1).setMaxWidth(120);
            DateTable.getColumnModel().getColumn(2).setPreferredWidth(120);
            DateTable.getColumnModel().getColumn(2).setMaxWidth(120);
            DateTable.getColumnModel().getColumn(3).setMinWidth(100);
            DateTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            DateTable.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        SaveButton.setText("Сохранить");
        SaveButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                SaveButtonActionPerformed(evt);
        });

        CancelButton.setText("Отмена");
        CancelButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                CancelButtonActionPerformed(evt);
        });

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Panel2Layout.createSequentialGroup()
                        .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(CancelButton)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    //проверка вводимых в таблицу значений на соответствие графику работы
    private void VerifyInput(TableModelEvent e)
    {
        if(e.getColumn() == 0 || e.getColumn() == 1)
            return;
        
        if(e.getType() == TableModelEvent.UPDATE)
        {
            SaveButton.setEnabled(true);
            try
            {
                if(e.getColumn() == 2)
                {
                    valueStart = Double.valueOf(dateTableModel.getValueAt(e.getFirstRow(), 2).toString().replace(',', '.'));
                    
                    if(valueStart < 8 || valueStart > 19) 
                    {
                        getToolkit().beep();
                        JOptionPane.showMessageDialog(DateTable, "Введено неправильное время начала работы", 
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                        SaveButton.setEnabled(false);
                    }
                }
                if(e.getColumn() == 3)
                {
                    valueEnd = Double.valueOf(dateTableModel.getValueAt(e.getFirstRow(), 3).toString().replace(',', '.'));
                    
                    if((valueEnd < 8 || valueEnd > 19) || (valueStart > valueEnd || valueStart == valueEnd)) 
                    {
                        getToolkit().beep();
                        JOptionPane.showMessageDialog(DateTable, "Введено неправильное время окончания работы", 
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                        SaveButton.setEnabled(false);
                    }
                }
            }
            catch(NumberFormatException | HeadlessException ex)
            {
                getToolkit().beep();
                JOptionPane.showMessageDialog(DateTable, "Неправильный формат времени", 
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                SaveButton.setEnabled(false);
            }
        }
    }    
    
    //заполнение списка фамилий докторов
    private void FillPersonalList() 
    {
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }

            String[] str = sender.SendTextToServer("getLName_штат").split("/");
            String tmp = "";
            
            for (String s : str)
            {
                String[] st = s.split("-");
                tmp = tmp + st[0] + ";";
            }
            
            final String[] doc = tmp.split(";");
            
            //отправка запроса на сервер для заполнения jList со списком профессий
            PersonalList.setModel(new javax.swing.AbstractListModel()
            {
                String[] strings = doc;
                public int getSize() { return strings.length; }
                public Object getElementAt(int i) { return strings[i]; }
            });
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
    
    //заполнение комбобокса с годами
    private void FillYearComboBox()
    {
        ArrayList<String> years_tmp = new ArrayList<String>();
        years_tmp.add("Год");
        for(int years = Calendar.getInstance().get(Calendar.YEAR); years<=2030; years++)
        {
            years_tmp.add(years+"");
        }
        YearComboBox.setModel(new javax.swing.DefaultComboBoxModel(years_tmp.toArray()));;
    }
    
    //обработчик выбора строки в комбобоксе с месяцами
    private void MonthComboBoxActionPerformed(ActionEvent evt)
    {
        JComboBox mc = (JComboBox)evt.getSource();
        month = mc.getSelectedIndex();
    }

    //обработчик выбора строки в комбобоксе с годами
    private void YearComboBoxActionPerformed(ActionEvent evt)
    {
        JComboBox yc = (JComboBox)evt.getSource();
        year = Integer.parseInt(yc.getSelectedItem().toString());
    }
    
    //обработчик клика по строке jList
    private void PersonalListMouseClicked(java.awt.event.MouseEvent evt)
    {   
        String value2, value3;
        DocName = (String) PersonalList.getSelectedValue();
        model = (DefaultTableModel) DateTable.getModel();
        
        if(model.getRowCount() > 0)
        {
            while (model.getRowCount() > 0)  model.removeRow(0);
        }
        
        try
        {
            //проверка соединения с сервером
            if(sender.SendTextToServer("Hello").equals(""))
            {
                sender.TryConnent();
            }
            
            if(month == null || year == null)
            {
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Выберите месяц и год", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int cur_month = Calendar.getInstance(new Locale("ru")).get(Calendar.MONTH)+1;
            int cur_day = Calendar.getInstance(new Locale("ru")).get(Calendar.DATE);
            int cur_year = Calendar.getInstance(new Locale("ru")).get(Calendar.YEAR);
            
            //отправка данных на сервер
            String[] answer = sender.SendTextToServer("getGrafic_" + DocName + "%" + month + year).split("%");

//<editor-fold defaultstate="collapsed" desc="следующий год или этот год и следующий месяц">
            if(year > cur_year || (year == cur_year && month > cur_month))
            {
                if(answer[0].equals("null"))
                {
                    List<String> date = getWeekDays(year, month);
                    for (String date1 : date) 
                    {
                        String[] tmp = date1.split("#");
                        if(Integer.parseInt(tmp[1]) < 10)
                            tmp[1] = "0" + tmp[1];

                        model.addRow(new Object[]{tmp[0], tmp[1], null, null});
                    }
                }
                else
                {
                    String[] work = answer[0].split("I");
                    for(String w : work)
                    {
                        String[] tmp = w.split(";");
                        
                        if(tmp[2].equals("null"))
                            value2 = null;
                        else
                            value2 = tmp[2];

                        if(tmp[3].equals("null"))
                            value3 = null;
                        else
                            value3 = tmp[3];

                        model.addRow(new Object[]{tmp[0], tmp[1], value2, value3});
                    }
                }
            }
//</editor-fold>
            
//<editor-fold defaultstate="collapsed" desc="текущий год и текущий месяц">
            if(year == cur_year)
            {
                if(month == cur_month)
                {
                   if(answer[0].equals("null"))
                    {
                        List<String> date = getWeekDays(year, month);
                        for (String date1 : date) 
                        {
                            String[] tmp = date1.split("#");
                            if(Integer.parseInt(tmp[1]) < 10)
                                tmp[1] = "0" + tmp[1];

                            if(cur_day < Integer.parseInt(tmp[1]))
                                model.addRow(new Object[]{tmp[0], tmp[1], null, null});
                        }
                    }
                    else
                    {
                        String[] work = answer[0].split("I");
                        for(String w : work)
                        {
                            String[] tmp = w.split(";");

                            if(cur_day < Integer.parseInt(tmp[1]))
                            {
                                if(tmp[2].equals("null"))
                                    value2 = null;
                                else
                                    value2 = tmp[2];

                                if(tmp[3].equals("null"))
                                    value3 = null;
                                else
                                    value3 = tmp[3];

                                model.addRow(new Object[]{tmp[0], tmp[1], value2, value3});
                            }
                        }
                    } 
                }
            }
//</editor-fold>
                    
//<editor-fold defaultstate="collapsed" desc="прошлый год или прошлый месяц">
            if((year == cur_year && month < cur_month) || (year < cur_year))
            {
                if(answer[0].equals("null"))
                {
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(this, "На выбранный месяц график работы доктора отсутствует", 
                    "Внимание", JOptionPane.INFORMATION_MESSAGE);
                
                    while(model.getRowCount() > 0) model.removeRow(0);
                }
                else
                {
                    DateTable.setEnabled(false);
                    String[] work = answer[0].split("I");
                    for(String w : work)
                    {
                        String[] tmp = w.split(";");

                        if(tmp[2].equals("null"))
                            value2 = null;
                        else
                            value2 = tmp[2];

                        if(tmp[3].equals("null"))
                            value3 = null;
                        else
                            value3 = tmp[3];

                        model.addRow(new Object[]{tmp[0], tmp[1], value2, value3});
                    }
                }
            }
//</editor-fold>
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
    
    //получение списка рабочих дней выбранного месяца и года включая субботу
    public List<String> getWeekDays(int year, int month) 
    {
        List<String> weekDays = new ArrayList<>();
        
	Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC+2:00"), new Locale("ru"));
       
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        
	for(int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++ )
        {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            switch (calendar.get(Calendar.DAY_OF_WEEK))
            {
                case Calendar.MONDAY:
                    weekDays.add("Пн#"
                    + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
                        break;
                case Calendar.TUESDAY:
                    weekDays.add("Вт#"
                    + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
                        break;
                case Calendar.WEDNESDAY:
                    weekDays.add("Ср#" 
                    + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
                        break;
                case Calendar.THURSDAY:
                    weekDays.add("Чт#"
                    + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
                        break;
                case Calendar.FRIDAY:
                    weekDays.add("Пт#"
                    + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
                        break;
                case Calendar.SATURDAY:
                    weekDays.add("Сб#"
                    + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
                        break;
            }
	}
        System.out.println("OK");
        return weekDays;
    }
    
    //обработчик нажатия на кнопку создания нового графика работы
    private void SaveButtonActionPerformed(ActionEvent evt) 
    {
        SaveWorkSchedule();
    }

    //обработчик нажатия на кнопку отмены
    private void CancelButtonActionPerformed(ActionEvent evt)
    {
        this.dispose();
    }

    //добавление в таблицу графика работы записи в строку с id работника графика работы на выбранный месяц
    private void SaveWorkSchedule()
    {        
        int columnCount = model.getColumnCount();
        int rowCount = model.getRowCount();
        String grafic = "", value = "";
        try
        {
            for(int i = 0; i < rowCount; i++)
            {
                for(int j = 0; j < columnCount; j++)
                {
                    if(j == (columnCount - 1))
                    {
                        if(model.getValueAt(i, j) != null)
                            grafic = grafic + model.getValueAt(i, j).toString().replace(',', '.') + "I";
                        else
                            grafic = grafic + "null" + "I";                            
                    }
                    else
                    {
                        if(model.getValueAt(i, j) != null)
                            grafic = grafic + model.getValueAt(i, j).toString().replace(',', '.') + ";";
                        else
                            grafic = grafic + "null" + ";";
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        String tmp = "addGrafic_" + DocName + "/" + grafic + "/"+ month.toString() + year;
        
        String answer = sender.SendTextToServer(tmp);
            
        switch(answer)
        {
            case "INS":
                JOptionPane.showMessageDialog(this, "Данные сохранены", 
                "Внимание", JOptionPane.WARNING_MESSAGE);
                break;
            case "NOINS":
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Ошибка сохранения данных", 
                "Ошибка", JOptionPane.ERROR_MESSAGE);
                break;
            case "UPD":
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Изменения сохранены", 
                "Внимание", JOptionPane.WARNING_MESSAGE);
                break;
            case "NOUPD":
                getToolkit().beep();
                JOptionPane.showMessageDialog(this, "Ошибка изменения данных", 
                "Ошибка", JOptionPane.WARNING_MESSAGE);
                break;
        }
    }
}