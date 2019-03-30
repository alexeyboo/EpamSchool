package travelcompany.common.business.exception;

public abstract class TravelCompanyUncheckedException extends RuntimeException {
    protected int code;

    public TravelCompanyUncheckedException(int code, String message) {
        super(message);
        this.code = code;
    }

    public TravelCompanyUncheckedException(int code, String message, Throwable cause) {
        super(message);
        this.code = code;
        initCause(cause);
    }
}
