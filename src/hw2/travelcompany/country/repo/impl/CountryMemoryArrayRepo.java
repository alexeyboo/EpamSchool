package hw2.travelcompany.country.repo.impl;

import hw2.travelcompany.common.solutions.utils.ArrayUtils;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.repo.CountryRepo;
import hw2.travelcompany.country.search.CountrySearchCondition;
import hw2.travelcompany.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hw2.travelcompany.common.solutions.utils.StringUtils.isNotBlank;
import static hw2.travelcompany.storage.Storage.countries;

public class CountryMemoryArrayRepo implements CountryRepo {

    private int countryIndex = 0;
    private static final Country[] EMPTY_COUNTRIES_ARR = new Country[0];
    private CountryOrderingComponent orderingComponent = new CountryOrderingComponent();

    @Override
    public void add(Country country) {
        if (countryIndex == countries.length) {
            Country[] newCountries = new Country[countries.length * 2];
            System.arraycopy(countries, 0, newCountries, 0, countries.length);
            countries = newCountries;
        }
        countries[countryIndex] = country;
        country.setId(SequenceGenerator.getNextValue());
        countryIndex++;
    }

    @Override
    public Country findById(long id) {
        Integer countryIndex = findIndexById(id);
        if (countryIndex != null) {
            return countries[countryIndex];
        }
        return null;
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
        boolean searchByCity = searchCondition.getCity() != null;
        Country[] result = new Country[countries.length];
        int resultIndex = 0;
        for (Country country : countries) {
            if (country != null) {
                boolean found = true;
                if (searchByName)
                    found = searchCondition.getName().equals(country.getName());
                if (found && searchByLanguage)
                    found = searchCondition.getLanguage().equals(country.getLanguage());
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
    public void update(Country country) {

    }

    @Override
    public void deleteById(long id) {
        Integer countryIndex = findIndexById(id);
        if (countryIndex != null)
            deleteCountryByIndex(countryIndex);
    }

    @Override
    public void printAll() {
        for (Country country : countries) {
            System.out.println(country);
        }
    }

    private void deleteCountryByIndex(Integer index) {
        ArrayUtils.removeElement(countries, index);
        countryIndex--;
    }

    private Integer findIndexById(Long id) {
        for (int i = 0; i < countries.length; i++) {
            if (countries[i].getId().equals(id))
                return i;
        }
        return null;
    }
}
