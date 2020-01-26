package model;

public abstract class Item {

    protected String task;
    protected String dueDate;
    protected String state;
    protected String notes;

    // SETTERS:
    public void setTask() {
        this.task = task;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    // GETTERS:
    public String getTask() {
        return task;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getState() {
        return state;
    }

    public String getNotes() {
        return notes;
    }



}
