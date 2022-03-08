package hi.feedme.feedme.models;

public class Review {
    private int user_id;
    private String title;
    private String subtitle;
    private String date;
    private String username;
    private double rating;

    public Review() {

    }

    public Review(String title, String subtitle, String date, double rating, String username, int userId) {
        this.user_id = userId;
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
        this.username = username;
        this.rating = rating;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
