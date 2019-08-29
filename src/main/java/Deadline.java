public class Deadline extends Task {
    String command;
    String action;
    String dueDate;


    public Deadline(String description, String type, String dueDate){
        super(description, type);
        this.dueDate = dueDate;

    }

    @Override
    public String toString(){
        return "[D]"+ super.toString() + " (by: " + dueDate + ")";
    }


}
