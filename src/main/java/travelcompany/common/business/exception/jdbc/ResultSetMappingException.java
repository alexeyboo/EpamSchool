package travelcompany.common.business.exception.jdbc;

import travelcompany.common.business.exception.TravelCompanyUncheckedException;

import static travelcompany.common.business.exception.ConnectionAchieveError.JDBC_RESULT_SET_MAPPING_EXCEPTION;

public class ResultSetMappingException extends TravelCompanyUncheckedException {
    public ResultSetMappingException(String entityClassName, Exception e) {
        super(JDBC_RESULT_SET_MAPPING_EXCEPTION.getCode(),
              JDBC_RESULT_SET_MAPPING_EXCEPTION.getDiscriptionAsFormatStr(entityClassName), e);
    }
}
