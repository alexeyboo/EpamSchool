package travelcompany.order.search;

import travelcompany.city.domain.City;
import travelcompany.common.business.search.BaseSearchCondition;
import travelcompany.country.domain.Country;
import travelcompany.user.domain.User;

public class OrderSearchCondition extends BaseSearchCondition <Long>{
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

    public boolean searchByUser() {
        return user != null;
    }

    public boolean searchByCity() {
        return city != null;
    }

    public boolean searchByCountry() {
        return country != null;
    }

    public boolean searchByPrice() {
        return price != null;
    }
}
