package travelcompany.country.search;

import travelcompany.city.domain.City;
import travelcompany.common.business.search.BaseSearchCondition;

import static travelcompany.common.solutions.utils.StringUtils.isNotBlank;

public class CountrySearchCondition extends BaseSearchCondition <Long>{

    private String name;
    private String language;
    private City city;
    private Integer numOfCities;
    private CountrySortByField sortByField;

    public boolean searchByName(){
        return isNotBlank(name);
    }
    public boolean searchByLanguage(){
        return isNotBlank(language);
    }
    public boolean searchByCity(){
        return city != null;
    }
    public boolean searchByNumOfCities(){ return numOfCities != null; }

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

    public CountrySortByField getSortByField() {
        return sortByField;
    }

    public void setSortByField(CountrySortByField sortByField) {
        this.sortByField = sortByField;
    }

    public Integer getNumOfCities() {
        return numOfCities;
    }

    public void setNumOfCities(Integer numOfCities) {
        this.numOfCities = numOfCities;
    }

    @Override
    public boolean needSorting() {
        return super.needSorting() && sortByField != null;
    }
}
