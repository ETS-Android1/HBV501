package is.hi.feedme.model;

import java.util.HashSet;
import java.util.Set;

public class IngredientDto {

    private long id;
    private String name;

    private Set<Recipe> recipes = new HashSet<>();

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

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Ingredient getIngredientFromDto() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);

        return ingredient;
    }
}
