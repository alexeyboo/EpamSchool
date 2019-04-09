package travelcompany.city.repo.impl.memory;

import travelcompany.city.domain.*;
import travelcompany.city.domain.typesofcities.*;
import travelcompany.city.repo.CityRepo;
import travelcompany.city.repo.impl.CitySortingComponent;
import travelcompany.city.search.CitySearchCondition;

import travelcompany.common.business.search.Paginator;
import travelcompany.common.solutions.utils.ArrayUtils;
import travelcompany.common.solutions.utils.OptionalUtils;
import travelcompany.storage.SequenceGenerator;

import java.util.*;
import java.util.stream.IntStream;

import static travelcompany.common.solutions.utils.CollectionUtils.getPageableData;
import static travelcompany.storage.Storage.citiesArray;

import static java.lang.Math.abs;

public class CityArrayRepo implements CityRepo {
    private int cityIndex = 0;
    private CitySortingComponent sortingComponent = new CitySortingComponent();

    @Override
    public City insert(City city) {
        if (cityIndex == citiesArray.length) {
            City[] newCities = new City[citiesArray.length * 2];
            System.arraycopy(citiesArray, 0, newCities, 0, citiesArray.length);
            citiesArray = newCities;
        }
        citiesArray[cityIndex] = city;
        city.setId(SequenceGenerator.getNextValue());
        cityIndex++;

        return city;
    }

    @Override
    public void insert(Collection<City> cities) {
        for (City city : cities) {
            insert(city);
        }
    }

    @Override
    public Optional<City> findById(Long id) {
        return findCityIndexById(id).map(cityIndex -> citiesArray[cityIndex]);
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
        City[] foundCities = new City[cities.size()];
        int resultIndex = 0;

        for (City city : cities) {
            if (city instanceof Beachable) {
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
            if (city instanceof Sightseeable) {
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
            if (city instanceof SkiResortable) {
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
        findCityIndexById(id).ifPresent(this::deleteCityByIndex);
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

    private Optional<Integer> findCityIndexById(Long cityId) {
        OptionalInt optionalInt = IntStream.range(0, citiesArray.length).filter(i ->
                citiesArray[i] != null && Long.valueOf(cityId).equals(citiesArray[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
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
