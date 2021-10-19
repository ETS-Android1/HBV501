package is.hi.feedme.model;

import java.util.HashSet;
import java.util.Set;

public class RecipeDto {

    private String name;
    private String description;
    private String instructions;
    private double calories;
    private double carbs;
    private double proteins;
    private double fats;
    private String image;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();

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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Recipe getRecipeFromDto() {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setInstructions(instructions);
        recipe.setCalories(calories);
        recipe.setProteins(proteins);
        recipe.setCarbs(carbs);
        recipe.setFats(fats);
        recipe.setImage(image);
        // Ingredients and comments are initialized as empty

        return recipe;
    }
}