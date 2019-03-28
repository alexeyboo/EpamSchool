package travelcompany.country.repo.impl;

<<<<<<< HEAD
import travelcompany.common.solutions.utils.ArrayUtils;
import travelcompany.country.domain.Country;
import travelcompany.country.repo.CountryRepo;
import travelcompany.country.search.CountrySearchCondition;
import travelcompany.storage.SequenceGenerator;
=======
import hw2.travelcompany.common.solutions.utils.ArrayUtils;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.repo.CountryRepo;
import hw2.travelcompany.country.search.CountrySearchCondition;
import hw2.travelcompany.storage.SequenceGenerator;
>>>>>>> github/master

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

<<<<<<< HEAD
import static travelcompany.storage.Storage.countriesArray;
=======
import static hw2.travelcompany.storage.Storage.countriesArray;
>>>>>>> github/master

public class CountryMemoryArrayRepo implements CountryRepo {

    private int countryIndex = 0;
    private CountrySortingComponent orderingComponent = new CountrySortingComponent();

    @Override
    public void insert(Country country) {
        if (countryIndex == countriesArray.length) {
            Country[] newCountries = new Country[countriesArray.length * 2];
            System.arraycopy(countriesArray, 0, newCountries, 0, countriesArray.length);
            countriesArray = newCountries;
        }
        countriesArray[countryIndex] = country;
        country.setId(SequenceGenerator.getNextValue());
        countryIndex++;
    }

    @Override
    public void update(Country country) {

    }

    @Override
    public Country findById(Long id) {
        Integer countryIndex = findCountryIndexById(id);
        if (countryIndex != null) {
            return countriesArray[countryIndex];
        }
        return null;
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

        Country[] result = new Country[countriesArray.length];
        int resultIndex = 0;
        for (Country country : countriesArray) {
            if (country != null) {
                boolean found = true;
                if (searchCondition.searchByName())
                    found = searchCondition.getName().equals(country.getName());
                if (found && searchCondition.searchByLanguage())
                    found = searchCondition.getLanguage().equals(country.getLanguage());
                if (found && searchCondition.searchByCity()) {
                    found = country.getCities().contains(searchCondition.getCity());
                }
                if (found) {
                    result[resultIndex] = country;
                    resultIndex++;
                }
            }
        }
        if (resultIndex > 0) {
            Country[] toReturn = new Country[resultIndex];
            System.arraycopy(result, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }
        return Collections.emptyList();
    }



    @Override
    public void deleteById(Long id) {
        Integer countryIndex = findCountryIndexById(id);
        if (countryIndex != null) {
            deleteCountryByIndex(countryIndex);
        }
    }

    private void deleteCountryByIndex(Integer index) {
        ArrayUtils.removeElement(countriesArray, index);
        countryIndex--;
    }

    @Override
    public void printAll() {
        for (Country country : countriesArray) {
            System.out.println(country);
        }
    }

    @Override
    public List<Country> findAll() {
        return new ArrayList<>(Arrays.asList(countriesArray));
    }



    private Integer findCountryIndexById(long id) {
        for (int i = 0; i < countriesArray.length; i++) {
            if (countriesArray[i].getId().equals(id))
                return i;
        }
        return null;
    }
}
