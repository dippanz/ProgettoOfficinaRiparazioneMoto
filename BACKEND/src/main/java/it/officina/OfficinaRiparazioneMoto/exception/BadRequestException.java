package it.officina.OfficinaRiparazioneMoto.exception;

import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

public class BadRequestException extends RuntimeException{

    private final String errorCode;

    public BadRequestException(ErrorManager error) {
        super(error.getMessage());
        this.errorCode = error.getCode();
    }

    public String getErrorCode() {
        return errorCode;
    } 
}
