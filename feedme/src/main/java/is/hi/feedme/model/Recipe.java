package is.hi.feedme.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @Column
    private String instructions;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "recipes_ingredients", joinColumns = {
            @JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false, updatable = false) })
    private Set<Ingredient> ingredients;

    @JsonIgnore
    @ManyToMany(mappedBy = "recipes", fetch = FetchType.LAZY)
    private Set<User> users;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Review> reviews;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
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
