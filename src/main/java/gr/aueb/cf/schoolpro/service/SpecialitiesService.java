package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dao.IDAOSpecialities;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;

public class SpecialitiesService implements ISpecialitiesService{
    private IDAOSpecialities dao;

    public SpecialitiesService(IDAOSpecialities dao){
        this.dao = dao;
    }

    @Override
    public void insertSpeciality(String speciality) throws DAOException {
        dao.insertSpeciality(speciality);
    }
}
