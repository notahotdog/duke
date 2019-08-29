import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you? ");
        Boolean state = true;

        List<Task> actionList = new ArrayList<>(); //List of actions to be taken

        while(state) {

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
                        System.out.println("      ☹   OOPS!!! The description of a todo cannot be empty.");
                        continue;
                    }
                    System.out.println("Got it. I've added this task: ");
                    ToDo action = new ToDo((restWord), "T");
                    actionList.add(action);

                    //System.out.println("[T][x]"  + restWord);
                    System.out.println("\t" + action);
                    System.out.println("Now you have " + actionList.size() + " tasks in the list.");

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

                }

                    else {
                        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

            }
            catch (Exception e){
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }



        }
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

