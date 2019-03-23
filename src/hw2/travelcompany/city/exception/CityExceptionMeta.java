package hw2.travelcompany.city.exception;

public enum CityExceptionMeta {
    DELETE_CITY_CONSTRAINT_ERROR(1, "Can't delete this city yet. The city is being used somewhere in ordersArray.");

    private int code;
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    CityExceptionMeta(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
