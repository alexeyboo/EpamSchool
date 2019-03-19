package hw2.travelcompany.city.repo.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.domain.CityDiscriminator;
import hw2.travelcompany.city.domain.typesofcities.BeachCity;
import hw2.travelcompany.city.repo.CityRepo;
import hw2.travelcompany.city.search.CitySearchCondition;
import hw2.travelcompany.city.search.BeachCitySearchCondition;
import hw2.travelcompany.city.search.SightseeCitySearchCondition;
import hw2.travelcompany.city.search.SkiResortCitySearchCondition;

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

    @Override
    public void add(City city) {
        if (cityIndex == cities.length) {
            City[] newCities = new City[cities.length * 2];
            System.arraycopy(cities, 0, newCities, 0, cities.length);
            cities = newCities;
        }
        cities[cityIndex] = city;
        cityIndex++;
    }

    @Override
    public List<? extends City> search(CitySearchCondition searchCondition) {
        if (searchCondition.getId() != null)
            return Collections.singletonList(findById(searchCondition.getId()));
        else {
            List<City> result = doSearch(searchCondition);
            CityDiscriminator modelDiscriminator = searchCondition.getCityDiscriminator();

            switch (modelDiscriminator) {
                case BEACH: {
                    return doBeachCitySearch((BeachCitySearchCondition) searchCondition, result);
                }
                case SIGHTSEE: {
                    return doSightseeCitySearch((SightseeCitySearchCondition) searchCondition, result);
                }
                case SKI_RESORT: {
                    return doSkiResortCitySearch((SkiResortCitySearchCondition) searchCondition, result);
                }
            }
//
//            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
//
//            if (needOrdering) {
//                orderingComponent.applyOrdering(result, searchCondition);
//            }
//            return result;
        }
    }

    private List<BeachCity> doBeachCitySearch(BeachCitySearchCondition searchCondition, List<City> cities) {
        BeachCity[] foundCities = new BeachCity[cities.size()];
        int resultIndex = 0;

        for (City city : cities) {
            if (CityDiscriminator.BEACH.equals(city.getDiscriminator())) {
                BeachCity beachCity = (BeachCity) city;
                boolean found = true;
                if (searchCondition.searchByNumOfBeaches()) {
                    found = searchCondition.getNumOfBeaches().equals(beachCity.getNumOfBeaches());
                }
                if (found) {
                    foundCities[resultIndex] = beachCity;
                    resultIndex++;
                }
            }
        }
        if (resultIndex > 0) {
            BeachCity toReturn[] = new BeachCity[resultIndex];
            System.arraycopy(foundCities, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }
        return Collections.emptyList();
    }

    private List<City> doSearch(CitySearchCondition searchCondition) {
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

    @Override
    public City findById(Long id) {
        Integer cityIndex = findCityIndexById(id);
        if (cityIndex != null) {
            return cities[cityIndex];
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Integer cityIndex = findCityIndexById(id);

        if (cityIndex != null) {
            deleteCityByIndex(cityIndex);
        }

    }

    public void deleteCity(City city) {
        Integer foundIndex = findCityIndexByEntity(city);
        if (foundIndex != null)
            deleteCityByIndex(foundIndex);
    }

    public void printAll() {
        for (City city : cities)
            System.out.println(city);
    }

    @Override
    public List<City> findAll() {
        return null;
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
