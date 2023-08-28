package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;

import java.sql.SQLException;
import java.util.Map;

public interface IDAOSpecialities {
    public Map<Integer, String> getSpecialities() throws SQLException;
    public boolean insertSpeciality(String speciality) throws DAOException;

}
