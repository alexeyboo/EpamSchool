package hw2.travelcompany.country.service;

import hw2.travelcompany.common.business.exception.TravelCompanyUncheckedException;
import hw2.travelcompany.common.solutions.service.BaseService;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService <Country, Long>{

    List<? extends Country> search(CountrySearchCondition searchCondition);

    void removeAllCitiesFromCountry(Long cityId) throws TravelCompanyUncheckedException;
}
