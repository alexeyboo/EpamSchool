package hw2.travelcompany.city.repo.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.repo.CityRepo;
import hw2.travelcompany.city.search.CitySearchCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hw2.travelcompany.common.solutions.utils.StringUtils.isBlank;
import static hw2.travelcompany.storage.Storage.cities;
import static java.lang.Math.abs;

public class CityMemoryArrayRepo implements CityRepo {

    private int cityIndex = 0;
    private CityOrderingComponent orderingComponent = new CityOrderingComponent();

    public void add(City city) {
        if (cityIndex == cities.length) {
            City[] newCities = new City[cities.length * 2];
            System.arraycopy(cities, 0, newCities, 0, cities.length);
            cities = newCities;
        }
        cities[cityIndex] = city;
        cityIndex++;
    }

    public City findById(long id) {
        Integer cityIndex = findCityIndexById(id);
        if (cityIndex != null) {
            return cities[cityIndex];
        }
        return null;
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        if (searchCondition.getId() != null)
            return Collections.singletonList(findById(searchCondition.getId()));
        else {
            List<City> result = doSearch(searchCondition);
            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();

            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }
            return result;
        }
    }

    public List<City> doSearch(CitySearchCondition searchCondition) {
        boolean searchByName = isBlank(searchCondition.getName());
        boolean searchByClimate = (searchCondition.getClimate() != null);
        boolean searchByCountry = (searchCondition.getCountry() != null);
        boolean searchByPopulation = (searchCondition.getPopulation() != null);
        boolean searchByCapital = (searchCondition.isCapital() != null);

        City[] result = new City[cities.length];
        int resultIndex = 0;
        for (City city : cities) {
            if (city != null) {
                boolean found = true;
                if (searchByName)
                    found = searchCondition.getName().equals(city.getName());
                if (found && searchByClimate)
                    found = searchCondition.getClimate().equals(city.getClimate());
                if (found && searchByCountry)
                    found = searchCondition.getCountry().equals(city.getCountry());
                if (found && searchByPopulation) {
                    if (abs(searchCondition.getPopulation() - city.getPopulation()) <= 100000)
                        found = true;
                    else
                        found = false;
                }
                if (found && searchByCapital)
                    found = searchCondition.isCapital().equals(city.isCapital());
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
    public void update(City city) {

    }

    public void deleteCity(City city) {
        Integer foundIndex = findCityIndexByEntity(city);
        if (foundIndex != null)
            deleteCityByIndex(foundIndex);
    }

    public void deleteById(long id) {
        Integer foundIndex = findCityIndexById(id);
        if (foundIndex != null)
            deleteCityByIndex(foundIndex);
    }

    public void printAll() {
        for (City city : cities)
            System.out.println(city);
    }

    private void deleteCityByIndex(int index) {
        City[] newCities = new City[cities.length - 1];
        System.arraycopy(cities, 0, newCities, 0, index);
        System.arraycopy(cities, index + 1, newCities, index, cities.length - index);
        cities = newCities;
        cityIndex--;
    }

    private Integer findCityIndexByEntity(City city) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equals(city))
                return i;
        }
        return null;
    }

    private Integer findCityIndexById(Long cityId) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getId().equals(cityId))
                return i;
        }
        return null;
    }
}
