package vask.pet.swapme.userservice.exeption;

import org.springframework.http.HttpStatus;

public class UndefinedException extends CustomErrorException {
    public UndefinedException() {
    }

    public UndefinedException(String message) {
        super(message);
    }

    public UndefinedException(HttpStatus status, String message) {
        super(status, message);
    }

    public UndefinedException(HttpStatus status, String message, Object data) {
        super(status, message, data);
    }
}
