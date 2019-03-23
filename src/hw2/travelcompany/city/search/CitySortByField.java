package hw2.travelcompany.city.search;

public enum CitySortByField {
    NAME("cityname"), POPULATION("citypopulation"),
    IS_CAPITAL("cityiscapital"), COUNTRY("citycountries"), CLIMATE("cityclimate"),
    NUM_OF_BEACHES("numofbeaches"), NUM_OF_SIGHTS("numofsights"), NUM_OF_SKIRESORTS("numofskiresorts");

    public String getRequestParamName() {
        return requestParamName;
    }

    CitySortByField(String requestParamName) {
        this.requestParamName = requestParamName;

    }
    private String requestParamName;
}
