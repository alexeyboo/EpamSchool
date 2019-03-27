package travelcompany.storage.initor.exception.checked;

import hw2.travelcompany.common.business.exception.TravelCompanyCheckedException;

public class InvalidCityDiscriminatorException extends TravelCompanyCheckedException {
    public InvalidCityDiscriminatorException(int code, String message) {
        super(code, message);
    }

}
