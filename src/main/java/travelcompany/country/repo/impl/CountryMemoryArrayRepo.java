package travelcompany.country.repo.impl;

import travelcompany.common.business.search.Paginator;
import travelcompany.common.solutions.utils.ArrayUtils;
import travelcompany.country.domain.Country;
import travelcompany.country.repo.CountryRepo;
import travelcompany.country.search.CountrySearchCondition;
import travelcompany.storage.SequenceGenerator;

import java.util.*;

import static travelcompany.common.solutions.utils.CollectionUtils.getPageableData;
import static travelcompany.storage.Storage.countriesArray;

public class CountryMemoryArrayRepo implements CountryRepo {
    private int countryIndex = 0;
    private CountrySortingComponent sortingComponent = new CountrySortingComponent();

    @Override
    public Country insert(Country country) {
        if (countryIndex == countriesArray.length) {
            Country[] newCountries = new Country[countriesArray.length * 2];
            System.arraycopy(countriesArray, 0, newCountries, 0, countriesArray.length);
            countriesArray = newCountries;
        }

        countriesArray[countryIndex] = country;
        country.setId(SequenceGenerator.getNextValue());
        countryIndex++;

        return country;
    }

    @Override
    public void insert(Collection<Country> countries) {
        for (Country country : countries) {
            insert(country);
        }
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
        List<Country> result = doSearch(searchCondition);
        boolean needOrdering = !result.isEmpty() && searchCondition.needSorting();

        if (needOrdering) {
            sortingComponent.applySorting(result, searchCondition);
        }

        if (!result.isEmpty() && searchCondition.shouldPaginate()) {
            return getPageableCountryData(result, searchCondition.getPaginator());
        }

        return result;
    }

    private List<Country> doSearch(CountrySearchCondition searchCondition) {
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

                if (found && searchCondition.searchByNumOfCities()) {
                    found = country.getCities().size() <= searchCondition.getNumOfCities();
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

    private List<Country> getPageableCountryData(List<Country> result, Paginator paginator) {
        return getPageableData(result, paginator.getLimit(), paginator.getOffset());
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

    private Integer findCountryIndexById(long id) {
        for (int i = 0; i < countriesArray.length; i++) {
            if (countriesArray[i].getId().equals(id))
                return i;
        }

        return null;
    }

    @Override
    public List<Country> findAll() {
        return new ArrayList<>(Arrays.asList(countriesArray));
    }

    @Override
    public int countAll() {
        return countriesArray.length;
    }
}
