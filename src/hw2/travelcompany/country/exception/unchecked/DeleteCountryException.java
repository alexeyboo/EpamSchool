package hw2.travelcompany.country.exception.unchecked;

import hw2.travelcompany.common.business.exception.TravelCompanyUncheckedException;
import hw2.travelcompany.country.exception.CountryExceptionMeta;

public class DeleteCountryException extends TravelCompanyUncheckedException {
    public DeleteCountryException(int code, String message) {
        super(code, message);
    }

    public DeleteCountryException(CountryExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }
}
