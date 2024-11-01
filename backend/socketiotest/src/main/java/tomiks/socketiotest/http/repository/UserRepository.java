package tomiks.socketiotest.http.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomiks.socketiotest.http.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailOrUsername(String email, String username);
}
