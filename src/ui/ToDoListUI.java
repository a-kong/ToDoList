package ui;

import exceptions.ItemDoesNotExist;
import exceptions.TooManyThingsToDo;
import model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class ToDoListUI {
    private ToDoListManager toDoListManager  = new ToDoListManager();
    private ToDoItem toDoItem = new ToDoItem("", "", "");
    private Scanner scanner = new Scanner(System.in);


    private DateFormat df = new SimpleDateFormat();
    private Date today = Calendar.getInstance().getTime();
    private String todaysDate = df.format(today);


    ToDoListUI() {
        String entry = "";
        String listEntry = "";
        System.out.println("today is " + todaysDate);
      //  System.out.println(giveTheWeatherMain);



        while (true) {
            startupLines();
            entry = scanner.nextLine();

            if (entry.equals("1")) {
                askWhichListToSee();
                listEntry = scanner.nextLine();

                if (listEntry.equals("a")) {
                    showRegListAndAskToChangeStates();
                }

                if (listEntry.equals("b")) {
                    showUrgListAndAskToChangeStates();
                }

                changingState();

            }

            if (entry.equals("2")) {

                addingTaskToList();
                String listChoice = scanner.nextLine();

                if (listChoice.equals("a")) {
                    addingRegTask();
                }

                if (listChoice.equals("b")) {
                    addingUrgTask();
                }
            }


            // FINISH A TASK
            if (entry.equals("3")) {
                removeTaskFromList();
            }

            // DISPLAY SAVED LIST
            if (entry.equals("4")) {
                try {
                    toDoListManager.load();
                } catch (Exception e) {
                    continue;
                }
                System.out.println("the list has been loaded");

            }

            // QUIT
            if (entry.equals("5")) {
                try {
                    toDoListManager.save();

                } catch (Exception e) {
                    break;
                }
                System.out.println("-------BYE-------");
                break;
            }
        }
    }


    private void removeTaskFromList() {
        System.out.println("what do you want to remove from the list?");
        String task = scanner.nextLine();
        try {
            toDoListManager.removeRegularTaskFromList(task);
            //System.out.println("you have removed the task");
        } catch (ItemDoesNotExist itemDoesNotExist) {
            System.out.println("that task does not exist");
            //continue;

        }

    }

    private void addingUrgTask() {
        System.out.println("what do you want to add to the list?");
        String urgentTask = scanner.nextLine();

        System.out.println("maybe you should add a due date to it:");
        String urgentDueDate = scanner.nextLine();

        System.out.println("what's the state of it right now?");
        String state = scanner.nextLine();

        UrgentToDoItem urgentToDoItem = new UrgentToDoItem(urgentTask, urgentDueDate, 0, state);
        toDoListManager.putUrgentTaskInList(urgentToDoItem);


        System.out.println("you have added the task: " + urgentTask);
        System.out.println("                    due: " + urgentDueDate);
        System.out.println("                  state: " + state);
    }

    private void addingRegTask() {
        System.out.println("what do you want to add to the list?");
        String task = scanner.nextLine();

        System.out.println("maybe you should add a due date to it:");
        String dueDate = scanner.nextLine();

        System.out.println("what's the state of it right now?");
        String state = scanner.nextLine();

        ToDoItem toDoItem = new ToDoItem(task, dueDate, state);
        try {
            toDoListManager.putRegularTaskInList(toDoItem);
        } catch (TooManyThingsToDo tooManyThingsToDo) {
            System.out.println("!!!");
            System.out.println("you have too many things to do!!");
            System.out.println("you cannot add anymore tasks until you finish some");
            //continue;
        }


        System.out.println("________________________________________________________");
        System.out.println("you have added the task: " + task);
        System.out.println("                    due: " + dueDate);
        System.out.println("                  state: " + state);
    }

    private void addingTaskToList() {
        System.out.println("which list do you want to add to?");
        System.out.println("a: regular list // b: urgent list");
    }


    private void changingState() {
        String response = scanner.nextLine();
        if (response.equals("yes")) {
            System.out.println("enter the task you want to change");
            String task = scanner.nextLine();
            for (ToDoItem item : toDoListManager.getRegularTasks()) {
                if (item.getTask().equals(task)) {
                    System.out.println("enter the new state");
                    String state = scanner.nextLine();
                    item.setState(state);
                }

            }

        }
    }

    private void showUrgListAndAskToChangeStates() {
        System.out.println("your todo list: ");
        toDoListManager.showUrgentTasks();
        System.out.println("do you want to change the state of any of your tasks?");
    }

    private void showRegListAndAskToChangeStates() {
        System.out.println("your todo list: ");
        toDoListManager.showRegularTasks();
        System.out.println("do you want to change the state of any of your tasks?");
    }

    private void askWhichListToSee() {
        System.out.println("which list do you want to see?");
        System.out.println("a: regular list // b: urgent list");
    }

    private void startupLines() {
        System.out.println("___________________________________________________");
        System.out.println("hello, what do you want to do?");
        System.out.println("1. see list // 2. add a task // 3. complete a task // 4. load the lists // 5. quit");
    }


    public static void main(String[] args) {
        new ToDoListUI();
    }
}








//https://stackoverflow.com/questions/5683728/convert-java-util-date-to-string





// ToDoListUI: a to-do list that allows the user to type in several tasks, with each task corresponding to a number
// assigned by the system.
// - adding a task: user types in multiple strings into a line and hits enter;
//   java will take the line of strings and input them into the list;
//   user doesn't have to view list before adding anything

// - finishing/removing a task: user types the task they want to delete

// - quitting the system will stop the program