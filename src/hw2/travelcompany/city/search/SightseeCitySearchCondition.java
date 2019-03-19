package hw2.travelcompany.city.search;

import hw2.travelcompany.city.search.CitySearchCondition;

public class SightseeCitySearchCondition extends CitySearchCondition {

    private Integer numOfSights;

    public boolean searchByNumOfSights(){
        return numOfSights != null;
    }

    public Integer getNumOfSights() {
        return numOfSights;
    }

    public void setNumOfSights(Integer numOfSights) {
        this.numOfSights = numOfSights;
    }

}
