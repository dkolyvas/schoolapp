package gr.aueb.cf.schoolpro.dao.exceptions;

public class StudentDAOException extends  Exception {
    private static final long serialVersionUID = 1;

    public StudentDAOException() {
    }

    public StudentDAOException(String message) {
        super(message);
    }
}
