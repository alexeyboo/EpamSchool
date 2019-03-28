package travelcompany.country.exception.unchecked;

import travelcompany.common.business.exception.TravelCompanyUncheckedException;
import travelcompany.country.exception.CountryExceptionMeta;

public class DeleteCountryException extends TravelCompanyUncheckedException {
    public DeleteCountryException(int code, String message) {
        super(code, message);
    }

    public DeleteCountryException(CountryExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }
}
