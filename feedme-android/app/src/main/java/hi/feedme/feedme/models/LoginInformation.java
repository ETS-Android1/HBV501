package hi.feedme.feedme.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = LoginInformationDeserializer.class)
public class LoginInformation {
    private User user;
    private String token;
    private int expiration;

    public LoginInformation(User user, String token, int expiration) {
        this.user = user;
        this.token = token;
        this.expiration = expiration;
    }

    public LoginInformation() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }
}
