import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.ParseException;
import java.util.*;
import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) throws DukeException,IOException, ParseException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you? ");
        Boolean state = true;

        List<Task> actionList = new ArrayList<>(); //List of actions to be taken

        //Level 7
        //Reading in the input from a previous session

        FileReader fileReader = new FileReader("src/data/duke.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String str1;
        while((str1 = br.readLine()) != null){
            String delims = "[|]";
            String[] tokens = str1.split(delims);

            //tokens
            if(tokens[0].equals("T")){
                //-- tasks[counter] = new Todo(tokens[2]);

                //Create Tasks
                ToDo action = new ToDo(tokens[2],"T");
                if(tokens[1].equals("true")){ //replaced from true
                    action.isDone = true;
                }
                //add to actionList
                actionList.add(action);

                //--counter++;
            }
            //Deadlines
            else if(tokens[0].equals("D")){

                //--tasks[counter] = new Deadline(tokens[2], tokens[3]);

                Deadline action = new Deadline(tokens[2],"D",tokens[3]);

                if(tokens[1].equals("true"))
                {
                    action.isDone = true;
                    //tasks[counter].isDone = true;
                }

                actionList.add(action);
                //--counter++;
            }
            //Events
            else if(tokens[0].equals("E")){

                Event action = new Event(tokens[2],"E", tokens[3]);
                //--tasks[counter] = new Event(tokens[2], tokens[3]);
                if(tokens[1].equals("true")){
                    action.isDone = true;
                    //--tasks[counter].isDone = true;
                }
                //--counter++;
                actionList.add(action);

            }
        }
        br.close();



        while(state) {

            //FileWriter/ PrintWriter
            FileWriter fileWriter = new FileWriter("src/data/duke.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);



            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();

            String firstWord = null;
            String restWord = null;

            int noWords = countWordsUsingStringTokenizer(str);
            //check the first word if it is todo/deadline/event than add
            if(noWords != 1) {
                String inpSentence[] = str.split(" ", 2);
                firstWord = inpSentence[0];
                restWord = inpSentence[1];
            }
            else{
                firstWord = str;
            }



            //System.out.println("FWord: " + firstWord);
            //System.out.println("RWord: " + restWord);

            try {
                if (firstWord.equals("bye")) { //replace
                    System.out.println("Bye. Hope to see you again soon!");
                    state = false;

                }

                //mark as done
                else if (firstWord.equals("done")) {
                    System.out.println("Nice! I've marked this task as done:");
                    String arrStr[] = str.split(" ", 2);

                    //this will change the icon to false
                    int num = Integer.parseInt(arrStr[1]) - 1;
                    actionList.get(num).isDone = true;

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

                        System.out.println(ctr + ". [" + actionType + "]" + "[" + check + "]" + action);
                        ctr++;

                    }

                } else if (firstWord.equals("todo")) {

                    if(restWord == null){
                        //Handle Errors
                        System.out.println("      ☹   OOPS!!! The description of a todo cannot be empty.");
                        continue;
                    }

                    System.out.println("Got it. I've added this task: ");
                    ToDo action = new ToDo((restWord), "T");
                    actionList.add(action);

                    //System.out.println("[T][x]"  + restWord);
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


                    Deadline addtoList = new Deadline(action, "D", dueDate);
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

                    Event addtoList = new Event(action, "E", dueDate);
                    actionList.add(addtoList);

                    System.out.println("\t" + addtoList);
                    System.out.println("Now you have " + actionList.size() + " tasks in the list.");

                    try {
                        printWriter.println("E|" + addtoList.isDone + "|"+ addtoList.description+"|"+ addtoList.dueDate);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

                    else {
                        //Exception Handling level 5
                        DukeException error  = new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println(error.getMessage());
                    }

            }
            catch (Exception e){
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
                printWriter.close();
                fileWriter.close();

        }


        // Update the .txt file at the end of the session

        FileReader fileReader1 = new FileReader("src/data/duke.txt");
        BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
        StringBuilder inputBuffer = new StringBuilder();
        String str2;

        int counter = actionList.size();
        Iterator<Task> actionItr = actionList.iterator();

        //iterate through the actionList file
        while((str2 = bufferedReader1.readLine()) != null && counter != 0) {

            //Checks the action
            Task element = actionItr.next();
            boolean check = element.isDone;

            String delims = "[|]";
            String[] tokens1 = str2.split(delims);

            if (check) {
                tokens1[1] = "true";
            }
            StringBuilder line1 = new StringBuilder();
            for (int i = 0; i < tokens1.length; i++) {
                line1.append(tokens1[i]);
                if (i != tokens1.length - 1) {
                    line1.append("|");
                }
            }
            inputBuffer.append(line1);
            inputBuffer.append('\n');

        }

        fileReader1.close();

        FileOutputStream fileOut = new FileOutputStream("src/data/duke.txt");
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    }


    public static int countWordsUsingStringTokenizer(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return 0;
        }
        StringTokenizer tokens = new StringTokenizer(sentence);
        return tokens.countTokens();
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }


}

