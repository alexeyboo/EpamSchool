package travelcompany.city.search;

import travelcompany.city.domain.*;
import travelcompany.common.business.search.BaseSearchCondition;
import travelcompany.country.domain.Country;

import static travelcompany.common.solutions.utils.StringUtils.isNotBlank;


public class CitySearchCondition extends BaseSearchCondition <Long>{

    private String name;
    private Integer population;
    private Boolean isCapital;
    private Country country;
    private Climate climate;
    private CitySortByField sortByField;
    private CityDiscriminator cityDiscriminator;
    private Integer numOfBeaches;
    private Integer numOfSights;
    private Integer numOfSkiResorts;

    public boolean searchByNumOfSkiResorts() {
        return numOfSkiResorts != null;
    }
    public boolean searchByNumOfSights(){
        return numOfSights != null;
    }
    public boolean searchByNumOfBeaches() {
        return numOfBeaches != null;
    }
    public boolean searchByName() {return isNotBlank(name);}
    public boolean searchByPopulation() {return population != null;}
    public boolean searchByCapital() {return isCapital != null;}
    public boolean searchByCountry() {return country != null;}
    public boolean searchByClimate() {return climate != null;}

    public Integer getNumOfSkiResorts() {
        return numOfSkiResorts;
    }

    public void setNumOfSkiResorts(Integer numOfSkiResorts) {
        this.numOfSkiResorts = numOfSkiResorts;
    }

    public Integer getNumOfSights() {
        return numOfSights;
    }

    public void setNumOfSights(Integer numOfSights) {
        this.numOfSights = numOfSights;
    }



    public Integer getNumOfBeaches() {
        return numOfBeaches;
    }

    public void setNumOfBeaches(Integer numOfBeaches) {
        this.numOfBeaches = numOfBeaches;
    }

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
