package travelcompany.common.business.exception.jdbc;

import travelcompany.common.business.exception.TravelCompanyUncheckedException;

import static travelcompany.common.business.exception.ConnectionAchieveError.JDBC_SQL_EXCEPTION;

public class SqlException extends TravelCompanyUncheckedException {
    public SqlException(Exception cause) {
        this(JDBC_SQL_EXCEPTION.getCode(), JDBC_SQL_EXCEPTION.getDescription(), cause);
    }

    public SqlException(int code, String message, Exception cause) {
        super(code, message, cause);
    }
}
