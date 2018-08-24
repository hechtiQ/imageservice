package nl.bijenkorf.imageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnsupportedPredefinedTypeException extends RuntimeException {

    public UnsupportedPredefinedTypeException() {
    }

    public UnsupportedPredefinedTypeException(String message) {
        super(message);
    }

    public UnsupportedPredefinedTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedPredefinedTypeException(Throwable cause) {
        super(cause);
    }

    public UnsupportedPredefinedTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
