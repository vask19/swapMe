package vask.pet.swapme.userservice.exeption;


import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends CustomErrorException {
    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(HttpStatus status, String message) {
        super(status, message);
    }

    public UserAlreadyExistsException(HttpStatus status, String message, Object data) {
        super(status, message, data);
    }
}
