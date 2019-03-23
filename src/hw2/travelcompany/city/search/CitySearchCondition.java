package hw2.travelcompany.city.search;

import hw2.travelcompany.city.domain.CityDiscriminator;
import hw2.travelcompany.city.domain.Climate;
import hw2.travelcompany.common.business.search.BaseSearchCondition;
import hw2.travelcompany.country.domain.Country;

public class CitySearchCondition extends BaseSearchCondition <Long>{
    private String name;
    private Integer population;
    private Boolean isCapital;
    private Country country;
    private Climate climate;
    private CitySortByField sortByField;
    private CityDiscriminator cityDiscriminator;

    public CityDiscriminator getCityDiscriminator() {
        return cityDiscriminator;
    }

    public void setCityDiscriminator(CityDiscriminator cityDiscriminator) {
        this.cityDiscriminator = cityDiscriminator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
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

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Boolean getCapital() {
        return isCapital;
    }

    public void setCapital(Boolean capital) {
        isCapital = capital;
    }

    public CitySortByField getSortByField() {
        return sortByField;
    }

    public void setSortByField(CitySortByField sortByField) {
        this.sortByField = sortByField;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    @Override
    public boolean needSorting() {
        return super.needSorting() && sortByField != null;
    }
}
