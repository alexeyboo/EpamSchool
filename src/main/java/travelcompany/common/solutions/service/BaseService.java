package travelcompany.common.solutions.service;

import travelcompany.common.business.exception.TravelCompanyUncheckedException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseService <TYPE, ID>{
    TYPE insert(TYPE entity);

    void insert(Collection<TYPE> entity);

    void update(TYPE entity);

    Optional<TYPE> findById(ID id);

    void deleteById(ID id) throws TravelCompanyUncheckedException;

    void delete(TYPE entity);

    void printAll();

    List<TYPE> findAll();

    int countAll();
}
