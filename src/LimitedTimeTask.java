import java.time.LocalDateTime;

public class LimitedTimeTask extends Task{
    private LocalDateTime deadline;

    public LimitedTimeTask(int id, String title, String description,String ownerUsername, LocalDateTime deadline){
        super(id,title,description,ownerUsername);
        this.deadline = deadline;
    }
    public void setDeadline(LocalDateTime deadline){
        this.deadline = deadline;
    }
    public  LocalDateTime getDeadline(){
        return deadline;
    }
    @Override
    public  String toString(){
        return  super.toString() + "| Deadline: " + deadline;
    }
}
