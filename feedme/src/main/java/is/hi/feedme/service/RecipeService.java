package is.hi.feedme.service;

import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;
import is.hi.feedme.model.CompositeRecipe;
import is.hi.feedme.model.Ingredient;
import is.hi.feedme.model.IngredientDto;

import java.util.List;

/**
 * Interface definitions for the RecipeService.
 * 
 * Details for the individual implementation can be seen in the /implementation
 * directory.
 */
public interface RecipeService {

        // Recipe stuff
        Recipe findRecipeById(long id);

        Recipe createRecipe(RecipeDto recipe);

        void deleteRecipe(Recipe recipe);

        List<Recipe> findAllRecipes();

        CompositeRecipe findAllSimpleRecipes(List<Long> identifiers, String sort, int minCalories, int maxCalories,
                        int minCarbs, int maxCarbs, int minProteins, int maxProteins, int minFats, int maxFats);

        CompositeRecipe findAllSimpleRecipesPaginated(List<Long> identifiers, int limit, int offset, String sort,
                        int minCalories, int maxCalories, int minCarbs, int maxCarbs, int minProteins, int maxProteins,
                        int minFats, int maxFats);

        // Ingredient stuff
        List<Ingredient> findAllIngredients();

        Ingredient createIngredient(IngredientDto ingredient);

        void deleteIngredient(Ingredient ingredient);

}
