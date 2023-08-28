package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dao.IDAOCities;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;

public class CitiesServiceImpl implements ICitiesService{
    private IDAOCities dao;


    public CitiesServiceImpl(IDAOCities dao){
        this.dao = dao;
    }

    @Override
    public void insertCity(String city) throws DAOException{
            dao.insertCity(city);

    }

    @Override
    public boolean cityExists(String city) throws DAOException {
        return dao.cityExists(city);
    }
}
