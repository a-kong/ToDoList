package test;

import model.ToDoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestToDoItem {
    ToDoItem testToDoItem;




    @BeforeEach
    public void setUp() {
        testToDoItem = new ToDoItem("task", "","date");

    }



    @Test
    public void testLoad(){

    }

    @Test
    public void testSave() {

    }























//    @Test
//    //TODO: check if the return message is what is expected
//    //TODO: outcome: return message should be either: "keep going at it" or "it's overdue :("
//    public void testIsPastDueDate() {
//        //check if item is past due date or not
//        testToDoItem. isItPastDueDate();
//
//
//    }
}
