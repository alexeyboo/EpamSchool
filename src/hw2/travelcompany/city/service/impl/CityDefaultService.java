package hw2.travelcompany.city.service.impl;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.repo.CityRepo;
import hw2.travelcompany.city.search.CitySearchCondition;

import java.util.List;

public class CityDefaultService implements hw2.travelcompany.city.service.CityService {

    private final CityRepo cityService;

    public CityDefaultService(CityRepo cityService) {
        this.cityService = cityService;
    }

    @Override
    public void insert(City city) {
        cityService.insert(city);
    }

    @Override
    public City findById(Long id) {
        return cityService.findById(id);
    }

    @Override
    public void delete(City city) {
        if (city.getId() != null) {
            this.deleteById(city.getId());
        }
    }

    @Override
    public List<? extends City> search(CitySearchCondition searchCondition) {
        return cityService.search(searchCondition);
    }

    @Override
    public void update(City city) {
        if (city.getId() != null)
            cityService.update(city);
    }
    @Override
    public void deleteById(Long id) {
        cityService.deleteById(id);
    }
    @Override
    public void printAll() {
        cityService.printAll();
    }

    @Override
    public List<City> findAll() {
        return cityService.findAll();
    }
}
