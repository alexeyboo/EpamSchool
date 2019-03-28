package travelcompany.city.service.impl;

import travelcompany.city.domain.City;
import travelcompany.city.exception.CityExceptionMeta;
import travelcompany.city.exception.unchecked.DeleteCityException;
import travelcompany.city.repo.CityRepo;
import travelcompany.city.search.CitySearchCondition;
import travelcompany.city.service.CityService;
import travelcompany.order.repo.OrderRepo;

import java.util.List;

public class CityDefaultService implements CityService {

    private final CityRepo cityRepo;
    private final OrderRepo orderRepo;

    public CityDefaultService(CityRepo cityRepo, OrderRepo orderRepo) {
        this.cityRepo = cityRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public void insert(City city) {
        if (city != null) {
            cityRepo.insert(city);
        }
    }

    @Override
    public City findById(Long id) {
        if (id != null) {
            return cityRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(City city) {
        if (city.getId() != null) {
            this.deleteById(city.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            boolean noOrders = orderRepo.countByCity(id) == 0;
            if (noOrders) {
                cityRepo.deleteById(id);
            } else {
                throw new DeleteCityException(CityExceptionMeta.DELETE_CITY_CONSTRAINT_ERROR);
            }
        }
    }

    @Override
    public void printAll() {
        cityRepo.printAll();
    }

    @Override
    public List<? extends City> search(CitySearchCondition searchCondition) {
        return cityRepo.search(searchCondition);
    }

    @Override
    public void update(City city) {
        if (city.getId() != null)
            cityRepo.update(city);
    }

    @Override
    public List<City> findAll() {
        return cityRepo.findAll();
    }

    @Override
    public int countAll() {
        return cityRepo.countAll();
    }
}
