package model;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UrgentToDoItem extends Item {

    private int countdown;

    DateFormat theDate = new SimpleDateFormat();
    Date today = Calendar.getInstance().getTime();
    //https://stackoverflow.com/questions/5683728/convert-java-util-date-to-string


    public UrgentToDoItem(String task, String dueDate, int countdown, String state) {
        this.task = task;
        this.dueDate = dueDate;
        this.countdown = countdown;
        this.state = state;

    }

    public void load(String from) {
        String[] values = from.split(",", 4);
        this.task = values[1];
        this.dueDate = values[2];
        this.countdown = Integer.parseInt(values[3]);
        this.state = values[4];
    }

    public String save() {
        return "urgent"+","+task+","+countdown+","+state;
    }




}
