package hw2.travelcompany.city.search;

import hw2.travelcompany.city.domain.City;
import hw2.travelcompany.city.domain.Climate;
import hw2.travelcompany.country.domain.Country;

public enum CityOrderByField {
    NAME("cityname"), POPULATION("citypopulation"), IS_CAPITAL("cityiscapital"), COUNTRY("citycountries"), CLIMATE("cityclimate");

    public String getRequestParamName() {
        return requestParamName;
    }

    CityOrderByField(String requestParamName) {
        this.requestParamName = requestParamName;

    }
    private String requestParamName;
}
