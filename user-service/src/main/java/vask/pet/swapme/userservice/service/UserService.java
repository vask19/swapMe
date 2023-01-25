package vask.pet.swapme.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vask.pet.swapme.userservice.UserRepository;

import vask.pet.swapme.userservice.dto.UserDto;
import vask.pet.swapme.userservice.exeption.UndefinedException;
import vask.pet.swapme.userservice.exeption.UserAlreadyExistsException;
import vask.pet.swapme.userservice.http.requests.CreateUserRequest;
import vask.pet.swapme.userservice.model.User;
import vask.pet.swapme.userservice.util.UserMapper;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final KeycloakAdminClientService keycloakService;
    private final UserMapper userMapper;



    @Transactional
    public UserDto saveUser(CreateUserRequest createUserRequest) {
        Response response = keycloakService.createKeycloakUser(createUserRequest);
        try {
            if (response.getStatus() == 201) {
                User user = userMapper.toUserFromCreateUserRequest(createUserRequest);
                log.info("user with username: {} was saved in keycloak",user.getUsername());
                String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
                user.setUserId(userId);
                userRepository.save(user);
                log.info("user with username: {} saved in database with id: {}", user.getUsername(), user.getUserId());
                return userMapper.toUserDtoFromCreateUserRequest(userRepository.save(user));
            } else if (response.getStatus() == 409) {
                throw new UserAlreadyExistsException(HttpStatus.CONFLICT, (String.format("user with username: %s already exists", createUserRequest.getUsername())));
            }
        } catch (Exception e) {
            throw new UndefinedException(HttpStatus.resolve(response.getStatus()),e.getMessage());

        }
        return new UserDto();


    }

}

//        log.info("user with username: {} didn't accept by keycloak", createUserRequest.getUsername());
//        throw new UserNotSaveException(String.format("user with username: {} didn't accept by keycloak",createUserRequest.getUsername()));
