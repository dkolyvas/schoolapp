package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dao.IDAOUsers;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolpro.dto.UserDTO;
import gr.aueb.cf.schoolpro.dto.UserInsertDTO;
import gr.aueb.cf.schoolpro.model.User;
import gr.aueb.cf.schoolpro.security.SecUtil;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements IUserService{

    private IDAOUsers dao;

    public UserServiceImpl(IDAOUsers dao){
        this.dao = dao;
    }

    @Override
    public int insertUser(UserInsertDTO user) throws DAOException {
        User newUser = dao.insertUser(mapToDao(user));
        return newUser.getId();
    }

    @Override
    public void updateUser(UserDTO user) throws DAOException, UserNotFoundException {
        dao.UpdateUser(mapToDao(user));

    }

    @Override
    public void deleteUser(UserDTO user) throws DAOException, UserNotFoundException {
        dao.deleteUser(user.getId());
    }



    @Override
    public boolean authenticate(UserInsertDTO user) throws DAOException {

       User storedUser = dao.findUser(user.getUsername());
       if (storedUser == null) return false;
       return SecUtil.checkPassword(user.getPassword(), storedUser.getPassword());

    }

    @Override
    public boolean checkExist(String username) throws DAOException{
        User user = dao.findUser(username);
        return (user != null);

    }

    private User mapToDao(UserInsertDTO userDTO){
        User user = new User();
        user.setId(-1);
        user.setUsername(userDTO.getUsername());
        user.setPassword(SecUtil.hashPassword(userDTO.getPassword()));
        return user;
    }

    private User mapToDao(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(SecUtil.hashPassword(userDTO.getPassword()));
        return user;
    }

    private UserDTO mapFromDao(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        return dto;
    }
}
