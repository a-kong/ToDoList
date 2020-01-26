package test;


import exceptions.ItemDoesNotExist;
import exceptions.TooManyThingsToDo;
import model.ToDoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.ToDoListManager;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestToDoListManager {
    ToDoListManager testToDoListManager;
    ToDoItem testitem0;
    ToDoItem testitem1;
    ToDoItem testitem2;
    private ArrayList<ToDoItem> tasks;

    @BeforeEach
    public void setUp() {
        testToDoListManager = new ToDoListManager();
        testitem0 = new ToDoItem("homework", "", "000");
        testitem1 = new ToDoItem("prelab", "", "001");
        testitem2 = new ToDoItem("prelecture", "", "002");
        tasks = new ArrayList<>();

    }


    //TODO: testPutInList: add strings to the list
    //TODO: outcome: the string will now be in the list
    @Test
    public void testPutInList() throws TooManyThingsToDo {
        testToDoListManager.putRegularTaskInList(testitem0);
        assertTrue(testToDoListManager.getRegularTasks().contains(testitem0));

    }


    //TODO: testRemoveFromList: removes the typed in string from list
    //TODO: outcome: the string is no longer in the list

    @Test
    public void testRemoveFromListThrowsException() {
        try {
            testToDoListManager.removeRegularTaskFromList(testitem0.getTask());
        } catch (ItemDoesNotExist itemDoesNotExist) {
            System.out.println("ItemDoesNotExist exception has been caught");
        }
        testToDoListManager.getRegularTasks();

    }

    @Test
    public void testRemoveFromListNoExceptionThrown() {
        try {
            testToDoListManager.removeRegularTaskFromList(testitem0.getTask());
            System.out.println("an exception has not been thrown");
        } catch (ItemDoesNotExist itemDoesNotExist) {
        }
        testToDoListManager.getRegularTasks();

    }



    @Test
    //TODO: testLoad: read data from the file
    //TODO: outcome: test that the list of objects contains objects that correspond to the lines from the file
    public void testLoad() {
        List<String> read = null;
        try {
            read = Files.readAllLines(new File("testFile").toPath());
        } catch (IOException e) {
            System.out.println("exception has been caught");
        }
        for(String item : read) {
            ToDoItem todoItem = new ToDoItem("","","");
           todoItem.load(item);
            this.tasks.add(todoItem);
        }
        assertEquals(0, tasks.size());

    }


    @Test
    //TODO: testSave: writes data to a file
    //TODO: outcome: output data is the same as what is contained in the file
    public void testSave() throws IOException {
        String filename = "schoolwork";
        ToDoListManager expectedToDoListManager = new ToDoListManager();
        String expectedContents = "homeworkprelabprelecturenullnullnull";

        try {
            testToDoListManager.putRegularTaskInList(testitem0);
        } catch (TooManyThingsToDo tooManyThingsToDo) {
            System.out.println("too many things to do!!");
        }
        try {
            testToDoListManager.putRegularTaskInList(testitem1);
        } catch (TooManyThingsToDo tooManyThingsToDo) {
            System.out.println("try removing things");
        }
        try {
            testToDoListManager.putRegularTaskInList(testitem2);
        } catch (TooManyThingsToDo tooManyThingsToDo) {
            System.out.println("you gotta finish some tasks");
        }

        testToDoListManager.save();
        expectedToDoListManager.load();

        assertEquals(expectedContents, getTaskDateFromAllToDoItem(expectedToDoListManager));

    }

    //
    public String getTaskDateFromAllToDoItem(ToDoListManager toDoListManager) {

        String tasks = "";
        String dates = "";

        for (int i = 0; i < toDoListManager.getRegularTasks().size(); i++) {

            tasks = tasks + toDoListManager.getRegularTasks().get(i).getTask();
            dates = dates + toDoListManager.getRegularTasks().get(i).getDueDate();
        }

        return tasks + dates;
    }

    }





