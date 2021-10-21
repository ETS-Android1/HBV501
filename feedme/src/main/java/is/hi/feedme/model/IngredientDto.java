package is.hi.feedme.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Helper Object for working with request bodies for ingredient insertion 
 */
public class IngredientDto {

    private Long id;
    private String name;

    private Set<Recipe> recipes = new HashSet<>();

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
