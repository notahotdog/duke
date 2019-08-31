public class Task {

    protected String description;
    protected boolean isDone;
    public String dueDate = null;
    public actionType type;

    //Include the tags for TODO's Deadlines and Events

    public Task(String description, actionType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;

    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription(){
        return description;
    }

    public String getActiontype(){
        return type.name(); // converts enum to string
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return  "[" + getStatusIcon() + "]" + description ;
    }
}
