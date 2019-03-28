package travelcompany.country.repo;

import travelcompany.common.solutions.repo.BaseRepo;
import travelcompany.country.domain.Country;
import travelcompany.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo <Country, Long> {

    List<? extends Country> search(CountrySearchCondition searchCondition);

}
