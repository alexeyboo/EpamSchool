package hw2.travelcompany.country.search;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.common.business.search.BaseSearchCondition;

public class CountrySearchCondition extends BaseSearchCondition {
    private String name;
    private String language;
    private City city;
    private CountryOrderByField orderByField;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public CountryOrderByField getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(CountryOrderByField orderByField) {
        this.orderByField = orderByField;
    }

    @Override
    public boolean needOrdering() {
        return super.needOrdering() && orderByField != null;
    }
}
