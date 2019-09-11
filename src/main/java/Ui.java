import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Ui {


    /**
     * Default Constructor for UI, prints out initial message
     */
    Ui(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you? ");

    }


    /**
     *  Show Loading Error
     */
    void showLoadingError(){
        System.out.println("     ☹   OOPS!!! There was an error loading your list. ");

    }


    /**
     * Creates a new ToDo task
     * @param tasks task
     * @param restWord Details of the todo task
     */
    void newTodo(TaskList tasks, String restWord){

        System.out.println("Got it. I've added this task: ");

        ToDo action = new ToDo((restWord), actionType.T);
        tasks.taskList.add(action);

        System.out.println("\t" + action);
        System.out.println("Now you have " + tasks.taskList.size() + " tasks in the list.");


    }


    /**
     * Creates a new Deadline task
     * @param tasks task
     * @param inpSentence the details of the Deadline
     * @param storage the storage location of the Deadline
     * @throws ParseException if the strings not in a particular format
     */
    void newDeadline(TaskList tasks, String inpSentence, Storage storage) throws ParseException {

        System.out.println("Got it. I've added this task: ");
        //separate the word
        String[] command = inpSentence.split("/by", 2);
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

    /**
     * Creates a new Event task
     * @param tasks task
     * @param inpSentence the details of the Event
     * @param storage the storage location of the Event
     * @throws ParseException if the strings not in a particular format
     */
    void  newEvent(TaskList tasks, String inpSentence, Storage storage) throws ParseException {

        System.out.println("Got it. I've added this task: ");

        //Separate the word
        String[] command = inpSentence.split("/at", 2);
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
        storage.newEvent(addtoList);//forget to include inside

    }


    /**
     * Prints out the task stored in the list
     * @param tasks array of tasks
     */
    void showList(TaskList tasks){

        System.out.println("Here are the tasks in your list:");

        //Implementation using the task class
        Iterator<Task> actionItr = tasks.taskList.iterator();
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

    /**
     * Deletes a task
     * @param tasks task to be deleted
     * @param lineNo line no of the task
     */
    void deleteLine(TaskList tasks, String lineNo){
        //Level 6- Delete
        System.out.println("Noted. I've removed this task:  ");

        int num = Integer.parseInt(lineNo) - 1;
        tasks.taskList.remove(num);
        System.out.println("Now you have " + tasks.taskList.size() + " tasks in the list.");

    }

    /**
     * Marks a task as done
     * @param tasks task to be marked as Done
     * @param pos pos of the tas
     */
    void markAsDone(TaskList tasks, String pos){
        System.out.println("Nice! I've marked this task as done:");

        //this will change the icon to true
        int num = Integer.parseInt(pos)-1;
        tasks.taskList.get(num).isDone = true;

        String action = tasks.taskList.get(num). getDescription();
        String check = tasks.taskList.get(num). getStatusIcon();
        System.out.println("  [" + check + "]" + action);

    }

    /**
     * Finds whether a Task is present in the taskList
     * @param tasks taskList to be searched from
     * @param findWord  task to be found
     */
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

    /**
     * Helper function for printing a Task List
     * @param arrayList taskList to be printed
     */
    private static void printTaskList(List<Task> arrayList){
        for(Task element: arrayList){
            System.out.println(element);
        }

    }
}
