package vask.pet.swapme.userservice.exeption;

public class UserNotSaveException extends RuntimeException{

    public UserNotSaveException(String message){
        super(message);
    }
}
