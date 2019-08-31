public class ToDo  extends Task{

    public ToDo(String description, actionType type){
        super(description,type);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
