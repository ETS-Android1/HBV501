package is.hi.feedme.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <pre>
 * IngredientQuantity entity, tied to the ingredient_quantity table in the database
 * Additional relations:
 * * Many to one relationship with the ingredients table
 * * Many to one relationship with the recipes table
 * ( This connecting entity is used to define the quantity and unit measurement of ingredients for a given recipe )
 * </pre>
 */
@Entity
@Table(name = "ingredient_quantity")
public class IngredientQuantity {

    @EmbeddedId
    IngredientQuantityKey id;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    // Recipe is omitted from any responses using this to avoid a looping response
    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    Recipe recipe;

    // The amount of the ingredient
    int quantity;
    // The describing unit ( cup, kg, liter, eggs, ... )
    String unit;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
}
