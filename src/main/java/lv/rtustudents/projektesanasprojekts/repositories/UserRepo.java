package lv.rtustudents.projektesanasprojekts.repositories;

import lv.rtustudents.projektesanasprojekts.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
