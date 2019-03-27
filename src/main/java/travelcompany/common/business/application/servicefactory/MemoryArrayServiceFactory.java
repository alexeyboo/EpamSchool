package travelcompany.common.business.application.servicefactory;

import hw2.travelcompany.city.repo.CityRepo;
import hw2.travelcompany.city.repo.impl.CityMemoryArrayRepo;
import hw2.travelcompany.city.service.CityService;
import hw2.travelcompany.city.service.impl.CityDefaultService;
import hw2.travelcompany.country.repo.CountryRepo;
import hw2.travelcompany.country.repo.impl.CountryMemoryArrayRepo;
import hw2.travelcompany.country.service.CountryService;
import hw2.travelcompany.country.service.impl.CountryDefaultService;
import hw2.travelcompany.order.repo.OrderRepo;
import hw2.travelcompany.order.repo.impl.OrderMemoryArrayRepo;
import hw2.travelcompany.order.service.OrderService;
import hw2.travelcompany.order.service.impl.OrderDefaultService;
import hw2.travelcompany.user.repo.UserRepo;
import hw2.travelcompany.user.repo.impl.UserMemoryArrayRepo;
import hw2.travelcompany.user.service.UserService;
import hw2.travelcompany.user.service.impl.UserDefaultService;

public class MemoryArrayServiceFactory implements ServiceFactory{

    private OrderRepo orderRepo = new OrderMemoryArrayRepo();
    private UserRepo userRepo = new UserMemoryArrayRepo();
    private CountryRepo countryRepo = new CountryMemoryArrayRepo();
    private CityRepo cityRepo = new CityMemoryArrayRepo();

    private OrderService orderService = new OrderDefaultService(orderRepo);
    private UserService userService = new UserDefaultService(userRepo);
    private CityService cityService = new CityDefaultService(cityRepo, orderRepo);
    private CountryService countryService = new CountryDefaultService(countryRepo, cityService, orderRepo);


    @Override
    public OrderService getOrderService() {
        return orderService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public CountryService getCountryService() {
        return countryService;
    }

    @Override
    public CityService getCityService() {
        return cityService;
    }
}
