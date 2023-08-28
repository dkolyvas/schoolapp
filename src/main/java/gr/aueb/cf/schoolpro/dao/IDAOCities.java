package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;

import java.sql.SQLException;
import java.util.Map;

public interface IDAOCities {
    public Map<Integer, String> getCities() throws SQLException;

    public boolean insertCity(String city) throws DAOException;

    public boolean cityExists(String city) throws DAOException;
}
