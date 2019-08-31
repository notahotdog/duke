public class Event  extends Task{

    public Event(String description, actionType type, String dueDate){
        super(description,type);
        this.dueDate = dueDate;

    }

    @Override
    public String toString(){
        return "[E]"+ super.toString() + " (at: " + dueDate + ")";
    }
}
