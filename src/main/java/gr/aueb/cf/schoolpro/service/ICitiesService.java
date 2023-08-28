package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;

public interface ICitiesService {
    void insertCity(String city) throws DAOException;
    boolean cityExists(String city) throws DAOException;
}
