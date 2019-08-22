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

        List<Task> actionList = new ArrayList<>();
        while(state) {

            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();

            boolean removeList = str.contains("done");

            if(str.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                state=false;
            }

            else if(removeList){
                System.out.println("Nice! I've marked this task as done:");
                String arrStr[]  = str.split(" ", 2);

                //this will change the icon to false
                int num = Integer.parseInt(arrStr[1]) -1;
                actionList.get(num).isDone = true;

                String action = actionList.get(num).getDescription();
                String check = actionList.get(num).getStatusIcon();
                System.out.println("  [" + check +"]" + action);
            }


            else if(str.equals("list")){
                System.out.println("Here are the tasks in your list:");

                //Implementation using the task class
                Iterator<Task> actionItr = actionList.iterator();
                int ctr = 1;
                while(actionItr.hasNext()){
                    Task element = actionItr.next();
                    String action = element.getDescription();
                    String check = element.getStatusIcon();

                    System.out.println(ctr +". [" + check +"]" + action);
                    ctr++;
                }


            }
            else {

                Task action = new Task(str);
                actionList.add(action);
                System.out.println("added: "+ str);
            }
        }
    }
}

