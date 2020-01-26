package model;


import sun.java2d.pipe.SpanShapeRenderer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ToDoItem extends Item{


    DateFormat df = new SimpleDateFormat();
    Date today = Calendar.getInstance().getTime();
    String reportTheDate = df.format(today);
    //^^
    //https://stackoverflow.com/questions/5683728/convert-java-util-date-to-string


    public ToDoItem(String task, String dueDate, String state) {
        this.task = task;
        this.dueDate = dueDate;  // current date
        this.state = state;
        notes = "";
    }

    public void load(String from) {
        String[] values = from.split(",", 5);
        this.task = values[1];
        this.dueDate = values[2];
        this.state = values[3];
        this.notes = values[4];
    }

    public String save() {
        return "regular"+","+task+","+dueDate+","+state+","+notes;
    }



//    @Override
//    public String getCategory() {
//        return category;
//    }
//
//    @Override
//    public void setCategory(Category category) {
//        String catName = category.getName();
//        if(this.category != null && !this.category.equals(catName)) {
//            this.category = catName;  //getName gets string from category
//        }
//        category.addItems(this);
//    }



    //REQUIRES: a Date
    //EFFECTS: returns if the task is past due date or not
    public String isItPastDueDate() {
        Date today = new Date();
        Date dueDate = new Date();
        String messageToReturn = null;

        if (today.before(dueDate)) {
            messageToReturn = "keep going at it ";
        }

        else if (today.after(dueDate)) {
            messageToReturn = "it's overdue :( ";

        }
        return messageToReturn;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoItem toDoItem = (ToDoItem) o;
        return Objects.equals(dueDate, toDoItem.dueDate) &&
                Objects.equals(task, toDoItem.task);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dueDate, task);
    }


    // from StackExchange: https://stackoverflow.com/questions/15925509/java-compare-two-dates

}
