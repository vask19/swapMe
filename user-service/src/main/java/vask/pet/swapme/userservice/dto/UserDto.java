package vask.pet.swapme.userservice.dto;

import lombok.*;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
