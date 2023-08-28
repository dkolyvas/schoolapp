package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SpecilaitiesDAOImpl implements IDAOSpecialities{
    private Connection connection;

    public SpecilaitiesDAOImpl() throws SQLException{
        connection = DBUtil.getConnection();
    }
    @Override
    public Map<Integer, String> getSpecialities() throws SQLException {
        Map<Integer, String> specialities = new HashMap<>();
        int id;
        String speciality;
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM SPECIALITIES");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                id = rs.getInt("ID");
                speciality = rs.getString("SPECIALITY");
                specialities.put(id, speciality);
            }
            return specialities;

        }catch (SQLException e){
            e.printStackTrace();
            throw    e;
        }
    }

    @Override
    public boolean insertSpeciality(String speciality) throws DAOException {
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO SPECIALITIES(SPECIALITY) VALUES (?)");
            ps.setString(1,speciality);
            int i = ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error. Unable to handle your request");
        }
    }
}
