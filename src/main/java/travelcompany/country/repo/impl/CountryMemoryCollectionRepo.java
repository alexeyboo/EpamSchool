package travelcompany.country.repo.impl;

import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.repo.CountryRepo;
import hw2.travelcompany.country.search.CountrySearchCondition;
import hw2.travelcompany.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static hw2.travelcompany.common.solutions.utils.StringUtils.isNotBlank;
import static hw2.travelcompany.storage.Storage.countriesList;

public class CountryMemoryCollectionRepo implements CountryRepo {

    private CountrySortingComponent orderingComponent = new CountrySortingComponent();

    @Override
    public void insert(Country country) {
        country.setId(SequenceGenerator.getNextValue());
        countriesList.add(country);
    }

    @Override
    public Country findById(Long id) {
        return findCountryById(id);
    }

    @Override
    public void update(Country country) {

    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            List<Country> result = doSearch(searchCondition);
            boolean needOrdering = !result.isEmpty() && searchCondition.needSorting();
            if (needOrdering) {
                orderingComponent.applySorting(result, searchCondition);
            }
            return result;
        }
    }


    public List<Country> doSearch(CountrySearchCondition searchCondition) {

        List<Country> result = new ArrayList<>();

        for (Country country : countriesList) {
            if (country != null) {
                boolean found = true;

                if (searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(country.getName());
                }
                if (found && searchCondition.searchByLanguage()) {
                    found = searchCondition.getLanguage().equals(country.getLanguage());
                }
                if (found && searchCondition.searchByCity()) {
                    found = country.getCities().contains(searchCondition.getCity());
                }
                if (found) {
                    result.add(country);
                }
            }
        }
        return result;
    }



    @Override
    public void deleteById(Long id) {
        Country found = findCountryById(id);
        if (found != null) {
            countriesList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (Country country : countriesList) {
            System.out.println(country);
        }
    }

    @Override
    public List<Country> findAll() {
        return countriesList;
    }

    private Country findCountryById(long id) {
        for (Country country : countriesList) {
            if (Long.valueOf(id).equals(country.getId())) {
                return country;
            }
        }
        return null;
    }
}
