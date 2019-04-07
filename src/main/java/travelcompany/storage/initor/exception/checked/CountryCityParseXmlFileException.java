package travelcompany.storage.initor.exception.checked;

import travelcompany.common.business.exception.TravelCompanyCheckedException;

public class CountryCityParseXmlFileException extends TravelCompanyCheckedException {
    public CountryCityParseXmlFileException(int code, String message, Exception cause) {
        super(code, message);
        initCause(cause);
    }
}
