package travelcompany.country.repo.impl.memory;

import travelcompany.common.business.search.Paginator;
import travelcompany.country.domain.Country;
import travelcompany.country.repo.CountryRepo;
import travelcompany.country.repo.impl.CountrySortingComponent;
import travelcompany.country.search.CountrySearchCondition;
import travelcompany.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static travelcompany.common.solutions.utils.CollectionUtils.getPageableData;
import static travelcompany.storage.Storage.countriesList;

public class CountryCollectionRepo implements CountryRepo {
    private CountrySortingComponent orderingComponent = new CountrySortingComponent();

    @Override
    public Country insert(Country country) {
        countriesList.add(country);
        country.setId(SequenceGenerator.getNextValue());

        return country;
    }

    @Override
    public void insert(Collection<Country> countries) {
        for (Country country : countries) {
            insert(country);
        }
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
        List<Country> result = doSearch(searchCondition);
        boolean needOrdering = !result.isEmpty() && searchCondition.needSorting();

        if (needOrdering) {
            orderingComponent.applySorting(result, searchCondition);
        }

        if (!result.isEmpty() && searchCondition.shouldPaginate()) {
            result = getPageableCountryData(result, searchCondition.getPaginator());
        }

        return result;
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

                if (found && searchCondition.searchByNumOfCities()) {
                    found = country.getCities().size() <= searchCondition.getNumOfCities();
                }

                if (found) {
                    result.add(country);
                }
            }
        }

        return result;
    }

    private List<Country> getPageableCountryData(List<Country> countries, Paginator paginator) {
        return getPageableData(countries, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void deleteById(Long id) {
        Country found = findCountryById(id);
        if (found != null) {
            countriesList.remove(found);
        }
    }

    private Country findCountryById(long id) {
        for (Country country : countriesList) {
            if (Long.valueOf(id).equals(country.getId())) {
                return country;
            }
        }

        return null;
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

    @Override
    public int countAll() {
        return countriesList.size();
    }
}
