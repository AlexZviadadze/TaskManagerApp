public abstract class Task {
    protected  int id;
    protected  String title;
    protected  String description;
    protected  String ownerUsername;

    public Task(int id, String title,String description,String ownerUsername){
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerUsername = ownerUsername;
    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public  String getDescription(){
        return description;
    }
    public  String getOwnerUsername(){
        return ownerUsername;
    }
    public  void setDescription(String desc){
        this.description = desc;
    }

    @Override
    public String toString(){
        return  title + "----"+ description + "(Created by: " + ownerUsername +")";
    }
}
