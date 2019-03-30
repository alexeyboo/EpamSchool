package travelcompany.country.exception;

public enum CountryExceptionMeta {
    DELETE_COUNTRY_CONSTRAINT_ERROR(1, "Can't delete country yet. The country is being used somewhere in orders.");

    private int code;
    private String description;

    CountryExceptionMeta(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
