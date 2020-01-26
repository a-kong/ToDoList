package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    private List<ToDoListManagerObserver> observerList = new ArrayList<>();


    public void addObserver(ToDoListManagerObserver observer) {
        if (!observerList.contains(observer)) {
            observerList.add(observer);
        }
    }

    public void notifyObserver(String task){
        for(ToDoListManagerObserver toDoListManagerObserver : observerList) {
            toDoListManagerObserver.update(task);
        }


    }
}
