package travelcompany.order.search;

public enum OrderSortByField {
    PRICE("price"), USER("user"), COUNTRY("country"),
    CITY("city"), DESCRIPTION("description");

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }

    OrderSortByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }
}
