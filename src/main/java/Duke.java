import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.util.StringTokenizer;


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

    public static int countWordsUsingStringTokenizer(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return 0;
        }
        StringTokenizer tokens = new StringTokenizer(sentence);
        return tokens.countTokens();
    }


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
}
