public class Task {

    String description;
    boolean isDone;
    String dueDate = null;
    private actionType type;

    //Include the tags for TODO's Deadlines and Events
    /**
     * Create a Task object
     * @param description description of the Task
     * @param type type of the task
     */
    Task(String description, actionType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;

    }

    //A-Classes
    /**
     * @return icon format of true / false
     */
    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * @return the description of the task
     */
    String getDescription(){
        return description;
    }

    /**
     * @return the string repr of enum
     */
    String getActiontype(){
        return type.name(); // converts enum to string
    }

    /**
     * @return the dueDate of the task
     */
    String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return  "[" + getStatusIcon() + "]" + description ;
    }
}
