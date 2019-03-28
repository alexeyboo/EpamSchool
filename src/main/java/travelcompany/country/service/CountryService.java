package travelcompany.country.service;

import travelcompany.common.business.exception.TravelCompanyUncheckedException;
import travelcompany.common.solutions.service.BaseService;
import travelcompany.country.domain.Country;
import travelcompany.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService <Country, Long>{

    List<? extends Country> search(CountrySearchCondition searchCondition);

    void removeAllCitiesFromCountry(Long cityId) throws TravelCompanyUncheckedException;
}
