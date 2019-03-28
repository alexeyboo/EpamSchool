package travelcompany.city.repo.impl;

import travelcompany.city.domain.City;
import travelcompany.city.domain.CityDiscriminator;
import travelcompany.city.domain.typesofcities.Beachable;
import travelcompany.city.domain.typesofcities.Sightseeable;
import travelcompany.city.domain.typesofcities.SkiResortable;
import travelcompany.city.repo.CityRepo;
import travelcompany.city.search.CitySearchCondition;

import travelcompany.common.solutions.utils.ArrayUtils;
import travelcompany.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static travelcompany.storage.Storage.citiesArray;

import static java.lang.Math.abs;

public class CityMemoryArrayRepo implements CityRepo {

    private int cityIndex = 0;
    private CitySortingComponent sortingComponent = new CitySortingComponent();

    @Override
    public void insert(City city) {
        if (cityIndex == citiesArray.length) {
            City[] newCities = new City[citiesArray.length * 2];
            System.arraycopy(citiesArray, 0, newCities, 0, citiesArray.length);
            citiesArray = newCities;
        }
        citiesArray[cityIndex] = city;
        city.setId(SequenceGenerator.getNextValue());
        cityIndex++;
    }

    @Override
    public City findById(Long id) {
        Integer cityIndex = findCityIndexById(id);
        if (cityIndex != null) {
            return citiesArray[cityIndex];
        }
        return null;
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
        City[] foundCities = new City[cities.size()];
        int resultIndex = 0;

        for (City city : cities) {
            if (CityDiscriminator.BEACH.equals(city.getDiscriminator())) {
                Beachable beachable = (Beachable) city;
                boolean found = true;
                if (searchCondition.searchByNumOfBeaches()) {
                    found = searchCondition.getNumOfBeaches().equals(beachable.getNumOfBeaches());
                }
                if (found) {
                    foundCities[resultIndex] = city;
                    resultIndex++;
                }
            }
        }
        if (resultIndex > 0) {
            City toReturn[] = new City[resultIndex];
            System.arraycopy(foundCities, 0, toReturn, 0, resultIndex);
            return Arrays.asList(toReturn);
        }
        return Collections.emptyList();
    }

    private List<City> doSightseeableSearch(CitySearchCondition searchCondition, List<City> cities) {
        City[] foundCities = new City[cities.size()];
        int resultIndex = 0;

        for (City city : cities) {
            if (CityDiscriminator.SIGHTSEE.equals(city.getDiscriminator())) {
                Sightseeable sightSeeable = (Sightseeable) city;
                boolean found = true;
                if (searchCondition.searchByNumOfSights()) {
                    found = searchCondition.getNumOfSights().equals(sightSeeable.getNumOfSights());
                }
                if (found) {
                    foundCities[resultIndex] = city;
                    resultIndex++;
                }
            }
        }
        if (resultIndex > 0) {
            City toReturn[] = new City[resultIndex];
            System.arraycopy(foundCities, 0, toReturn, 0, resultIndex);
            return Arrays.asList(toReturn);
        }
        return Collections.emptyList();
    }

    private List<City> doSkiResortableSearch(CitySearchCondition searchCondition, List<City> cities) {
        City[] foundCities = new City[cities.size()];
        int resultIndex = 0;

        for (City city : cities) {
            if (CityDiscriminator.SKI_RESORT.equals(city.getDiscriminator())) {
                SkiResortable skiResortable = (SkiResortable) city;
                boolean found = true;
                if (searchCondition.searchByNumOfSkiResorts()) {
                    found = searchCondition.getNumOfSkiResorts().equals(skiResortable.getNumOfSkiResorts());
                }
                if (found) {
                    foundCities[resultIndex] = city;
                    resultIndex++;
                }
            }
        }
        if (resultIndex > 0) {
            City toReturn[] = new City[resultIndex];
            System.arraycopy(foundCities, 0, toReturn, 0, resultIndex);
            return Arrays.asList(toReturn);
        }
        return Collections.emptyList();
    }

    private List<City> doSearch(CitySearchCondition searchCondition) {

        City[] result = new City[citiesArray.length];
        int resultIndex = 0;
        for (City city : citiesArray) {
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
                    result[resultIndex] = city;
                    resultIndex++;
                }
            }
        }
        if (resultIndex > 0) {
            City[] toReturn = new City[resultIndex];
            System.arraycopy(result, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
        Integer cityIndex = findCityIndexById(id);

        if (cityIndex != null) {
            deleteCityByIndex(cityIndex);
        }

    }

    private void deleteCityByIndex(int index) {
        ArrayUtils.removeElement(citiesArray, index);
        cityIndex--;
    }

    public void printAll() {
        for (City city : citiesArray) {
            if (city == null) {
                System.out.println(city);
            }
        }
    }

    private Integer findCityIndexById(Long cityId) {
        for (int i = 0; i < citiesArray.length; i++) {
            if (citiesArray[i].getId().equals(cityId))
                return i;
        }
        return null;
    }

    @Override
    public List<City> findAll() {
        return new ArrayList<>(Arrays.asList(citiesArray));
    }

    @Override
    public int countAll() {
        return citiesArray.length;
    }

}
