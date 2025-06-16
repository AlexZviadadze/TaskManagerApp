public class RepeatableTask extends Task{
    private  String repeatPattern;

    public RepeatableTask(int id, String title, String description,String ownerUsername,String pattern){
        super(id,title,description,ownerUsername);
        this.repeatPattern = pattern;
    }

    public  String getRepeatPattern(){
        return repeatPattern;
    }

    @Override
    public String toString(){
        return  super.toString() + "| Repeat: " + repeatPattern;
    }
}
