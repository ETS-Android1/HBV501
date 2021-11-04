package is.hi.feedme.model;

/**
 * Helper Object for working with user authentication tokens
 */
public class LoginResponse {

    private SimplifiedUser user;
    private String token;
    long expiration;

    public LoginResponse() {
    }

    public LoginResponse(SimplifiedUser user, String token, long expiration) {
        this.user = user;
        this.token = token;
        this.expiration = expiration;
    }

    public SimplifiedUser getUser() {
        return user;
    }

    public void setUser(SimplifiedUser user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

}