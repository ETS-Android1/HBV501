package is.hi.feedme.model;

/**
 * <pre>
 * Simplified object used for sending responses where full user information is not required
 * Example routes used:
 * * /users/me
 * </pre>
 */
public class SimplifiedUser {

    private Long id;
    private String username;
    private String email;
    private boolean admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
