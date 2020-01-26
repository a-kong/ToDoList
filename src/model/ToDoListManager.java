package model;

import exceptions.ItemDoesNotExist;
import exceptions.TooManyThingsToDo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ToDoListManager extends Subject{

    private ArrayList<ToDoItem> regularTasks;
    private ArrayList<UrgentToDoItem> urgentTasks;
    private ToDoListManagerObserver toDoListManagerObserver;
    private SaveAndLoadData saveAndLoadData;

    public ToDoListManager() {
        regularTasks = new ArrayList<>();
        urgentTasks = new ArrayList<>();
        toDoListManagerObserver = new ToDoListManagerObserver();
        addObserver(toDoListManagerObserver);
        saveAndLoadData = new SaveAndLoadData(this);
    }

    ToDoItem toDoItem = new ToDoItem("","", "");



    // REQUIRES: ToDoItem
    // MODIFIES: this
    // EFFECTS: adds ToDoItem to list
    public void putRegularTaskInList(ToDoItem item) throws TooManyThingsToDo {
        if (regularTasks.size() > 8) {
            throw new TooManyThingsToDo();
        }
        regularTasks.add(item);
    }

    // REQUIRES: UrgentToDoItem
    // MODIFIES: this
    // EFFECTS: adds UrgentToDoItem to list
    public void putUrgentTaskInList(UrgentToDoItem urgentItem) {
        urgentTasks.add(urgentItem);
    }

    // REQUIRES: task
    // EFFECTS: returns true if task is in the to-do list, false otherwise
    public boolean isInList(ToDoItem task) {
        return regularTasks.contains(task);
    }

    // REQUIRES: regularTask
    // EFFECTS: returns the regular task list
    public ArrayList<ToDoItem> getRegularTasks() {
        return regularTasks;
    }


    // REQUIRES: urgentTask
    // EFFECTS: returns the urgent to-do list
    public ArrayList<UrgentToDoItem> getUrgentTasks() {
        return urgentTasks;
    }

    // REQUIRES: string
    // MODIFIES: this
    // EFFECTS: removes the string from the list
    public void removeRegularTaskFromList(String task) throws ItemDoesNotExist {
//        if(!toDoItem.getTask().equals(task)) {
//            throw new ItemDoesNotExist();
//        }
        ToDoItem toRemove = null;
        for(ToDoItem toDoItem : regularTasks){               // for each item in regularTasks, iterate over every item and do the if method on it
            if(toDoItem.getTask().equals(task)) {
                toRemove = toDoItem;
            }
        }
        regularTasks.remove(toRemove);

        notifyObserver(task);
    }


    // EFFECTS: prints out regular task list
    public void showRegularTasks() {
        for(ToDoItem task : regularTasks) {
            System.out.println(">> "+task.getTask());
            System.out.println("due: " +task.getDueDate());
            System.out.println("state: " +task.getState());
        }
    }

    // EFFECTS: prints urgent to-do list
    public void showUrgentTasks() {
        for(UrgentToDoItem task : urgentTasks) {
            System.out.println(">> "+task.getTask());
            System.out.println("due: " +task.getDueDate());
            System.out.println("state: " +task.getState());
        }
    }

    public void save() {
        try {
            saveAndLoadData.save("regular",regularTasks);
            saveAndLoadData.save("urgent",urgentTasks);
        } catch (IOException e) {
            System.out.println("oh no");
        }
    }

    public void load() {
        try {
            saveAndLoadData.load("regular");
            saveAndLoadData.load("urgent");
        } catch (IOException e) {
            System.out.println("oh no");
        }
    }


//    @Override
//    public void load(String file) throws IOException {
//        List<String> read = Files.readAllLines(new File(file+".txt").toPath());
//        for(String item : read) {
//            ToDoItem todoItem = new ToDoItem("","", "");
//            todoItem.load(item);
//            this.regularTasks.add(todoItem);
//        }
//    }
//
//    @Override
//    public void save(String file) throws IOException {
//        List<String> tasks = new ArrayList<>();
//        for(ToDoItem item : this.regularTasks) {
//            tasks.add(item.save());
//        }
//        File f = new File(file+".txt");
//        Files.write(f.toPath(), tasks);
//        while(!f.exists()){}
//    }
//
//
//
//    public void loadUrgent(String file) throws IOException {
//        List<String> read = Files.readAllLines(new File(file+".txt").toPath());
//        for(String item : read) {
//            UrgentToDoItem todoItem = new UrgentToDoItem("","", 0, "");
//            todoItem.load(item);
//            this.urgentTasks.add(todoItem);
//        }
//    }
//
//
//    public void saveUrgent(String file) throws IOException {
//        List<String> tasks = new ArrayList<>();
//        for(ToDoItem item : this.regularTasks) {
//            tasks.add(item.save());
//        }
//        File f = new File(file+".txt");
//        Files.write(f.toPath(), tasks);
//        while(!f.exists()){}
//    }
}
