package travelcompany.city.repo.impl.memory;

import travelcompany.city.domain.*;
import travelcompany.city.domain.typesofcities.*;
import travelcompany.city.repo.CityRepo;
import travelcompany.city.repo.impl.CitySortingComponent;
import travelcompany.city.search.CitySearchCondition;
import travelcompany.common.business.search.Paginator;
import travelcompany.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static travelcompany.common.solutions.utils.CollectionUtils.getPageableData;
import static travelcompany.storage.Storage.citiesList;
import static java.lang.Math.abs;

public class CityCollectionRepo implements CityRepo {
    private CitySortingComponent sortingComponent = new CitySortingComponent();

    @Override
    public City insert(City city) {
        citiesList.add(city);
        city.setId(SequenceGenerator.getNextValue());

        return city;
    }

    @Override
    public void insert(Collection<City> cities) {
        for (City city : cities) {
            insert(city);
        }
    }

    @Override
    public City findById(Long id) {
        return findCityById(id);
    }

    @Override
    public void update(City city) {}

    @Override
    public List<? extends City> search(CitySearchCondition searchCondition) {
        List<City> result = doSearch(searchCondition);
        CityDiscriminator cityDiscriminator = searchCondition.getCityDiscriminator();

        switch (cityDiscriminator) {
            case BEACH: {
                result = doBeachableSearch(searchCondition, result);
                break;
            }
            case SIGHTSEE: {
                result = doSightseeableSearch(searchCondition, result);
                break;
            }
            case SKI_RESORT: {
                result = doSkiResortableSearch(searchCondition, result);
                break;
            }
            case BEACH_N_SIGHTSEE: {
                result = doBeachableSearch(searchCondition, result);
                result = doSightseeableSearch(searchCondition, result);
                break;
            }
            case BEACH_N_SKI_RESORT: {
                result = doBeachableSearch(searchCondition, result);
                result = doSkiResortableSearch(searchCondition, result);
                break;
            }
            case SIGHTSEE_N_SKI_RESORT: {
                result = doSightseeableSearch(searchCondition, result);
                result = doSkiResortableSearch(searchCondition, result);
                break;
            }
            case BEACH_N_SIGHTSEE_N_SKI_RESORT: {
                result = doBeachableSearch(searchCondition, result);
                result = doSkiResortableSearch(searchCondition, result);
                result = doSightseeableSearch(searchCondition, result);
                break;
            }
        }

        boolean needSorting = !result.isEmpty() && searchCondition.needSorting();
        boolean shouldPaginate = !result.isEmpty() && searchCondition.shouldPaginate();

        if (needSorting) {
            sortingComponent.applySorting(result, searchCondition);
        }

        if (shouldPaginate) {
            result = getPageableCityData(result, searchCondition.getPaginator());
        }

        return result;
    }

    private List<City> getPageableCityData(List<City> result, Paginator paginator) {
        return getPageableData(result, paginator.getLimit(), paginator.getOffset());
    }

    private List<City> doBeachableSearch(CitySearchCondition searchCondition, List<City> cities) {
        List<City> foundCities = new ArrayList<>();

        for (City city : cities) {
            if (city instanceof Beachable) {
                Beachable beachable = (Beachable) city;
                boolean found = true;

                if (searchCondition.searchByNumOfBeaches()) {
                    found = searchCondition.getNumOfBeaches().equals(beachable.getNumOfBeaches());
                }
                if (found) {
                    foundCities.add(city);
                }
            }
        }

        return foundCities;
    }

    private List<City> doSightseeableSearch(CitySearchCondition searchCondition, List<City> cities) {
        List<City> foundCities = new ArrayList<>();

        for (City city : cities) {
            if (city instanceof Sightseeable) {
                Sightseeable sightSeeable = (Sightseeable) city;
                boolean found = true;

                if (searchCondition.searchByNumOfSights()) {
                    found = searchCondition.getNumOfSights().equals(sightSeeable.getNumOfSights());
                }
                if (found) {
                    foundCities.add(city);
                }
            }
        }

        return foundCities;
    }

    private List<City> doSkiResortableSearch(CitySearchCondition searchCondition, List<City> cities) {
        List<City> foundCities = new ArrayList<>();

        for (City city : cities) {
            if (city instanceof SkiResortable) {
                SkiResortable skiResortable = (SkiResortable) city;
                boolean found = true;

                if (searchCondition.searchByNumOfSkiResorts()) {
                    found = searchCondition.getNumOfSkiResorts().equals(skiResortable.getNumOfSkiResorts());
                }
                if (found) {
                    foundCities.add(city);
                }
            }
        }

        return foundCities;
    }

    private List<City> doSearch(CitySearchCondition searchCondition) {
        List<City> result = new ArrayList<>();

        for (City city : citiesList) {
            if (city != null) {
                boolean found = true;
                if (searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(city.getName());
                }
                if (found && searchCondition.searchByClimate()) {
                    found = searchCondition.getClimate().equals(city.getClimate());
                }
                if (found && searchCondition.searchByPopulation()) {
                    found = abs(searchCondition.getPopulation() - city.getPopulation()) <= 100000;
                }
                if (found && searchCondition.searchByCapital()) {
                    found = searchCondition.isCapital().equals(city.getIsCapital());
                }
                if (found) {
                    result.add(city);
                }
            }
        }

        return result;
    }

    @Override
    public void deleteById(Long id) {
        City found = findCityById(id);

        if (found != null) {
            citiesList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (City city : citiesList) {
            System.out.println(city);
        }
    }

    @Override
    public List<City> findAll() {
        return citiesList;
    }

    @Override
    public int countAll() {
        return citiesList.size();
    }

    private City findCityById(long cityId) {
        for (City city : citiesList) {
            if (city.getId().equals(cityId)) {
                return city;
            }
        }

        return null;
    }
}
