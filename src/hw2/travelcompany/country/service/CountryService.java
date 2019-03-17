package hw2.travelcompany.country.service;

import hw2.travelcompany.common.solutions.service.BaseService;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService {
    void add(Country country);

    Country findById(Long id);

    void delete(Country country);

    List<Country> search(CountrySearchCondition searchCondition);

    void update(Country country);
}
