package travelcompany.common.business.application.servicefactory;

import travelcompany.city.repo.CityRepo;
import travelcompany.city.repo.impl.CityMemoryArrayRepo;
import travelcompany.city.service.CityService;
import travelcompany.city.service.impl.CityDefaultService;
import travelcompany.country.repo.CountryRepo;
import travelcompany.country.repo.impl.CountryMemoryArrayRepo;
import travelcompany.country.service.CountryService;
import travelcompany.country.service.impl.CountryDefaultService;
import travelcompany.order.repo.OrderRepo;
import travelcompany.order.repo.impl.OrderMemoryArrayRepo;
import travelcompany.order.service.OrderService;
import travelcompany.order.service.impl.OrderDefaultService;
import travelcompany.user.repo.UserRepo;
import travelcompany.user.repo.impl.UserMemoryArrayRepo;
import travelcompany.user.service.UserService;
import travelcompany.user.service.impl.UserDefaultService;

public class MemoryArrayServiceFactory implements ServiceFactory{

    private OrderRepo orderRepo = new OrderMemoryArrayRepo();
    private UserRepo userRepo = new UserMemoryArrayRepo();
    private CountryRepo countryRepo = new CountryMemoryArrayRepo();
    private CityRepo cityRepo = new CityMemoryArrayRepo();

    private OrderService orderService = new OrderDefaultService(orderRepo);
    private UserService userService = new UserDefaultService(userRepo, orderService);
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
