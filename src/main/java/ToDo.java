public class ToDo  extends Task{

    /**
     * ToDo Constructor
     * @param description description of the task
     * @param type type of task
     */
    public ToDo(String description, actionType type){
        super(description,type);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
