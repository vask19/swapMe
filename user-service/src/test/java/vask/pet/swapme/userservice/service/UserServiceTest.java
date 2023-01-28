package vask.pet.swapme.userservice.service;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import vask.pet.swapme.userservice.UserRepository;
import vask.pet.swapme.userservice.dto.UserDto;
import vask.pet.swapme.userservice.http.requests.CreateUserRequest;
import vask.pet.swapme.userservice.model.User;
import vask.pet.swapme.userservice.util.UserMapper;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private KeycloakAdminClientService keycloakAdminClientService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    private static final CreateUserRequest createUserRequest;
    private static User user;
    private static final UserDto userDto;
    static {
        createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail("email@gmail.com");
        createUserRequest.setUsername("username");
        createUserRequest.setLastname("lastname");
        createUserRequest.setFirstname("firstname");
        createUserRequest.setPassword("password");

        user = User.builder()
                .username("username")
                .lastname("lastname")
                .firstname("firstname")
                .password("password")
                .email("email@gmail.com")
                .build();
        userDto = UserDto.builder()
                .username("username")
                .lastname("lastname")
                .firstname("firstname")
                .email("email@gmail.com")
                .build();

    }

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void getUserCreateRequestAndSaveUserIntoKeycloakThenSaveHisInDatabase(){
        Mockito.when(userMapper.toUserFromCreateUserRequest(createUserRequest))
                        .thenReturn(user);
        Mockito.when(userMapper.toUserDtoFromUser(user))
                .thenReturn(userDto);
        Mockito.when(keycloakAdminClientService.createKeycloakUser(createUserRequest))
                .thenReturn(Response.status(201).location(URI.create("/123")).build());
        Response response = keycloakAdminClientService.createKeycloakUser(createUserRequest);
        User user = userMapper.toUserFromCreateUserRequest(createUserRequest);
        user.setUserId("123");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Optional<UserDto> actual = Optional.of(userDto);
        System.out.println(userService);
        var expected  = userService.saveUser(createUserRequest);
        assertEquals(expected,actual);



    }
}
