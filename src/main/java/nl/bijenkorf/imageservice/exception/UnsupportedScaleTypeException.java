package nl.bijenkorf.imageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnsupportedScaleTypeException extends RuntimeException {

    public UnsupportedScaleTypeException() {
    }

    public UnsupportedScaleTypeException(String message) {
        super(message);
    }

    public UnsupportedScaleTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedScaleTypeException(Throwable cause) {
        super(cause);
    }

    public UnsupportedScaleTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
