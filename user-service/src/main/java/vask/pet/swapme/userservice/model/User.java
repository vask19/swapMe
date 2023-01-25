package vask.pet.swapme.userservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.value.qual.StringVal;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "userId")
    private String  userId;
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private short rank;
    private String aboutMe;
    private String localisation;
    private LocalDateTime dateOfCreated;
    private LocalDateTime lastUpdated;
}
