package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolpro.model.User;
import gr.aueb.cf.schoolpro.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDAOImplementation implements IDAOUsers{

    private Connection connection;

    public UserDAOImplementation() throws SQLException{

        connection = DBUtil.getConnection();
    }



    @Override
    public User findUser(String userName) throws DAOException {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS WHERE  USERNAME = ?");
            ResultSet rs;
            ps.setString(1, userName);
            rs = ps.executeQuery();

            if (!rs.next()) return null;

            User user = new User();
            user.setId(rs.getInt("ID"));
            user.setUsername(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            return user;
        }catch(SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error! Please try again.");
        }

    }

    @Override
    public User insertUser(User newUser) throws DAOException {
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO USERS(USERNAME, PASSWORD) VALUES(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getPassword());
            int i = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(!rs.next()) throw  new DAOException("Database error. Please try again");
            newUser.setId(rs.getInt(1));
            return newUser;

        }catch(SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error! Please try again.");
        }
    }

    @Override
    public void deleteUser(int userid) throws DAOException, UserNotFoundException {
        try{
            PreparedStatement ps = connection.prepareStatement("DELETE FRO USERS WHERE ID = ?");
            ps.setInt( 1,userid);
            int i = ps.executeUpdate();
            if(i == 0) throw new UserNotFoundException(userid);

        }catch (SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error! Please try again.");
        }

    }



    @Override
    public Map<Integer, String> returnUserList() throws SQLException {
        Map<Integer, String> users = new HashMap<>();
        int id;
        String username;
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User newUser = new User();
                id =rs.getInt("ID");
                username = rs.getString("USERNAME");
                users.put(id, username);
                }
            return users;
            }catch(SQLException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void UpdateUser(User user) throws DAOException, UserNotFoundException {
            try{
                PreparedStatement ps = connection.prepareStatement("UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE ID= ?");
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setInt(3, user.getId());
                int i = ps.executeUpdate();
                if(i == 0) throw new UserNotFoundException(user.getUsername());
            }catch(SQLException e){
                e.printStackTrace();
                throw new DAOException("Database error! Please try again.");
            }
    }
}
