package hw2.travelcompany.order.domain;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.common.business.domain.BaseDomain;
import hw2.travelcompany.user.domain.User;
import hw2.travelcompany.country.domain.Country;

import java.util.Arrays;

public class Order extends BaseDomain {

    private int price;
    private User[] users;
    private Country[] countries;
    private City[] cities;

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }

//    public Order(ArrayList<User> usersArray, ArrayList<Country> countriesArray) {
//        this.usersArray = usersArray;
//        this.countriesArray = countriesArray;
//        calculateThePrice(usersArray, countriesArray);
//    }

    public int getPrice() {
        return price;
    }

    public User[] getUsers() {
        return users;
    }

    public Country[] getCountries() {
        return countries;
    }

    public void setCountries(Country[] countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", usersArray=" + (users == null ? null : Arrays.asList(users)) +
                ", countriesArray=" + (countries == null ? null : Arrays.asList(countries)) +
                ", citiesArray=" + (cities == null ? null : Arrays.asList(cities)) +
                '}';
    }
}
