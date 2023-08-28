package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolpro.dto.UserDTO;
import gr.aueb.cf.schoolpro.dto.UserInsertDTO;
import gr.aueb.cf.schoolpro.model.User;

import java.util.List;
import java.util.Set;

public interface IUserService {

    public int insertUser(UserInsertDTO user) throws DAOException;
    public void updateUser(UserDTO user) throws DAOException, UserNotFoundException;
    public void deleteUser(UserDTO user) throws DAOException, UserNotFoundException;


    public boolean authenticate(UserInsertDTO user) throws DAOException;
    public boolean checkExist(String username) throws DAOException;
}
