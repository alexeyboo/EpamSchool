package hw2.travelcompany.city.search;

import hw2.travelcompany.city.search.CitySearchCondition;

public class BeachCitySearchCondition extends CitySearchCondition {

    private Integer numOfBeaches;

    public boolean searchByNumOfBeaches() {
        return numOfBeaches != null;
    }

    public Integer getNumOfBeaches() {
        return numOfBeaches;
    }

    public void setNumOfBeaches(Integer numOfBeaches) {
        this.numOfBeaches = numOfBeaches;
    }

}
