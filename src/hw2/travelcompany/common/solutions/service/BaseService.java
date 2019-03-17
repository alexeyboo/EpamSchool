package hw2.travelcompany.common.solutions.service;

import java.util.List;

public interface BaseService <TYPE, ID>{

    void insert(TYPE entity);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(long id);

    void delete(TYPE entity);

    void printAll();

    List<TYPE> findAll();

}
