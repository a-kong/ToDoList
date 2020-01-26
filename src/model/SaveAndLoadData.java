package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SaveAndLoadData {
    ToDoListManager toDoListManager;

    public SaveAndLoadData(ToDoListManager toDoListManager) {
        this.toDoListManager = toDoListManager;
    }

    public void load(String file) throws IOException {
        List<String> read = Files.readAllLines(new File(file+".txt").toPath());
        for(String item : read) {
            String[] values = item.split(",");
            if (values[0].equals("regular")) {
                ToDoItem todoItem = new ToDoItem("","", "");
                todoItem.load(item);
                toDoListManager.getRegularTasks().add(todoItem);
            } else if (values[0].equals("urgent")) {
                UrgentToDoItem urgentToDoItem = new UrgentToDoItem("","",0, "");
                urgentToDoItem.load(item);
                toDoListManager.getUrgentTasks().add(urgentToDoItem);
            }


        }
    }


    public void save(String file, List aList) throws IOException {
        List<String> tasks = new ArrayList<>();
        for(Object item : aList) {
            if (item instanceof ToDoItem) {
                ToDoItem tdi = (ToDoItem) item;
                tasks.add(tdi.save());
            } else if (item instanceof UrgentToDoItem) {
                UrgentToDoItem utdi = (UrgentToDoItem) item;
                tasks.add(utdi.save());
            }
        }
        File f = new File(file+".txt");
        Files.write(f.toPath(), tasks);
    }

}
