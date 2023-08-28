package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CitiesDAOImpl  implements IDAOCities{
    private Connection connection;

    public CitiesDAOImpl() throws SQLException {
        connection = DBUtil.getConnection();

    }

    @Override
    public Map<Integer, String> getCities() throws SQLException {
        Map<Integer, String> cities = new HashMap<>();
        int id;
        String city;
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CITIES");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("ID");
                city = rs.getString("CITY");
                cities.put(id, city);
            }
            return cities;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean insertCity(String city) throws DAOException {
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO CITIES(CITY) VALUES(?)");
            ps.setString(1, city);
            int i = ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error. Unable to handle your request");
        }
    }

    @Override
    public boolean cityExists(String city) throws DAOException {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM CITIES WHERE CITY = ?")){
            ps.setString(1, city);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error!");
        }
    }
}
