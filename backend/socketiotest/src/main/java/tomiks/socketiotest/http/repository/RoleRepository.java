package tomiks.socketiotest.http.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomiks.socketiotest.http.model.auth.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(String name);
}
