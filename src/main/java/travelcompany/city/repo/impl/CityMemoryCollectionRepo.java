package travelcompany.city.repo.impl;

import travelcompany.city.domain.City;
import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.domain.typesofcities.Beachable;
import travelcompany.city.domain.typesofcities.Sightseeable;
import travelcompany.city.domain.typesofcities.SkiResortable;
import travelcompany.city.repo.CityRepo;
import travelcompany.city.search.CitySearchCondition;
import travelcompany.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static travelcompany.storage.Storage.citiesList;
import static java.lang.Math.abs;


public class CityMemoryCollectionRepo implements CityRepo {

    private CitySortingComponent sortingComponent = new CitySortingComponent();
    @Override
    public void insert(City city) {
        citiesList.add(city);
        city.setId(SequenceGenerator.getNextValue());
    }

    @Override
    public City findById(Long id) {
        return findCityById(id);
    }

    @Override
    public void update(City city) {

    }

    @Override
    public List<? extends City> search(CitySearchCondition searchCondition) {
        if (searchCondition.getId() != null)
            return Collections.singletonList(findById(searchCondition.getId()));
        else {
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

            if (needSorting) {
                sortingComponent.applySorting(result, searchCondition);
            }
            return result;
        }
    }

    private List<City> doBeachableSearch(CitySearchCondition searchCondition, List<City> cities) {
        List<City> foundCities = new ArrayList<>();

        for (City city : cities) {
            if (CityDiscriminator.BEACH.equals(city.getDiscriminator())) {
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
            if (CityDiscriminator.SIGHTSEE.equals(city.getDiscriminator())) {
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
            if (CityDiscriminator.SKI_RESORT.equals(city.getDiscriminator())) {
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
                if (searchCondition.searchByName())
                    found = searchCondition.getName().equals(city.getName());
                if (found && searchCondition.searchByClimate())
                    found = searchCondition.getClimate().equals(city.getClimate());
                if (found && searchCondition.searchByCountry())
                    found = searchCondition.getCountry().equals(city.getCountry());
                if (found && searchCondition.searchByPopulation()) {
                    if (abs(searchCondition.getPopulation() - city.getPopulation()) <= 100000)
                        found = true;
                    else
                        found = false;
                }
                if (found && searchCondition.searchByCapital())
                    found = searchCondition.isCapital().equals(city.getIsCapital());
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
        if (found != null)
            citiesList.remove(found);
    }

    @Override
    public void printAll() {
        for (City city: citiesList) {
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
        for (City city: citiesList) {
            if (city.getId().equals(cityId))
                return city;
        }
        return null;
    }
}
