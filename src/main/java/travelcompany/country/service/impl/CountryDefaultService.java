package travelcompany.country.service.impl;

import travelcompany.city.domain.City;
import travelcompany.city.service.CityService;
import travelcompany.common.business.exception.TravelCompanyUncheckedException;
import travelcompany.country.domain.Country;
import travelcompany.country.exception.CountryExceptionMeta;
import travelcompany.country.exception.unchecked.DeleteCountryException;
import travelcompany.country.repo.CountryRepo;
import travelcompany.country.search.CountrySearchCondition;
import travelcompany.country.service.CountryService;
import travelcompany.order.repo.OrderRepo;

import java.util.Collection;
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
    public Country insert(Country country) {
        if (country != null) {
            countryRepo.insert(country);

            if (country.getCities() != null && !country.getCities().isEmpty()) {
                for (City city : country.getCities()) {
                    if (city != null) {
                        cityService.insert(city);
                        city.setCountryId(country.getId());
                    }
                }
            }
        }

        return country;
    }

    @Override
    public void insert(Collection<Country> countries) {
        if (countries != null & !countries.isEmpty()) {
            for (Country country : countries) {
                countryRepo.insert(country);

                if (country.getCities() != null && !country.getCities().isEmpty()) {
                    country.getCities().replaceAll(city -> {
                        city.setCountryId(country.getId());

                        return city;
                    });

                    cityService.insert(country.getCities());
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
        if (searchCondition.getId() != null) {
            return Collections.singletonList(countryRepo.findById(searchCondition.getId()));
        } else {
            return countryRepo.search(searchCondition);
        }
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

    @Override
    public int countAll() {
        return countryRepo.countAll();
    }
}
