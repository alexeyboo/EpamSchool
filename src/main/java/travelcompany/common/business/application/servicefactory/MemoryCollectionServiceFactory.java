package travelcompany.common.business.application.servicefactory;

import travelcompany.city.repo.CityRepo;
import travelcompany.city.repo.impl.CityMemoryCollectionRepo;
import travelcompany.city.service.CityService;
import travelcompany.city.service.impl.CityDefaultService;
import travelcompany.country.repo.CountryRepo;
import travelcompany.country.repo.impl.CountryMemoryCollectionRepo;
import travelcompany.country.service.CountryService;
import travelcompany.country.service.impl.CountryDefaultService;
import travelcompany.order.repo.OrderRepo;
import travelcompany.order.repo.impl.OrderMemoryCollectionRepo;
import travelcompany.order.service.OrderService;
import travelcompany.order.service.impl.OrderDefaultService;
import travelcompany.user.repo.UserRepo;
import travelcompany.user.repo.impl.UserMemoryCollectionRepo;
import travelcompany.user.service.UserService;
import travelcompany.user.service.impl.UserDefaultService;

public class MemoryCollectionServiceFactory implements ServiceFactory {

    private CountryRepo countryRepo = new CountryMemoryCollectionRepo();
    private CityRepo cityRepo = new CityMemoryCollectionRepo();
    private OrderRepo orderRepo = new OrderMemoryCollectionRepo();
    private UserRepo userRepo = new UserMemoryCollectionRepo();


    private CityService cityService = new CityDefaultService(cityRepo, orderRepo);
    private OrderService orderService = new OrderDefaultService(orderRepo);
    private UserService userService = new UserDefaultService(userRepo);
    private CountryService countryService = new CountryDefaultService(countryRepo, cityService, orderRepo);

    @Override
    public CountryService getCountryService() {
        return countryService;
    }

    @Override
    public CityService getCityService() {
        return cityService;
    }

    @Override
    public OrderService getOrderService() {
        return orderService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }
}
