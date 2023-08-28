package gr.aueb.cf.schoolpro.dao.exceptions;

public class InvalidEntryException extends Exception{
    public static final long serialVersionUID = 1;

    public InvalidEntryException(String entry){
        super("Invalid Entry: " + entry);
    }

}
