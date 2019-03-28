package travelcompany.storage.initor.exception.checked;

<<<<<<< HEAD
import travelcompany.common.business.exception.TravelCompanyCheckedException;
=======
import hw2.travelcompany.common.business.exception.TravelCompanyCheckedException;
>>>>>>> github/master

public class InvalidCityDiscriminatorException extends TravelCompanyCheckedException {
    public InvalidCityDiscriminatorException(int code, String message) {
        super(code, message);
    }

}
