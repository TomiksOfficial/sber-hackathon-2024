package tomiks.socketiotest.http.controllers.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tomiks.socketiotest.http.model.User;
import tomiks.socketiotest.http.model.auth.Role;
import tomiks.socketiotest.http.repository.RoleRepository;
import tomiks.socketiotest.http.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @PostMapping("/create_role")
    public void createRole(@RequestBody String roleName) {
        roleRepository.save(new Role(roleName));
    }

    @PostMapping("/add_role")
    public ResponseEntity<String> addRole(@RequestBody Map<String, String> data) {
        if (!data.containsKey("roleName") || !data.containsKey("emailTarget")) {
            return ResponseEntity.badRequest().body("BAD CREDENTIALS");
        }

        Optional<User> target = userRepository.findByEmail(data.get("emailTarget"));

        if (target.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        target.get().getRoles().add(roleRepository.findRoleByName(data.get("roleName")).orElse(new Role(data.get("roleName"))));

        return ResponseEntity.status(200).body("Role added");
    }

    @PostMapping("/remove_role")
    public ResponseEntity<String> removeRole(@RequestBody Map<String, String> data) {
        if (!data.containsKey("roleName") || !data.containsKey("emailTarget")) {
            return ResponseEntity.badRequest().body("BAD CREDENTIALS");
        }

        Optional<User> target = userRepository.findByEmail(data.get("emailTarget"));

        if (target.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        target.get().getRoles().remove(roleRepository.findRoleByName(data.get("roleName")).orElse(null));

        return ResponseEntity.status(200).body("Role removes");
    }

}
