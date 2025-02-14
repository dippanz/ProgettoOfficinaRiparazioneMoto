package it.officina.OfficinaRiparazioneMoto.exception;

import it.officina.OfficinaRiparazioneMoto.utils.Constants.ErrorManager;

/**
 * Exception thrown when a bad request is encountered.
 * <p>
 * This exception is a runtime exception that encapsulates an error code and
 * message
 * defined in {@link ErrorManager}. It is typically thrown when a request does
 * not meet
 * the expected criteria or when a validation error occurs.
 * </p>
 */
public class BadRequestException extends RuntimeException {

    private final String errorCode;

    /**
     * Constructs a new {@code BadRequestException} with the specified error
     * details.
     * <p>
     * The error details are provided through an {@link ErrorManager} instance,
     * which
     * supplies both the error message and error code.
     * </p>
     *
     * @param error the {@link ErrorManager} containing the error message and code
     */
    public BadRequestException(ErrorManager error) {
        super(error.getMessage());
        this.errorCode = error.getCode();
    }

    /**
     * Retrieves the error code associated with this exception.
     *
     * @return the error code as a {@link String}
     */
    public String getErrorCode() {
        return errorCode;
    }
}
