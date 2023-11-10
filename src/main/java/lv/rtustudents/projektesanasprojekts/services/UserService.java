package lv.rtustudents.projektesanasprojekts.services;

import lombok.extern.slf4j.Slf4j;
import lv.rtustudents.projektesanasprojekts.models.User;
import lv.rtustudents.projektesanasprojekts.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepo userRepo;

    UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> user = userRepo.findByUsername(username);

        if (user.isPresent()) {
            return password.equals(user.get().getPassword());
        } else {
            log.warn("USER AUTHENTICATION | User is already present");
            return false;
        }
    }

    public boolean createUser(String username, String password) {
        if (userRepo.findByUsername(username).isPresent()) {
            log.warn("CREATE USER | User already exists");
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
        Optional<User> user = userRepo.findByUsername(username);

        if (user.isPresent()) {
            userRepo.delete(user.get());
            return true;
        } else {
            log.warn("User does not exist");
            return false;
        }
    }

    public User getUser(String username) {
        Optional<User> user = userRepo.findByUsername(username);

        if (user.isPresent()) {
            return user.get();
        } else {
            log.warn("User does not exist");
            return null;
        }
    }
}
