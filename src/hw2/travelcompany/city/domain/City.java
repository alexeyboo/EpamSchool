package hw2.travelcompany.city.domain;

import hw2.travelcompany.common.business.domain.BaseDomain;
import hw2.travelcompany.country.domain.Country;

public abstract class City extends BaseDomain <Long>{
    protected String name;
    protected int population;
    protected boolean isCapital;
    protected Country country;
    protected Climate climate;
    protected CityDiscriminator discriminator;

    public CityDiscriminator getDiscriminator() {
        return discriminator;
    }

    public City() {
        initDiscriminator();
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
        return discriminator + "City" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", isCapital=" + isCapital +
                ", country=" + country +
                ", id=" + id +
                ", climate=" + climate +
                '}';
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

    protected abstract void initDiscriminator();

}
