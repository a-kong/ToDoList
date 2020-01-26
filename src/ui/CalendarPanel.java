package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarPanel extends JPanel {

    private DefaultTableModel model;
    private Calendar cal = new GregorianCalendar();
    private JLabel label;

    public CalendarPanel() {
        label = new JLabel();
        label.setFont(new Font("Avenir Next Condensed", Font.PLAIN, 15));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton backOneMonth = new JButton("<");
        backOneMonth.setFont(new Font("Avenir Next Condensed Regular", Font.BOLD, 15));
        backOneMonth.setPreferredSize(new Dimension(25,15));
        backOneMonth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, -1);
                updateMonth();
            }
        });

        JButton forwardOneMonth = new JButton(">");
        forwardOneMonth.setFont(new Font("Avenir Next Condensed Regular", Font.BOLD, 15));
        forwardOneMonth.setPreferredSize(new Dimension(25, 15));
        forwardOneMonth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, +1);
                updateMonth();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.add(backOneMonth,BorderLayout.WEST);
        panel.add(label,BorderLayout.CENTER);
        panel.add(forwardOneMonth,BorderLayout.EAST);



        String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        model = new DefaultTableModel(null,columns);
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(200,200));
        JScrollPane pane = new JScrollPane(table);

        this.add(panel,BorderLayout.NORTH);
        this.add(pane,BorderLayout.CENTER);

        this.updateMonth();
    }

    void updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int year = cal.get(Calendar.YEAR);
        label.setText(month + " " + year);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        model.setRowCount(0);
        model.setRowCount(weeks);

        int i = startDay-1;
        for(int day=1;day<=numberOfDays;day++){
            model.setValueAt(day, i/7 , i%7 );
            i = i + 1;
        }

    }

}


// source: https://www.javacodex.com/Swing/Swing-Calendar