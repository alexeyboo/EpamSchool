package travelcompany.order.dto;


import travelcompany.city.dto.CityDto;
import travelcompany.common.business.dto.BaseDto;
import travelcompany.country.dto.CountryDto;
import travelcompany.user.dto.UserDto;

public class OrderDto extends BaseDto<Long> {
    private int price;
    private UserDto user;
    private CountryDto country;
    private CityDto city;
    private String description;

    public OrderDto() {}

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
