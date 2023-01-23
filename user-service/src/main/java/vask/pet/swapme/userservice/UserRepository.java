package vask.pet.swapme.userservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vask.pet.swapme.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
