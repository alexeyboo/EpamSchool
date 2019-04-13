package travelcompany.common.business.exception;

public enum ConnectionAchieveError {
    JDBC_SQL_EXCEPTION(1, "Sql error"),
    JDBC_CONNECTION_ACHIEVE_EXCEPTION(10, "Can't get connection"),
    JDBC_KEY_GENERATION_EXCEPTION(20, "Key for field '%' wasn't generated"),
    JDBC_RESULT_SET_MAPPING_EXCEPTION(30, "Error while map result of entity class '%'");

    private int code;
    private String description;

    ConnectionAchieveError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDiscriptionAsFormatStr(Object... args) {
        return String.format(description, args);
    }
}
