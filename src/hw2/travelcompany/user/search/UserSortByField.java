package hw2.travelcompany.user.search;

public enum  UserSortByField {
    FIRST_NAME("firstname"), LAST_NAME("lastname"),
    PASSPORT("passport"), CLIENT_TYPE("clienttype");

    public String getRequestParamName() {
        return requestParamName;
    }

    UserSortByField(String requestParamName) {
        this.requestParamName = requestParamName;

    }
    private String requestParamName;
}
