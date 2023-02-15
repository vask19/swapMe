package vask.pet.swapme.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vask.pet.swapme.userservice.UserRepository;

import vask.pet.swapme.userservice.dto.UserDto;
import vask.pet.swapme.userservice.exeption.KeycloakBasicException;
import vask.pet.swapme.userservice.exeption.UndefinedException;
import vask.pet.swapme.userservice.exeption.UserAlreadyExistsException;
import vask.pet.swapme.userservice.http.requests.CreateUserRequest;
import vask.pet.swapme.userservice.model.User;
import vask.pet.swapme.userservice.util.UserMapper;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    //private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final KeycloakAdminClientService keycloakService;
    private final UserMapper userMapper;



    /**
     * saveUser - first step is saving user in keycloak
     *  if user has been saved successfully in keycloak database
     *  user will be saved in user-service's database
     *
     * @param createUserRequest
     * @return UserDto
     * */
    @Transactional
    public Optional<UserDto> saveUser(CreateUserRequest createUserRequest) {
        Response response = keycloakService.createKeycloakUser(createUserRequest);
        try {
            if (response.getStatus() == 201) {
                User user = userMapper.toUserFromCreateUserRequest(createUserRequest);
                log.info("user with username: {} was saved in keycloak",user.getUsername());
                String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
                user.setUserId(userId);
                user = userRepository.save(user);
                log.info("user with username: {} saved in database with id: {}", user.getUsername(), user.getUserId());
                return Optional.ofNullable(userMapper.toUserDtoFromUser(user));
            } else if (response.getStatus() == 409) {
                throw new UserAlreadyExistsException(HttpStatus.CONFLICT, (String.format("user with username: %s already exists", createUserRequest.getUsername())));
            }
        } catch (Exception e) {
            throw new UndefinedException(HttpStatus.resolve(response.getStatus()),e.getMessage(),e.getStackTrace());
        }
        throw new KeycloakBasicException(HttpStatus.NOT_FOUND,"keycloak exception");

    }


}

