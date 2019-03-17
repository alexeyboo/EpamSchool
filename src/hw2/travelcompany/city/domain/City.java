package hw2.travelcompany.city.domain;

import hw2.travelcompany.common.business.domain.BaseDomain;
import hw2.travelcompany.country.domain.Country;

public class City extends BaseDomain <Long>{
    private String name;
    private int population;
    private boolean isCapital;
    private Country country;
    private Climate climate;

    public City() {
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", isCapital=" + isCapital +
                ", country=" + country +
                ", id=" + id +
                ", climate=" + climate +
                '}';
    }

    public City(String name, int population, boolean isCapital, Country[] countries, Climate climate) {
        this.name = name;
        this.population = population;
        this.isCapital = isCapital;
        this.country = country;
        this.climate = climate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean isCapital) {
        this.isCapital = isCapital;
    }



}
