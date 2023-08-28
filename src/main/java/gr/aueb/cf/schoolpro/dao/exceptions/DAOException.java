package gr.aueb.cf.schoolpro.dao.exceptions;

public class DAOException extends Exception {
    private static final long serialVersionUID = 1;

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }
}
