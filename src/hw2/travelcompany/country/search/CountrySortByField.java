package hw2.travelcompany.country.search;

public enum CountrySortByField {
    NAME("countryname"), LANGUAGE("countrylanguage");

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }

    CountrySortByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }
}
