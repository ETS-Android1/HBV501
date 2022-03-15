package hi.feedme.feedme.models;

public class User {
    private int id;
    private String username;
    private String email;
    private Boolean admin;

    public User(int id, String username, String email, Boolean admin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.admin = admin;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
