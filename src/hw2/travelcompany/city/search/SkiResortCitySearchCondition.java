package hw2.travelcompany.city.search;

public class SkiResortCitySearchCondition extends CitySearchCondition{
    private Integer numOfSkiResorts;

    public boolean searchByNumOfSkiResorts() {
        return numOfSkiResorts != null;
    }

    public Integer getNumOfSkiResorts() {
        return numOfSkiResorts;
    }

    public void setNumOfSkiResorts(Integer numOfSkiResorts) {
        this.numOfSkiResorts = numOfSkiResorts;
    }
}
