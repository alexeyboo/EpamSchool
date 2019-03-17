package hw2.travelcompany.common.business.exception;

public abstract class TravelCompanyUncheckedException extends RuntimeException {

    protected int code;

    public TravelCompanyUncheckedException(String message, int code) {
        super(message);
        this.code = code;
    }

    public TravelCompanyUncheckedException(String message, Throwable cause, int code) {
        super(message);
        this.code = code;
        initCause(cause);
    }
}
