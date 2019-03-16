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
import static hw2.travelcompany.storage.Storage.citiesList;
import static java.lang.Math.abs;

public class CityMemoryCollectionRepo implements CityRepo {
    @Override
    public void add(City city) {
        citiesList.add(city);
    }

    @Override
    public City findById(long id) {
        return findCityById(id);
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        if (searchCondition.getId() != null)
            return Collections.singletonList(findById(searchCondition.getId()));
        boolean searchByName = isBlank(searchCondition.getName());
        boolean searchByClimate = (searchCondition.getClimate() != null);
        boolean searchByCountry = (searchCondition.getCountry() != null);
        boolean searchByPopulation = (searchCondition.getPopulation() != null);
        boolean searchByCapital = (searchCondition.isCapital() != null);

        List<City> result = new ArrayList<>();
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
                    result.add(city);
                }
            }
        }
        return result;
    }

    @Override
    public void update(City city) {

    }

    @Override
    public void deleteById(long id) {
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
    private City findCityById(long cityId) {
        for (City city: citiesList) {
            if (city.getId().equals(cityId))
                return city;
        }
        return null;
    }
}
