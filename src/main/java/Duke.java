import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.util.StringTokenizer;


//Commit

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.load());

        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException, ParseException {

        boolean state = true;
        while (state) {

            /*
            //FileWriter/PrintWriter
            FileWriter fileWriter = new FileWriter("src/data/duke.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.close();
            fileWriter.close();
             */
            parser.inputString(); //Takes in an input
            String firstWord = parser.firstWord;
            String restWord = parser.restWord;
            String inputSentence = parser.wholeSentence;


            try {

                if (firstWord.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    state = false;

                } else if (firstWord.equals("find")) {

                    //Level 9-Find
                    ui.findWord(tasks, restWord);// find the word inside taskList and Prints

                } else if (firstWord.equals("delete")) {

                    //Level 6-Delete
                    ui.deleteLine(tasks, restWord); //delete from the arrayList

                    //Delete from Memory
                    int lineNo = Integer.parseInt(restWord);
                    storage.deleteLine(lineNo);

                } else if (firstWord.equals("done")) {

                    ui.markAsDone(tasks, restWord); //mark as done in taskList
                    //Updates the state of the line
                    int lineNo = Integer.parseInt(restWord);
                    storage.updateStateDone(lineNo);


                } else if (firstWord.equals("list")) {
                    ui.showList(tasks);
                } else if (firstWord.equals("todo")) {

                    if (restWord == null) {
                        //Handle Errors
                        System.out.println("      ☹   OOPS!!! The description of a todo cannot be empty.");
                        continue;
                    }
                    //print
                    ui.newTodo(tasks, restWord);
                    storage.newToDo(restWord);

                } else if (firstWord.equals("deadline")) {

                    ui.newDeadline(tasks, inputSentence, storage);

                } else if (firstWord.equals("event")) {

                    ui.newEvent(tasks, inputSentence, storage);

                } else {
                    //Exception Handling level 5
                    DukeException error = new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(error.getMessage());
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
    }
    public static void main(String[] args) throws IOException, ParseException {
        new Duke("data/tasks.txt").run();
    }



/*
    public static void main(String[] args) throws DukeException, IOException, ParseException {

        Boolean state = true;
        List<Task> actionList = new ArrayList<>(); //List of actions to be taken
*/

/*

        //Level 7
        //Reading in the input from a previous session

        FileReader fileReader = new FileReader("src/data/duke.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String str1;
        while ((str1 = br.readLine()) != null) {
            String delims = "[|]";
            String[] tokens = str1.split(delims);

            //tokens
            if (tokens[0].equals("T")) {

                //Create Tasks
                ToDo action = new ToDo(tokens[2], actionType.T);
                if (tokens[1].equals("true")) { //replaced from true
                    action.isDone = true;
                }

                actionList.add(action);
            }

            //Deadlines
            else if (tokens[0].equals("D")) {

                Deadline action = new Deadline(tokens[2], actionType.D, tokens[3]);
                if (tokens[1].equals("true")) {
                    action.isDone = true;
                }
                actionList.add(action);
            }

            //Events
            else if (tokens[0].equals("E")) {

                Event action = new Event(tokens[2], actionType.E, tokens[3]);
                if (tokens[1].equals("true")) {
                    action.isDone = true;
                }
                actionList.add(action);
            }
        }
        br.close();
*/
/*
        while (state) {

            /*
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();

            String firstWord = null;
            String restWord = null;

            int noWords = countWordsUsingStringTokenizer(str);
            //check the first word if it is todo/deadline/event than add
            if (noWords != 1) {
                String inpSentence[] = str.split(" ", 2);
                firstWord = inpSentence[0];
                restWord = inpSentence[1];
            } else {
                firstWord = str;
            }

             */

            /*
            //FileWriter/PrintWriter
            FileWriter fileWriter = new FileWriter("src/data/duke.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            try {

                if (firstWord.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    state = false;

                }else if(firstWord.equals("find")){

                    //Level 9-Find
                     Iterator<Task> itr = actionList.iterator();
                     List<Task> printList = new ArrayList<>();
                     while(itr.hasNext()){
                         Task element = itr.next();
                         String elementDscr = element.description;
                         if(elementDscr.contains(restWord)){
                             printList.add(element);
                         }
                     }
                    System.out.println("Here are the matching tasks in your list:");
                     printTaskList(printList);

                }

                else if (firstWord.equals("delete")) {

                    //Level 6- Delete
                    System.out.println("Noted. I've removed this task:  ");
                    String arrStr[] = str.split(" ", 2);

                    int num = Integer.parseInt(arrStr[1]) - 1;
                    actionList.remove(num);
                    System.out.println("Now you have " + actionList.size() + " tasks in the list.");


                    //Delete from Memory
                    int lineNo = Integer.parseInt(arrStr[1]);
                    deleteLine(lineNo);

                }

                //DONE
                else if (firstWord.equals("done")) {
                    System.out.println("Nice! I've marked this task as done:");
                    String arrStr[] = str.split(" ", 2);

                    //this will change the icon to true
                    int num = Integer.parseInt(arrStr[1]) - 1;
                    actionList.get(num).isDone = true;

                    //Updates the state of the line
                    int lineNo = Integer.parseInt(arrStr[1]);
                    updateStateDone(lineNo);

                    String action = actionList.get(num).getDescription();
                    String check = actionList.get(num).getStatusIcon();
                    System.out.println("  [" + check + "]" + action);

                } else if (firstWord.equals("list")) {
                    System.out.println("Here are the tasks in your list:");

                    //Implementation using the task class
                    Iterator<Task> actionItr = actionList.iterator();
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

                } else if (firstWord.equals("todo")) {

                    if (restWord == null) {
                        //Handle Errors
                        System.out.println("      ☹   OOPS!!! The description of a todo cannot be empty.");
                        continue;
                    }

                    System.out.println("Got it. I've added this task: ");

                    ToDo action = new ToDo((restWord), actionType.T);
                    actionList.add(action);

                    System.out.println("\t" + action);
                    System.out.println("Now you have " + actionList.size() + " tasks in the list.");


                    try {
                        printWriter.println("T|" + action.isDone + "|" + action.description);
                    } catch (Exception e) {
                        System.out.println("exception caught");
                        e.printStackTrace();
                    }


                } else if (firstWord.equals("deadline")) {

                    System.out.println("Got it. I've added this task: ");

                    //separate the word
                    String command[] = restWord.split("/by", 2);
                    String action = command[0]; //action to be undertaken
                    String dueDate = command[1]; //date to be completed


                    //conversion from string to date format
                    String temp = convertToDateFormat(dueDate);
                    dueDate = temp;

                    Deadline addtoList = new Deadline(action, actionType.D, dueDate);
                    actionList.add(addtoList);

                    System.out.println("\t" + addtoList);
                    System.out.println("Now you have " + actionList.size() + " tasks in the list.");

                    try {

                        printWriter.println("D|" + addtoList.isDone + "|" + addtoList.description + "|" + addtoList.dueDate);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else if (firstWord.equals("event")) {

                    System.out.println("Got it. I've added this task: ");

                    //Separate the word
                    String command[] = restWord.split("/at", 2);
                    String action = command[0]; //action to be undertaken
                    String dueDate = command[1]; // date to be completed

                    //conversion from string to date format
                    String temp = convertToDateFormat(dueDate);
                    dueDate = temp;

                    //enum
                    Event addtoList = new Event(action, actionType.E, dueDate);


                    actionList.add(addtoList);

                    System.out.println("\t" + addtoList);
                    System.out.println("Now you have " + actionList.size() + " tasks in the list.");

                    try {
                        printWriter.println("E|" + addtoList.isDone + "|" + addtoList.description + "|" + addtoList.dueDate);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    //Exception Handling level 5
                    DukeException error = new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(error.getMessage());
                }

            } catch (Exception e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            printWriter.close();
            fileWriter.close();


        }

    }
*/


    public static int countWordsUsingStringTokenizer(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return 0;
        }
        StringTokenizer tokens = new StringTokenizer(sentence);
        return tokens.countTokens();
    }

    /*
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
     */



    //Level 8 - Date Formatting
    public static String convertToDateFormat(String dueDate) throws ParseException {

        SimpleDateFormat formatter_st = new SimpleDateFormat("dd 'st' 'of' MMMMMMMMMMMM yyyy, h:mm a");
        SimpleDateFormat formatter_nd = new SimpleDateFormat("dd 'nd' 'of' MMMMMMMMMMMM yyyy, h:mm a");
        SimpleDateFormat formatter_rd = new SimpleDateFormat("dd 'rd' 'of' MMMMMMMMMMMM yyyy, h:mm a");
        SimpleDateFormat formatter_th = new SimpleDateFormat("dd 'th' 'of' MMMMMMMMMMMM yyyy, h:mm a");


        if (dueDate.contains("/") && Character.isDigit(dueDate.charAt(1))) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

            Date date = formatter.parse(dueDate);

            String tempFormat = "";
            if (dueDate.charAt(2) == '1' || (dueDate.charAt(1) == '1' && dueDate.charAt(2) == '/')) {
                if (dueDate.charAt(0) == '1' && dueDate.charAt(1) == '1') {
                    tempFormat = formatter_th.format(date);
                } else {
                    tempFormat = formatter_st.format(date);
                }
            } else if (dueDate.charAt(2) == '2' || (dueDate.charAt(1) == '2' && dueDate.charAt(2) == '/')) {
                if (dueDate.charAt(1) == '1' && dueDate.charAt(2) == '2') {
                    tempFormat = formatter_th.format(date);
                } else {
                    tempFormat = formatter_nd.format(date);
                }
            } else if (dueDate.charAt(2) == '3' || (dueDate.charAt(1) == '3' && dueDate.charAt(2) == '/')) {
                if (dueDate.charAt(1) == '1' && dueDate.charAt(2) == '3') {
                    tempFormat = formatter_th.format(date);
                } else {
                    tempFormat = formatter_rd.format(date);
                }
            } else {
                tempFormat = formatter_th.format(date);
            }

            return tempFormat;
        }
        return dueDate;
    }


    /*
    public static void updateStateDone (int lineNo) throws IOException {
        //System.out.println("UPDATING state");
        FileReader fileReaderUpdate = new FileReader("src/data/duke.txt");
        BufferedReader bufferedReaderUpdate = new BufferedReader(fileReaderUpdate);
        StringBuilder inputBuffer = new StringBuilder();
        String tmpStr;

        int ctr = 0;
        while ((tmpStr = bufferedReaderUpdate.readLine()) != null) {


            StringBuilder line1 = new StringBuilder();

            ctr++;
            if (lineNo == ctr) { //change the values in a line
                String delims = "[|]";
                String[] tokens1 = tmpStr.split(delims);
                tokens1[1] = "true";
                for (int i = 0; i < tokens1.length; i++) {
                    line1.append(tokens1[i]);
                    if (i != tokens1.length - 1) {
                        line1.append("|");
                    }
                }
            }
            else{
                line1.append(tmpStr);
            }

            inputBuffer.append(line1);
            inputBuffer.append('\n');

        }

        fileReaderUpdate.close();
        FileOutputStream fileOut = new FileOutputStream("src/data/duke.txt");
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    }


     */

    /*
    public static void deleteLine(int lineNo) throws IOException {

        //System.out.println("UPDATING Delete");
        FileReader fileReaderDelete = new FileReader("src/data/duke.txt");
        BufferedReader bufferedReaderDelete = new BufferedReader(fileReaderDelete);
        StringBuilder inputBuffer = new StringBuilder();
        String tmpStr;

        int ctr = 0;
        while ((tmpStr = bufferedReaderDelete.readLine()) != null) {

            ctr++;
            if(lineNo == ctr) continue; // ignores the line

          //  System.out.println("Line No: " + lineNo + " Ctr val:" + ctr);
           // System.out.println("DELETE DEBUG: " + tmpStr);
            inputBuffer.append(tmpStr);
            inputBuffer.append('\n');

        }

        fileReaderDelete.close();
        FileOutputStream fileOut = new FileOutputStream("src/data/duke.txt");
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    }
     */
}

