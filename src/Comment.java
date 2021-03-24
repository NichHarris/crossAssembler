public class Comment implements IComment{

    private String comment;

    public Comment(){
        comment="";
    }
    public Comment(String comment){
        this.comment=comment;
    }
    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment=comment;
    }
}
