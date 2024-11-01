package tomiks.socketiotest.http.model.auth;

import lombok.Getter;

@Getter
public final class UserRequestLogin {
    private String login;
    private String password;
}
