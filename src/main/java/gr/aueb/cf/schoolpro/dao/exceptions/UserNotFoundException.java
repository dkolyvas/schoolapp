package gr.aueb.cf.schoolpro.dao.exceptions;

public class UserNotFoundException extends Exception{
    private static final long serialVersionUID = 1;

    public UserNotFoundException(String username){
        super("No user with username "+ username + "was found");
    }

    public UserNotFoundException(int id) { super("User not found");}


}
