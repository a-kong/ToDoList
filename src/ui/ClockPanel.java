package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ClockPanel extends JPanel {

    private DigitPane hour;
    private DigitPane min;
    private DigitPane second;
    private JLabel[] separator;

    private int tick = 0;

    public ClockPanel() {
        setLayout(new GridBagLayout());

        hour = new DigitPane();
        min = new DigitPane();
        second = new DigitPane();
        separator = new JLabel[]{new JLabel(":"), new JLabel(":")};

        add(hour);
        add(separator[0]);
        add(min);
        add(separator[1]);
        add(second);

        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                hour.setValue(cal.get(Calendar.HOUR_OF_DAY));
                min.setValue(cal.get(Calendar.MINUTE));
                second.setValue(cal.get(Calendar.SECOND));

                int fontSize = 20;

                hour.setFont(new Font("Avenir Next Condensed", Font.BOLD, fontSize));
                min.setFont(new Font("Avenir Next Condensed", Font.BOLD, fontSize));
                second.setFont(new Font("Avenir Next Condensed", Font.BOLD, fontSize));

                if (tick % 2 == 1) {
                    separator[0].setText(" ");
                    separator[1].setText(" ");
                } else {
                    separator[0].setText(":");
                    separator[1].setText(":");
                }
                tick++;
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

}



class DigitPane extends JPanel {

    private int value;

    @Override
    public Dimension getPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        return new Dimension(fm.stringWidth("00"), fm.getHeight());
    }

    public void setValue(int aValue) {
        if (value != aValue) {
            int old = value;
            value = aValue;
            firePropertyChange("value", old, value);
            repaint();
        }
    }

    public int getValue() {
        return value;
    }

    protected String pad(int value) {
        StringBuilder sb = new StringBuilder(String.valueOf(value));
        while (sb.length() < 2) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String text = pad(getValue());
        FontMetrics fm = getFontMetrics(g.getFont());
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = ((getHeight()- fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(text, x, y);
    }
}





// source: https://stackoverflow.com/questions/15329869/making-a-digital-clock-in-java

