package hw2.travelcompany.country.repo.impl;

import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.repo.CountryRepo;
import hw2.travelcompany.country.search.CountrySearchCondition;
import hw2.travelcompany.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hw2.travelcompany.common.solutions.utils.StringUtils.isNotBlank;
import static hw2.travelcompany.storage.Storage.countriesList;

public class CountryMemoryCollectionRepo implements CountryRepo {

    private CountryOrderingComponent orderingComponent = new CountryOrderingComponent();

    @Override
    public void add(Country country) {
        country.setId(SequenceGenerator.getNextValue());
        countriesList.add(country);
    }

    @Override
    public Country findById(long id) {
        return findCountryById(id);
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            List<Country> result = doSearch(searchCondition);
            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }
            return result;
        }
    }


    public List<Country> doSearch(CountrySearchCondition searchCondition) {
        boolean searchByName = isNotBlank(searchCondition.getName());
        boolean searchByLanguage = isNotBlank(searchCondition.getLanguage());
        boolean searchByCity = (searchCondition.getCity() != null);
        List<Country> result = new ArrayList<>();

        for (Country country : countriesList) {
            if (country != null) {
                boolean found = true;

                if (searchByName) {
                    found = searchCondition.getName().equals(country.getName());
                }
                if (found && searchByLanguage) {
                    found = searchCondition.getLanguage().equals(country.getLanguage());
                }
                if (found && searchByCity) {
                    for (int i = 0; i < country.getCities().length; i++) {
                        if (searchCondition.getCity().equals(country.getCities()[i])) {
                            found = true;
                            break;
                        }
                        found = false;
                    }
                }
                if (found) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    @Override
    public void update(Country country) {

    }

    @Override
    public void deleteById(long id) {
        Country found = findCountryById(id);
        if (found != null)
            countriesList.remove(found);
    }

    @Override
    public void printAll() {
        for (Country country : countriesList) {
            System.out.println(country);
        }
    }

    private Country findCountryById(long countryId) {
        for (Country country : countriesList) {
            if (Long.valueOf(countryId).equals(country.getId()))
                return country;

        }
        return null;
    }
}
