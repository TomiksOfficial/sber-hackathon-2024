package tomiks.socketiotest.http.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tomiks.socketiotest.http.model.User;
import tomiks.socketiotest.http.model.auth.Role;
import tomiks.socketiotest.http.model.auth.UserRequestRegister;
import tomiks.socketiotest.http.repository.RoleRepository;
import tomiks.socketiotest.http.repository.UserRepository;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    /*

    Мне не нравится внедрение зависимостей полем, но столкнулся с проблемой в файле SecurityConfig.java

     */

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Регистрация пользователя через UserRequestRegister
     *
     * @param user Данные пользователя для регистрации
     * @return Успешность регистрации пользователя true/false
     */
    public boolean registerUser(UserRequestRegister user) {

        if (userRepository.findByEmailOrUsername(user.getEmail(), user.getUsername()).isPresent()) {
            return false;
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        newUser.setRoles(Collections.singleton(roleRepository.findRoleByName("ROLE_USER").orElse(new Role("ROLE_USER"))));

        userRepository.save(newUser);

        return true;
    }

    /**
     * Получение данных пользователя по логину или эмайлу
     * @param login эмайл или логин пользователя
     * @return возвращает пользователя
     */
    public User getUserByEmailOrUsername(String login) {
        return userRepository.findByEmailOrUsername(login, login).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByEmailOrUsername(login, login).orElseThrow(() -> new UsernameNotFoundException(login));
    }

}
