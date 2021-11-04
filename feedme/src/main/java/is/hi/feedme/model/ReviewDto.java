package is.hi.feedme.model;

/**
 * Helper Object for working with request bodies for review insertion.
 */
public class ReviewDto {

    private String title;
    private String subtitle;
    private int rating;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
