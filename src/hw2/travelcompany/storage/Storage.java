package hw2.travelcompany.storage;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.order.domain.Order;
import hw2.travelcompany.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final int CAPACITY = 3;
    public static Country[] countries = new Country[CAPACITY];
    public static City[] cities = new City[CAPACITY];
    public static Order[] orders = new Order[CAPACITY];
    public static User[] users = new User[CAPACITY];

    public static List<City> citiesList = new ArrayList<>();
    public static List<Country> countriesList = new ArrayList<>();
    public static List<Order> ordersList = new ArrayList<>();
    public static List<User> usersList = new ArrayList<>();
}
