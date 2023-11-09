package lv.rtustudents.projektesanasprojekts.services;

import lombok.extern.slf4j.Slf4j;
import lv.rtustudents.projektesanasprojekts.models.User;
import lv.rtustudents.projektesanasprojekts.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AuthenticationService {
    private final UserRepo userRepo;

    AuthenticationService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepo.findByUsername(username);

        if (user != null) {
            return user.getPassword().equals(password);
        } else {
            return false;
        }
    }

    public boolean createUser(String username, String password) {
        if (userRepo.findByUsername(username) != null) {
            log.warn("User already exists");
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setDateJoined(LocalDateTime.now());

        try {
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(String username) {
        User user = userRepo.findByUsername(username);

        if (user != null) {
            userRepo.delete(user);
            return true;
        } else {
            log.warn("User does not exist");
            return false;
        }
    }
}
