package lv.rtustudents.projektesanasprojekts.services;

import lombok.extern.slf4j.Slf4j;
import lv.rtustudents.projektesanasprojekts.models.User;
import lv.rtustudents.projektesanasprojekts.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AuthenticationService {
    private UserRepo userRepo;

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
            e.printStackTrace();
            return false;
        }
    }
}
