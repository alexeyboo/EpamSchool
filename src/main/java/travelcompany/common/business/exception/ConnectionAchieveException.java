package travelcompany.common.business.exception;

import static travelcompany.common.business.exception.ConnectionAchieveError.JDBC_CONNECTION_ACHIEVE_EXCEPTION;

public class ConnectionAchieveException extends TravelCompanyUncheckedException {
    public ConnectionAchieveException(Exception cause) {
        super(JDBC_CONNECTION_ACHIEVE_EXCEPTION.getCode(), JDBC_CONNECTION_ACHIEVE_EXCEPTION.getDescription(), cause);
    }
}
