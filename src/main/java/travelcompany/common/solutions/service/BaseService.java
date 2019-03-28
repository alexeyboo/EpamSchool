package travelcompany.common.solutions.service;

<<<<<<< HEAD
import travelcompany.common.business.exception.TravelCompanyUncheckedException;
=======
import hw2.travelcompany.common.business.exception.TravelCompanyUncheckedException;
>>>>>>> github/master

import java.util.List;

public interface BaseService <TYPE, ID>{

    void insert(TYPE entity);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(ID id) throws TravelCompanyUncheckedException;

    void delete(TYPE entity);

    void printAll();

    List<TYPE> findAll();

<<<<<<< HEAD
    int countAll();

=======
>>>>>>> github/master
}
