package hw2.travelcompany.country.repo;

import hw2.travelcompany.common.solutions.repo.BaseRepo;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo <Country, Long> {

    List<? extends Country> search(CountrySearchCondition searchCondition);

}
