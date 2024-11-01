package tomiks.socketiotest.http.controllers.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomiks.socketiotest.http.model.auth.UserRequestRegister;
import tomiks.socketiotest.http.service.UserService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestRegister user) {
        return userService.registerUser(user) ? ResponseEntity.ok("SUCCESS") : ResponseEntity.badRequest().body("FAILURE");
    }

}
