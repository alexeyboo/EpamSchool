package travelcompany.country.exception.unchecked;

<<<<<<< HEAD
import travelcompany.common.business.exception.TravelCompanyUncheckedException;
import travelcompany.country.exception.CountryExceptionMeta;
=======
import hw2.travelcompany.common.business.exception.TravelCompanyUncheckedException;
import hw2.travelcompany.country.exception.CountryExceptionMeta;
>>>>>>> github/master

public class DeleteCountryException extends TravelCompanyUncheckedException {
    public DeleteCountryException(int code, String message) {
        super(code, message);
    }

    public DeleteCountryException(CountryExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }
}
