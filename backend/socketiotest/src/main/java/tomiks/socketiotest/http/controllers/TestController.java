package tomiks.socketiotest.http.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tomiks.socketiotest.http.config.roles.ModeratorRole;
import tomiks.socketiotest.http.model.User;
import tomiks.socketiotest.http.repository.UserRepository;

@RestController
@AllArgsConstructor
public class TestController {

    private final UserRepository userRepository;

    @ModeratorRole
    @GetMapping("/{userId}")
    public User test(@PathVariable Long userId) {
        if (userId == null) {
            return new User();
        }

        return userRepository.findById(userId).orElse(new User());
    }
}
