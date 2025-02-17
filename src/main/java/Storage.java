import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Storage {

    private String filepath;

    Storage(String Filepath){
        filepath = Filepath;
    }

    /**
     * Loads data from a previous session
     * @return List of Task data
     * @throws IOException if theres no input
     */
    List<Task> load() throws IOException {

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
            switch (tokens[0]) {
                case "T": {

                    //Create Tasks
                    ToDo action = new ToDo(tokens[2], actionType.T);
                    if (tokens[1].equals("true")) { //replaced from true
                        action.isDone = true;
                    }

                    actionList.add(action);
                    break;
                }

                //Deadlines
                case "D": {

                    Deadline action = new Deadline(tokens[2], actionType.D, tokens[3]);
                    if (tokens[1].equals("true")) {
                        action.isDone = true;
                    }
                    actionList.add(action);
                    break;
                }

                //Events
                case "E": {

                    Event action = new Event(tokens[2], actionType.E, tokens[3]);
                    if (tokens[1].equals("true")) {
                        action.isDone = true;
                    }
                    actionList.add(action);
                    break;
                }
            }
        }
        br.close();
        fileReader.close();//not sure

        return actionList;

    }

    /**
     * Marks as Done inside the stored file
     * @param lineNo the line number of the stored data to be changed
     * @throws IOException if there's no input
     */
    void updateStateDone(int lineNo) throws IOException {

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

    /**
     * deletes the action
     * @param lineNo the line number of the stored data to be deleted
     * @throws IOException if theres no input
     */
    void deleteLine(int lineNo) throws IOException {

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

    /**
     *  Stores the data of the new ToDo task
     * @param restWord description of the ToDo task
     */
    static void newToDo(String restWord) {
        try {
            FileWriter fileWriter = new FileWriter("src/data/duke.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("T|" + false + "|" + restWord); // add this to the shit
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("exception caught");
            e.printStackTrace();
        }
    }

    /**
     * Stores the data of the new Deadline task
     * @param addtoList Deadline task
     */
    static void newDeadline(Task addtoList){
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

    /**
     * Stores the data of the new Event task
     * @param addtoList event task
     */
    static void newEvent(Task addtoList){
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
