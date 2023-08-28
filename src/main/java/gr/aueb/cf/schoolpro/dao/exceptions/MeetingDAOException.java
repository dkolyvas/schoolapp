package gr.aueb.cf.schoolpro.dao.exceptions;

public class MeetingDAOException extends Exception {
    private static final long serialVersionUID = 1;

    public MeetingDAOException(){
        super("Error in processing meetings data");
    }

}
