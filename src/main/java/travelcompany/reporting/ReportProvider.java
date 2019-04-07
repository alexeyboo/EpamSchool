package travelcompany.reporting;

import travelcompany.city.service.CityService;
import travelcompany.country.service.CountryService;
import travelcompany.order.service.OrderService;
import travelcompany.user.service.UserService;

import java.io.File;

public class ReportProvider {
    private final UserService userService;
    private final OrderService orderService;
    private final CountryService countryService;
    private final CityService cityService;

    private ReportComponent userOrdersTextFileReport;

    public ReportProvider(UserService userService, OrderService orderService,
                          CountryService countryService, CityService cityService) {
        this.userService = userService;
        this.orderService = orderService;
        this.countryService = countryService;
        this.cityService = cityService;

        initReportComponents();
    }

    private void initReportComponents() {
        userOrdersTextFileReport = new UserOrdersToTextFileReport(
                userService,
                orderService,
                countryService,
                cityService);
    }

    public File getUserOrdersToTextFileReport() throws Exception {
        return userOrdersTextFileReport.generateReport();
    }
}
