package hw2.travelcompany.country.repo;

import hw2.travelcompany.common.solutions.repo.BaseRepo;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo {
    void add(Country country);

    Country findById(long id);

    List<Country> search(CountrySearchCondition searchCondition);

    void update(Country country);
}
