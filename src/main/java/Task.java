public class Task {

    protected String description;
    protected boolean isDone;
    public String actiontype;

    //Include the tags for TODO's Deadlines and Events
    //extend Class


    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.actiontype = type;
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription(){
        return description;
    }

    public String getActiontype(){
        return actiontype;
    }

    @Override
    public String toString() {
        return  "[" + getStatusIcon() + "]" + description ;
    }
}
