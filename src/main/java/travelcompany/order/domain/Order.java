package travelcompany.order.domain;

<<<<<<< HEAD
import travelcompany.city.domain.City;
import travelcompany.common.business.domain.BaseDomain;
import travelcompany.user.domain.User;
import travelcompany.country.domain.Country;
=======
import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.common.business.domain.BaseDomain;
import hw2.travelcompany.user.domain.User;
import hw2.travelcompany.country.domain.Country;

import java.util.Arrays;
>>>>>>> github/master

public class Order extends BaseDomain <Long>{

    private int price;
    private User user;
    private Country country;
    private City city;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", user=" + user +
                ", country=" + country +
                ", city=" + city +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
