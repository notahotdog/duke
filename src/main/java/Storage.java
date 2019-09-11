import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    protected String filepath;

    public Storage(String Filepath){
        filepath = Filepath;
    }


    //Loads data from the previous session

    /**
     * Loads a previous team
     * @return
     * @throws IOException
     */
    public List<Task> load() throws IOException {

        List<Task> actionList = new ArrayList<>(); //List of actions to be taken

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
        fileReader.close();//not sure

        return actionList;

    }



    //needs to include filepath
    public void updateStateDone(int lineNo) throws IOException {

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


    public void deleteLine(int lineNo) throws IOException {

        FileReader fileReaderDelete = new FileReader("src/data/duke.txt");
        BufferedReader bufferedReaderDelete = new BufferedReader(fileReaderDelete);
        StringBuilder inputBuffer = new StringBuilder();
        String tmpStr;

        int ctr = 0;
        while ((tmpStr = bufferedReaderDelete.readLine()) != null) {

            ctr++;
            if(lineNo == ctr) continue; // ignores the line

            inputBuffer.append(tmpStr);
            inputBuffer.append('\n');

        }

        fileReaderDelete.close();
        FileOutputStream fileOut = new FileOutputStream("src/data/duke.txt");
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    }



    public static void newToDo(String restWord) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("src/data/duke.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("T|" + false + "|" + restWord); // add this to the shit
            //printWriter.println("T|" + action.isDone + "|" + action.description);
            printWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("exception caught");
            e.printStackTrace();
        }

    }

    public static void newDeadline(Task addtoList){
        try {
            FileWriter fileWriter = new FileWriter("src/data/duke.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("D|" + addtoList.isDone + "|" + addtoList.description + "|" + addtoList.dueDate);
            printWriter.close();
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void newEvent(Task addtoList){
        try {
            FileWriter fileWriter = new FileWriter("src/data/duke.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("E|" + addtoList.isDone + "|" + addtoList.description + "|" + addtoList.dueDate);
            printWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }







}
