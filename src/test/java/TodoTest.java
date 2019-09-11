import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void dummyTest(){
        assertEquals(2,2);
    }


    @Test
    public void Test(){
        new ToDo("a a a /by 12/13/12 9934)", actionType.T).toString();
        new ToDo("a a a /at 12/13/12 9934)", actionType.T).toString();
        new ToDo("", actionType.T).toString();
    }



}
