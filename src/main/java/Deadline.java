public class Deadline extends Task {

    public Deadline(String description, actionType type, String dueDate){
        super(description, type);
        this.dueDate = dueDate;

    }

    @Override
    public String toString(){
        return "[D]"+ super.toString() + " (by: " + dueDate + ")";
    }


}
