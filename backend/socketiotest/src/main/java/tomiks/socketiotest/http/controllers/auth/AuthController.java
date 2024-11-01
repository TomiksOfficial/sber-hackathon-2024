package tomiks.socketiotest.http.controllers.auth;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tomiks.socketiotest.http.model.auth.UserRequestLogin;
import tomiks.socketiotest.http.service.UserService;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(HttpServletRequest request, @RequestBody UserRequestLogin user) {
        try {
            request.login(user.getLogin(), user.getPassword());

            return ResponseEntity.ok(userService.getUserByEmailOrUsername(user.getLogin()));
        } catch (Exception e) {

            if (request.getRemoteUser() != null) {
//                System.out.println("Remote user: " + request.getRemoteUser());
                return ResponseEntity.ok(userService.getUserByEmailOrUsername(request.getRemoteUser()));
            }

            return ResponseEntity.status(400).body("BAD LOGIN");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();

        if (session != null) {
            session.invalidate();
        }

        Arrays.stream(request.getCookies()).forEach(cookie -> cookie.setMaxAge(0));

        return ResponseEntity.ok("SUCCESS LOGOUT");
    }
}
