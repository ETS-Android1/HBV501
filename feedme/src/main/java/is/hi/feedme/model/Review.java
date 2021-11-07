package is.hi.feedme.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <pre>
 * Review entity, tied to the reviews table in the database
 * Additional relations:
 * * Many to one relationship with the users table
 * * Many to one relationship with the recipes table
 * ( All additional relationships are currently omitted when creating responses )
 * </pre>
 */
@Entity
@Table(name = "reviews")
public class Review implements Serializable {

    @EmbeddedId
    ReviewKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    // Recipe is omitted from any responses using this to avoid a looping response
    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    Recipe recipe;

    @Column
    private String title;

    @Column
    private String subtitle;

    @Column
    private Date date;

    @Column
    private int rating;

    @Transient
    @JsonInclude
    private String username;

    @Transient
    @JsonInclude
    @JsonProperty("user_id")
    private long userId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public long getUserId() {
        return this.user.getId();
    }

    public void setUserId() {
        // Do not actually allow explicit setting
    }

    public String getUsername() {
        return this.user.getUsername();
    }

    public void setUsername() {
        // Do not actually allow explicit setting
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
