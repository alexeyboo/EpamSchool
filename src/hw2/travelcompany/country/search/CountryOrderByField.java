package hw2.travelcompany.country.search;

import hw2.travelcompany.city.domain.City;

public enum CountryOrderByField {
    NAME("markname"), LANGUAGE("marklanguage");

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }

    CountryOrderByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }
}
