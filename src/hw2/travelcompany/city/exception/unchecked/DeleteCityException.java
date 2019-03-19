package hw2.travelcompany.city.exception.unchecked;

import hw2.travelcompany.city.exception.CityExceptionMeta;
import hw2.travelcompany.common.business.exception.TravelCompanyUncheckedException;

public class DeleteCityException extends TravelCompanyUncheckedException {


    public DeleteCityException(int code, String message) {
        super(code, message);
    }

    public DeleteCityException(CityExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }

}
