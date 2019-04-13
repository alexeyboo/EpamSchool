package travelcompany.common.business.exception.jdbc;

import travelcompany.common.business.exception.TravelCompanyUncheckedException;

import static travelcompany.common.business.exception.ConnectionAchieveError.JDBC_KEY_GENERATION_EXCEPTION;

public class KeyGeneratorException extends TravelCompanyUncheckedException {
    public KeyGeneratorException(String generatedKey) {
        super(JDBC_KEY_GENERATION_EXCEPTION.getCode(), JDBC_KEY_GENERATION_EXCEPTION.getDiscriptionAsFormatStr(generatedKey));
    }
}
