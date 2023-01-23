package vask.pet.swapme.userservice.dto;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private short rank;
    private String aboutMe;
    private String localisation;
}
