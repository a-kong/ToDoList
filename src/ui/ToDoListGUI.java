package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.Font;

public class ToDoListGUI extends JFrame {
    private JLabel label;
    private int fontSize;
    private int yourTasksFontSize;
    private int windowHeight;
    private int windowWidth;
    private int defaultX;

    private Color backgroundColour;

    private NotesPanel notesPanel;
    private CalendarPanel calendarPanel;
    private ToDoListPanel toDoListPanel;
    private UrgentToDoListPanel urgentToDoListPanel;
    private ClockPanel clockPanel;


    public ToDoListGUI()
    {
        super("To Do List");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        windowHeight = 700;
        windowWidth = 1000;
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);
        backgroundColour = new Color(235, 235, 253);
        getContentPane().setBackground(backgroundColour);


        addClockPanel();
        addText();
        addNotesPanel();
        addCalendarPanel();
        addToDoListPanel();
        addUrgentToDoListPanel();


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }



    private void addClockPanel() {
        clockPanel = new ClockPanel();
        clockPanel.setBounds(20, 50, 100, 30);
        clockPanel.setBackground(backgroundColour);
        add(clockPanel);
    }

    private void addUrgentToDoListPanel() {
        JLabel urgentToDoListLabel = new JLabel("must do now!!!: ");
        urgentToDoListLabel.setFont(new Font("Avenir", Font.BOLD, fontSize));
        urgentToDoListLabel.setBounds(680, 97, 100, 50);
        urgentToDoListPanel = new UrgentToDoListPanel();
        add(urgentToDoListLabel);
        add(urgentToDoListPanel);
        urgentToDoListPanel.setBounds(680, 130, 250, 500);
    }

    private void addToDoListPanel() {
        JLabel todoListLabel = new JLabel("to do: ");
        todoListLabel.setFont(new Font("Avenir", Font.BOLD, fontSize));
        todoListLabel.setBounds(350, 97, 100, 50);
        toDoListPanel = new ToDoListPanel();
        add(todoListLabel);
        toDoListPanel.setBounds(350, 130, 250, 500);
        add(toDoListPanel);
    }

    private void addText() {
        label = new JLabel(":)");
        fontSize = 13;
        label.setFont(new Font("Avenir", Font.PLAIN, fontSize));
        defaultX = 13;
        label.setBounds(defaultX, 13, 15, 15);
        add(label);

        JLabel title = new JLabel(" your tasks");
        yourTasksFontSize = 80;
        title.setFont(new Font("Better Together", Font.PLAIN, yourTasksFontSize));
        int textXPos = 300;
        int textYPos = 1;
        int textWidth = 300;
        int textHeight = 100;
        title.setBounds(textXPos, textYPos, textWidth, textHeight);
        add(title);

        JLabel titleShadow = new JLabel("your tasks");
        titleShadow.setFont(new Font("Better Together", Font.PLAIN, yourTasksFontSize));
        titleShadow.setForeground(Color.lightGray);
        titleShadow.setBounds(textXPos+22, textYPos+2, textWidth,textHeight);
        add(titleShadow);
    }

    private void addNotesPanel() {
        JLabel notesTitle = new JLabel("notes");
        int notesFontSize = 45;
        notesTitle.setFont(new Font("Better Together", Font.PLAIN, notesFontSize));
        int notesYPos = 262;
        int notesWidth = 100;
        int notesHeight = 100;
        notesTitle.setBounds(defaultX, notesYPos, notesWidth, notesHeight);
        notesPanel = new NotesPanel();
        notesPanel.setBounds(defaultX, 330, windowWidth/4 + 10, 300);
        add(notesTitle);
        add(notesPanel);

        JLabel notesShadow = new JLabel("notes");
        notesShadow.setFont(new Font("Better Together", Font.PLAIN, notesFontSize ));
        notesShadow.setForeground(Color.lightGray);
        notesShadow.setBounds(defaultX-2, notesYPos+2, notesWidth, notesHeight);
        add(notesShadow);
    }

    private void addCalendarPanel() {
        calendarPanel = new CalendarPanel();
        calendarPanel.setBounds(3, 100, 250, 150);
        calendarPanel.setBackground(backgroundColour);
        add(calendarPanel);
    }

    public static void main(String[] args)
    {
        new ToDoListGUI();
    }
}

