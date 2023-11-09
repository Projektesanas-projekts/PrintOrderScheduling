package lv.rtustudents.projektesanasprojekts.controllers;

import lv.rtustudents.projektesanasprojekts.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class Controller {

    AuthenticationService authService;

    Controller(AuthenticationService authService) {
        this.authService = authService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/authenticate")
    public ResponseEntity<Boolean> authenticate(@RequestParam String username,
                                              @RequestParam String password) {
        Boolean response = authService.authenticateUser(username, password);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/createUser")
    public ResponseEntity<Boolean> createUser(@RequestParam String username,
                                                @RequestParam String password) {
        Boolean response = authService.createUser(username, password);
        return ResponseEntity.ok(response);
    }
}
