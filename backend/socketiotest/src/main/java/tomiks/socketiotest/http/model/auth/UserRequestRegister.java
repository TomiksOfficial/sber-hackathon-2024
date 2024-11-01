package tomiks.socketiotest.http.model.auth;

import lombok.Getter;

@Getter
public final class UserRequestRegister {
    private String username;
    private String email;
    private String password;
}
