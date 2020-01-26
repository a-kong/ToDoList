package model;


public class ToDoListManagerObserver extends Observer {



    @Override
    public void update(String task) {

        System.out.println("you've removed " +task);


    }
}
