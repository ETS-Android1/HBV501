package is.hi.feedme.model;

/**
 * Helper Object for working with user authentication tokens
 */
public class AuthToken {

    private String token;

    public AuthToken() {

    }

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}