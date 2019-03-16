package hw2.travelcompany.city.service.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.repo.CityRepo;
import hw2.travelcompany.city.search.CitySearchCondition;
import hw2.travelcompany.city.service.CityService;

import java.util.List;

public class CityDefaultService implements CityService {

    private final CityRepo cityRepo;

    public CityDefaultService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public void add(City city) {
        cityRepo.add(city);
    }

    public City findById(Long id) {
        return cityRepo.findById(id);
    }

    public void deleteCity(City city) {
        if (city.getId() != null) {
            this.deleteById(city.getId());
        }
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        return cityRepo.search(searchCondition);
    }

    @Override
    public void update(City city) {
        if (city.getId() != null)
            cityRepo.update(city);
    }

    public void deleteById(Long id) {
        cityRepo.deleteById(id);
    }

    public void printAll() {
        cityRepo.printAll();
    }
}
