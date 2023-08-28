package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;

public interface ISpecialitiesService {
    void insertSpeciality(String speciality) throws DAOException;
}
