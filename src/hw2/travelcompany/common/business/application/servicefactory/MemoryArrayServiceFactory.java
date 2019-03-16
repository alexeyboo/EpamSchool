package hw2.travelcompany.common.business.application.servicefactory;

import hw2.travelcompany.city.repo.impl.CityMemoryArrayRepo;
import hw2.travelcompany.city.service.CityService;
import hw2.travelcompany.city.service.impl.CityDefaultService;
import hw2.travelcompany.country.repo.impl.CountryMemoryArrayRepo;
import hw2.travelcompany.country.service.CountryService;
import hw2.travelcompany.country.service.impl.CountryDefaultService;
import hw2.travelcompany.order.repo.impl.OrderMemoryArrayRepo;
import hw2.travelcompany.order.service.OrderService;
import hw2.travelcompany.order.service.impl.OrderDefaultService;
import hw2.travelcompany.user.repo.impl.UserMemoryArrayRepo;
import hw2.travelcompany.user.service.UserService;
import hw2.travelcompany.user.service.impl.UserDefaultService;

public class MemoryArrayServiceFactory implements ServiceFactory{
    @Override
    public CityService getCityService() {
        return new CityDefaultService(new CityMemoryArrayRepo());
    }

    @Override
    public CountryService getCountryService() {
        return new CountryDefaultService(new CountryMemoryArrayRepo(), new CityMemoryArrayRepo());
    }

    @Override
    public OrderService getOrderService() {
        return new OrderDefaultService(new OrderMemoryArrayRepo());
    }

    @Override
    public UserService getUserService() {
        return new UserDefaultService(new UserMemoryArrayRepo());
    }
}
