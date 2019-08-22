import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you?");
        Boolean state = true;
        while(state) {
            Scanner sc = new Scanner(System.in);
            String str = sc.next();
            if(str.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                state=false;
            }
            else {
                System.out.println(str);
            }
        }
    }
}
