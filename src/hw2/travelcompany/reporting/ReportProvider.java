package hw2.travelcompany.reporting;

import hw2.travelcompany.city.service.CityService;
import hw2.travelcompany.country.service.CountryService;
import hw2.travelcompany.order.service.OrderService;
import hw2.travelcompany.user.service.UserService;

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
