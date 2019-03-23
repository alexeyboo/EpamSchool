package hw2.travelcompany.country.service.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.repo.CityRepo;
import hw2.travelcompany.city.service.CityService;
import hw2.travelcompany.common.business.exception.TravelCompanyUncheckedException;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.exception.CountryExceptionMeta;
import hw2.travelcompany.country.exception.unchecked.DeleteCountryException;
import hw2.travelcompany.country.repo.CountryRepo;
import hw2.travelcompany.country.search.CountrySearchCondition;
import hw2.travelcompany.country.service.CountryService;
import hw2.travelcompany.order.repo.OrderRepo;

import java.util.Collections;
import java.util.List;

public class CountryDefaultService implements CountryService {

    private final CityService cityService;
    private final CountryRepo countryRepo;
    private final OrderRepo orderRepo;

    public CountryDefaultService(CountryRepo countryRepo, CityService cityService, OrderRepo orderRepo) {
        this.cityService = cityService;
        this.countryRepo = countryRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public void insert(Country country) {
        if (country != null) {
            countryRepo.insert(country);
            if (country.getCities() != null) {
                for (City city : country.getCities()) {
                    if (city != null) {
                        cityService.insert(city);
                    }
                }
            }
        }
    }

    @Override
    public Country findById(Long id) {
        if (id != null) {
            return countryRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Country country) {
        if (country.getId() != null) {
            countryRepo.deleteById(country.getId());
        }
    }

    @Override
    public void deleteById(Long id) throws TravelCompanyUncheckedException {
        if (id != null) {
            boolean noOrders = orderRepo.countByCountry(id) == 0;

            if (noOrders) {
                removeAllCitiesFromCountry(id);
                countryRepo.deleteById(id);
            } else {
                throw new DeleteCountryException(CountryExceptionMeta.DELETE_COUNTRY_CONSTRAINT_ERROR);
            }
        }
    }

    @Override
    public void printAll() {
        countryRepo.printAll();
    }

    @Override
    public List<? extends Country> search(CountrySearchCondition searchCondition) {
        return countryRepo.search(searchCondition);
    }

    @Override
    public void update(Country country) {
        if (country.getId() != null) {
            countryRepo.update(country);
        }
    }

    @Override
    public void removeAllCitiesFromCountry(Long countryId) throws TravelCompanyUncheckedException {
        Country country = findById(countryId);
        if (country != null) {
            List<City> cities = country.getCities() == null ? Collections.emptyList() : country.getCities();

            for (City city : cities) {
                cityService.deleteById(city.getId());
            }

        }

    }

    @Override
    public List<Country> findAll() {
        return countryRepo.findAll();
    }

}
