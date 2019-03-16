package hw2.travelcompany.order.search;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.common.business.search.BaseSearchCondition;
import hw2.travelcompany.country.domain.Country;
import hw2.travelcompany.user.domain.User;

public class OrderSearchCondition extends BaseSearchCondition {
    private Integer price;
    private User user;
    private Country country;
    private City city;

    public Integer getPrice() {
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
}
