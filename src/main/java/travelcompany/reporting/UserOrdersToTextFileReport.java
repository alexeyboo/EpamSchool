package travelcompany.reporting;

import hw2.travelcompany.city.service.CityService;
import hw2.travelcompany.country.service.CountryService;
import hw2.travelcompany.order.domain.Order;
import hw2.travelcompany.order.service.OrderService;
import hw2.travelcompany.user.domain.User;
import hw2.travelcompany.user.service.UserService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UserOrdersToTextFileReport implements ReportComponent {

    private static final String USER_SEPARATOR = "----------------------";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private UserService userService;
    private OrderService orderService;
    private CountryService countryService;
    private CityService cityService;

    public UserOrdersToTextFileReport(UserService userService,
                                      OrderService orderService,
                                      CountryService countryService,
                                      CityService cityService) {
        this.userService = userService;
        this.orderService = orderService;
        this.countryService = countryService;
        this.cityService = cityService;
    }

    @Override
    public File generateReport() throws Exception {
        File tempFile = File.createTempFile(System.currentTimeMillis() + "_user_orders_report", "_io.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            List<String> report = getReportData();

            for (String reportLine : report) {
                writer.write(reportLine);
                writer.newLine();
            }
            writer.flush();
        }
        return tempFile;
    }

    private List<String> getReportData() {
        List<String> report = new ArrayList<>();
        List<User> users = userService.findAll();

        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                report.add(USER_SEPARATOR);
                report.add(userToReportLine(user));
                report.addAll(getOrderInformation(user));
            }
        }
        return report;
    }

    private List<String> getOrderInformation(User user) {
        List<String> reportData = new ArrayList<>();

        List<Order> orders = orderService.getOrdersByUser(user.getId());
        reportData.add("Total ordersArray: " + orders.size());
        
        if (!orders.isEmpty()) {
            reportData.add("Orders");
            for (Order order : orders) {
                reportData.add(orderToReportLine(order));
            }
        }
        return reportData;
    }

    private String orderToReportLine(Order order) {
        StringBuilder orderAsStr = new StringBuilder();

        orderAsStr.append("Order: ").append("Country: ").append(order.getCountry().toString())
                                    .append("City: ").append(order.getCity().toString())
                                    .append("Price: ").append(order.getPrice());

        return orderAsStr.toString();
    }

    private String userToReportLine(User user) {
        StringBuilder userAsStr = new StringBuilder();
        userAsStr.append("User:").append(LINE_SEPARATOR)
                .append("Full name: ").append(user.getFirstName())
                .append(user.getLastName());
        return userAsStr.toString();
    }

}
