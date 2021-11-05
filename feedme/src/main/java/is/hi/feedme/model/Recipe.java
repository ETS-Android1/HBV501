package is.hi.feedme.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Set;

/**
 * <pre>
 * Recipe entity, tied to the recipes table in the database
 * Additional relations:
 * * Many to many relationship with the users table
 * * One to many relationship with the ingredient_quantities connecting table
 * * One to many relationship with the comments table
 * * One to many relationship with the reviews table
 * ( All of these values except for ingredients are omitted when creating responses )
 * </pre>
 */
@Entity
@Table(name = "recipes")
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
        setterVisibility=JsonAutoDetect.Visibility.NONE, creatorVisibility=JsonAutoDetect.Visibility.NONE)
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(length = 1024)
    private String description;

    @Column(length = 1024)
    private String instructions;

    @Transient
    @JsonInclude
    @JsonProperty("user_stored")
    boolean userStored = false;

    @Column
    private double calories;

    @Column
    private double carbs;

    @Column
    private double proteins;

    @Column
    private double fats;

    @Column
    private String image;

    @OneToMany(mappedBy = "recipe")
    @JsonProperty("ingredients")
    private Set<IngredientQuantity> ingredientQuantities;

    @JsonIgnore
    @ManyToMany(mappedBy = "recipes", fetch = FetchType.LAZY)
    private Set<User> users;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Review> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public boolean getUserStored() {
        return userStored;
    }

    public void setUserStored(boolean userStored) {
        this.userStored = userStored;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<IngredientQuantity> getIngredientQuantities() {
        return ingredientQuantities;
    }

    public void setIngredientQuantities(Set<IngredientQuantity> ingredientQuantities) {
        this.ingredientQuantities = ingredientQuantities;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

}
