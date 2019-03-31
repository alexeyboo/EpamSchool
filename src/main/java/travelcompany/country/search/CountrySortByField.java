package travelcompany.country.search;

public enum CountrySortByField {
    NAME("countryname"), LANGUAGE("countrylanguage"),
    NUM_OF_CITIES("numofcities");

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }

    CountrySortByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }
}
