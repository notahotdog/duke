public class Deadline extends Task {

    /**
     * Creates a new Deadline
     * @param description Description of the task
     * @param type type of task
     * @param dueDate when the task is due
     */
    Deadline(String description, actionType type, String dueDate){
        super(description, type);
        this.dueDate = dueDate;

    }

    @Override
    public String toString(){
        return "[D]"+ super.toString() + " (by: " + dueDate + ")";
    }


}
