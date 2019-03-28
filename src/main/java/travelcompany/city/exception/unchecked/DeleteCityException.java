package travelcompany.city.exception.unchecked;

import travelcompany.city.exception.CityExceptionMeta;
import travelcompany.common.business.exception.TravelCompanyUncheckedException;

public class DeleteCityException extends TravelCompanyUncheckedException {


    public DeleteCityException(int code, String message) {
        super(code, message);
    }

    public DeleteCityException(CityExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }

}
