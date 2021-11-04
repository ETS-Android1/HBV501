package is.hi.feedme.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <pre>
 * Comment entity, tied to the comments table in the database
 * Additional relations:
 * * Many to one relationship with recipe entities
 * * Many to one relationship with user entities
 * (Both of these relationships are omitted from responses)
 * </pre>
 */
@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id // No composite key, we allow multiple comments per user
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String body;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.user.getUsername();
    }

    public void setUsername() {
        // Do not actually allow explicit setting
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
