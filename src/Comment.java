public class Comment implements IComment{
    private String comment;

    public Comment(){
        comment = "";
    }

    public Comment(String cm){
        comment = cm;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String cm){
        comment = cm;
    }
}
