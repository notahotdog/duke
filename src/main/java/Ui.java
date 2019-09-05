import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ui {


    public Ui(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you? ");

    }


    void showLoadingError(){
        System.out.println("     ☹   OOPS!!! There was an error loading your list. ");

    }


    void newTodo(TaskList tasks, String restWord){


        System.out.println("Got it. I've added this task: ");

        ToDo action = new ToDo((restWord), actionType.T);
        tasks.taskList.add(action);

        System.out.println("\t" + action);
        System.out.println("Now you have " + tasks.taskList.size() + " tasks in the list.");


    }


    void newDeadline(TaskList tasks, String inpSentence, Storage storage) throws ParseException {

        System.out.println("Got it. I've added this task: ");
        //separate the word
        String command[] = inpSentence.split("/by", 2);
        String action = command[0]; //action to be undertaken
        String dueDate = command[1]; //date to be completed


        //conversion from string to date format
        String temp = Duke.convertToDateFormat(dueDate);
        dueDate = temp;

        Deadline addtoList = new Deadline(action, actionType.D, dueDate);
        tasks.taskList.add(addtoList);

        System.out.println("\t" + addtoList);
        System.out.println("Now you have " + tasks.taskList.size() + " tasks in the list.");

        storage.newDeadline(addtoList);// saves in file
    }

    void  newEvent(TaskList tasks, String inpSentence, Storage storage) throws ParseException {



        System.out.println("Got it. I've added this task: ");

        //Separate the word
        String command[] = inpSentence.split("/at", 2);
        String action = command[0]; //action to be undertaken
        String dueDate = command[1]; // date to be completed

        //conversion from string to date format
        String temp = Duke.convertToDateFormat(dueDate);
        dueDate = temp;

        //enum
        Event addtoList = new Event(action, actionType.E, dueDate);


        tasks.taskList.add(addtoList);

        System.out.println("\t" + addtoList);
        System.out.println("Now you have " + tasks.taskList.size() + " tasks in the list.");

        //handle storage


    }




    void showList(TaskList tasks){

        System.out.println("Here are the tasks in your list:");

        //Implementation using the task class
        Iterator<Task> actionItr = tasks.taskList.iterator();
        //Iterator<Task> actionItr = tasks.getIterator();
        int ctr = 1;
        while (actionItr.hasNext()) {
            Task element = actionItr.next();
            String action = element.getDescription();
            String check = element.getStatusIcon();
            String actionType = element.getActiontype();

            String dueDate = element.getDueDate();

            if (element instanceof ToDo) {
                System.out.println(ctr + ". [" + actionType + "]" + "[" + check + "]" + action);
            } else if (element instanceof Deadline) {
                System.out.println(ctr + ". [" + actionType + "]" + "[" + check + "]" + action + "(by:" + dueDate + ")");
            } else if (element instanceof Event) {
                System.out.println(ctr + ". [" + actionType + "]" + "[" + check + "]" + action + "(at:" + dueDate + ")");
            }

            ctr++;
    }
}

    void deleteLine(TaskList tasks, String lineNo){
        //Level 6- Delete
        System.out.println("Noted. I've removed this task:  ");

        int num = Integer.parseInt(lineNo) - 1;
        tasks.taskList.remove(num);
        System.out.println("Now you have " + tasks.taskList.size() + " tasks in the list.");

    }

    void markAsDone(TaskList tasks, String pos){
        System.out.println("Nice! I've marked this task as done:");
       // String arrStr[] = str.split(" ", 2);

        //this will change the icon to true
        int num = Integer.parseInt(pos)-1;//arrStr[1]) - 1;
        //actionList.get(num).isDone = true;
        tasks.taskList.get(num).isDone = true;

        //Updates the state of the line
        //int lineNo = Integer.parseInt(arrStr[1]);
        //int lineNo = Integer.parseInt(str);
        //Duke.updateStateDone(lineNo);
        //update to storage

        //String action = actionList.get(num).getDescription();
        //String check = actionList.get(num).getStatusIcon();
        String action = tasks.taskList.get(num). getDescription();
        String check = tasks.taskList.get(num). getStatusIcon();
        System.out.println("  [" + check + "]" + action);


    }



    void findWord(TaskList tasks, String findWord){
        Iterator<Task> itr = tasks.taskList.iterator();
        List<Task> printList = new ArrayList<>();
        while (itr.hasNext()) {
            Task element = itr.next();
            String elementDescription = element.description;
            if (elementDescription.contains(findWord)) {
                printList.add(element);
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        printTaskList(printList);

    }

    //helper print function
    public static void printTaskList(List<Task> arrayList){
        for(Task element: arrayList){
            System.out.println(element);
        }

    }
}
