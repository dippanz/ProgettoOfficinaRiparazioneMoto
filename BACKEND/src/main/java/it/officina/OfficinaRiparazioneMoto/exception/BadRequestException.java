package it.officina.OfficinaRiparazioneMoto.exception;

import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

public class BadRequestException extends RuntimeException{

    private final String errorCode;

    public BadRequestException(String errorCode) {
        super(ErrorManager.getErrorMessage(errorCode));
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    
}
