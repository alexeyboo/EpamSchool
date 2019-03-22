package hw2.travelcompany.country.service.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.repo.CityRepo;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.country.repo.CountryRepo;
import hw2.travelcompany.country.search.CountrySearchCondition;
import hw2.travelcompany.country.service.CountryService;

import java.util.List;

public class CountryDefaultService implements CountryService {

    private final CityRepo cityRepo;
    private final CountryRepo countryRepo;

    public CountryDefaultService(CountryRepo countryRepo, CityRepo cityRepo) {
        this.cityRepo = cityRepo;
        this.countryRepo = countryRepo;
    }


    public void insert(Country country) {
        if (country != null) {
            countryRepo.add(country);
            if (country.getCities() != null) {
                for (City city : country.getCities()) {
                    if (city != null) {
                        cityRepo.insert(city);
                    }
                }
            }
        }
    }

    public Country findById(Long id) {
        if (id != null) {
            return countryRepo.findById(id);
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        if (id != null) {
            countryRepo.deleteById(id);
        }
    }

    public void delete(Country country) {
        if (country.getId() != null) {
            countryRepo.deleteById(country.getId());
        }
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        return countryRepo.search(searchCondition);
    }

    @Override
    public void update(Country country) {
        if (country.getId() != null) {
            countryRepo.update(country);
        }
    }

    public void printAll() {
        countryRepo.printAll();
    }

    @Override
    public List<Country> findAll() {
        return null;
    }

}
