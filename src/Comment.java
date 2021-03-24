public class Comment implements IComment{
    private String comment;

    //Default constructor
    public Comment(){
        comment = "";
    }

    //Parametrized constructor
    public Comment(String cm){
        comment = cm;
    }

    //Return a comment
    public String getCmt(){
        return comment;
    }

    //Set a comment
    public void setComment(String cm){
        comment = cm;
    }
}
