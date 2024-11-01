package tomiks.socketiotest.http.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import tomiks.socketiotest.http.model.User;

import java.util.Set;

@Data
@Entity
@Table(name = "customers_roles")
@EqualsAndHashCode(exclude = {"users"})
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name", unique = true)
    private String name;

//  На возможный будущий функционал админки
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("roles")
    @JsonIgnore
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
