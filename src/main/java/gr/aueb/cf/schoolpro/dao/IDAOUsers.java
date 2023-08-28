package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolpro.model.User;

import java.sql.SQLException;
import java.util.Map;

public interface IDAOUsers {
    public User findUser(String userName) throws DAOException;

    public User insertUser(User newUser) throws DAOException;

    public void deleteUser(int userid) throws DAOException, UserNotFoundException;

    public Map<Integer, String> returnUserList() throws SQLException;

    public void UpdateUser(User user) throws DAOException, UserNotFoundException;
}
