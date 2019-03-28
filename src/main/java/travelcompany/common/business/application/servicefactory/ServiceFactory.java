package travelcompany.common.business.application.servicefactory;

import travelcompany.city.service.CityService;
import travelcompany.country.service.CountryService;
import travelcompany.order.service.OrderService;
import travelcompany.user.service.UserService;

public interface ServiceFactory {
    CityService getCityService();
    CountryService getCountryService();
    OrderService getOrderService();
    UserService getUserService();
}
