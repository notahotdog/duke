public class Event  extends Task{
    String command;
    String action;
    String dueDate;
/*
    public Event(String description, String type){
        super(description,type);

    }
*/
    public Event(String description, String type, String dueDate){
        super(description,type);
        this.dueDate = dueDate;

    }


    @Override
    public String toString(){
        return "[E]"+ super.toString() + " (at: " + dueDate + ")";
    }
}
