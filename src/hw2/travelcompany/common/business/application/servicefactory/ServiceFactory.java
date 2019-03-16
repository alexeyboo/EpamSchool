package hw2.travelcompany.common.business.application.servicefactory;

import hw2.travelcompany.city.service.CityService;
import hw2.travelcompany.country.service.CountryService;
import hw2.travelcompany.order.service.OrderService;
import hw2.travelcompany.user.service.UserService;

public interface ServiceFactory {
    CityService getCityService();
    CountryService getCountryService();
    OrderService getOrderService();
    UserService getUserService();
}
