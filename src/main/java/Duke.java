import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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
        List<String> ls1 = new ArrayList<String>();
        while(state) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if(str.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                state=false;
            }
            else if(str.equals("list")){
                ListIterator<String> itr= ls1.listIterator();
                while(itr.hasNext()) {
                    System.out.println((itr.nextIndex()+1)+". " + itr.next());
                }

            }
            else {
                ls1.add(str);
                System.out.println("added: "+ str);
            }
        }
    }
}
