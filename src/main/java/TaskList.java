import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList {

    List<Task> taskList;

    public TaskList(List<Task> tList) {
        if(tList.size() >= 100){
            System.out.println("      ☹   OOPS!!! This list is full.");
        }
        taskList = tList;
    }

    public TaskList (){
        taskList = new ArrayList<>();
    }

    /*
    public TaskList(void load) {
        System.out.println("buggy lol");;
    }
    */



    public Iterator getIterator(){
        return taskList.iterator();
    }

    public static void newTask(Task task) throws IOException{
            ;

    }

}



