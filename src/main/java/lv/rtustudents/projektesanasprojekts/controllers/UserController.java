package lv.rtustudents.projektesanasprojekts.controllers;

import lv.rtustudents.projektesanasprojekts.models.User;
import lv.rtustudents.projektesanasprojekts.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService authService;

    UserController(UserService authService) {
        this.authService = authService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/authenticate")
    public ResponseEntity<Long> authenticate(@RequestParam String username,
                                                @RequestParam String password) {
        Long response = authService.authenticateUser(username, password);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestParam String username,
                                              @RequestParam String password) {
        Boolean response = authService.createUser(username, password);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteUser(@RequestParam String username) {
        Boolean response = authService.deleteUser(username);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/entity")
    public ResponseEntity<User> getUser(@RequestParam String username) {
        User response = authService.getUser(username);
        return ResponseEntity.ok(response);
    }
}
