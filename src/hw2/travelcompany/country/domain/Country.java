package hw2.travelcompany.country.domain;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.common.business.domain.BaseDomain;

import java.util.Arrays;

public class Country extends BaseDomain {
    private String name;
    private String language;
    private City[] cities;

    public Country() {
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public City[] getCities() {
        return cities;
    }

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

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", cities=" + getCitiesAsStr() +
                '}';
    }

    private String getCitiesAsStr() {
        StringBuilder stringBuilder = new StringBuilder();
        for (City city : cities) {
            stringBuilder.append(city.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
    public String getAsStrWithoutCities() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'';
    }
}
