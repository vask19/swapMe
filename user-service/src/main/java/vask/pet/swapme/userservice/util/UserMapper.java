package vask.pet.swapme.userservice.util;

import org.springframework.stereotype.Component;
import vask.pet.swapme.userservice.dto.UserDto;
import vask.pet.swapme.userservice.http.requests.CreateUserRequest;
import vask.pet.swapme.userservice.model.User;

@Component
public class UserMapper {


    public User toUserFromCreateUserRequest(CreateUserRequest createUserRequest){
        return User.builder()
                .username(createUserRequest.getUsername())
                .firstname(createUserRequest.getFirstname())
                .lastname(createUserRequest.getLastname())
                .email(createUserRequest.getEmail())
                .build();
    }

    public UserDto toUserDtoFromCreateUserRequest(User user){
        return UserDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .aboutMe(user.getAboutMe())
                .localisation(user.getLocalisation())
                .rank(user.getRank())
                .userId(user.getUserId())
                .username(user.getUsername())
                .build();
    }
}
