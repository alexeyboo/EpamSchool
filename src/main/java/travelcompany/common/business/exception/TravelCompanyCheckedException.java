package travelcompany.common.business.exception;

public abstract class TravelCompanyCheckedException extends Exception {

    protected int code;

    public TravelCompanyCheckedException(int code, String message) {
        super(message);
        this.code = code;
    }

    public TravelCompanyCheckedException(int code, String message, Exception cause) {
        super(message);
        this.code = code;
        initCause(cause);
    }
}
