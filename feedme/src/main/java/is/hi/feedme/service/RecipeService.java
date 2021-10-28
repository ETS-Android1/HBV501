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

        /**
         * Standard function to find one complete Recipe by its ID number
         * 
         * @param id the ID number of the recipe to find
         * @return the recipe that corresponds to that ID if any
         */
        Recipe findRecipeById(long id);

        /**
         * Standard function to save a recipe gotten from a request body. It is worth
         * noting that newly stored recipes have no ingredients associated with them.
         * 
         * @param recipe the RecipeDto made from the request body information
         * @return the Recipe entity created from saving it
         */
        Recipe createRecipe(RecipeDto recipe);

        /**
         * Standard function to delete a recipe based on its entity.
         * 
         * @param recipe the Recipe entity to delete
         */
        void deleteRecipe(Recipe recipe);

        /**
         * Basic find all service, returns a list of all the recipes ordered by their
         * IDs.
         */
        List<Recipe> findAllRecipes();

        /**
         * The main /recipes list service to be used when no pagination is requested.
         * Generates a "page" with paging metadata and a list of recipes with the given
         * filtering. ( For this variant the paging data isn't necessary as both prev
         * and next are always null, but we retain the format of the response for the
         * front-end. )
         * 
         * For the purpose of simplifying the format of our database queries it is
         * expected that sensible defaults are provided if a query string does not give
         * requirements for some of the parameters.
         * 
         * @param identifiers a list of Ingredient IDs to filter by
         * @param sort        the name of the "column" to order the recipes by
         * @param minCalories the minimum allowed calories of the recipe
         * @param maxCalories the maximum allowed calories of the recipe
         * @param minCarbs    the minimum allowed carbs of the recipe
         * @param maxCarbs    the maximum allowed carbs of the recipe
         * @param minProteins the minimum allowed proteins of the recipe
         * @param maxProteins the maximum allowed proteins of the recipe
         * @param minFats     the minimum allowed fats of the recipe
         * @param maxFats     the maximum allowed fats of the recipe
         * @return a CompositeRecipe page with information matching that of the request
         */
        CompositeRecipe findAllSimpleRecipes(List<Long> identifiers, String sort, int minCalories, int maxCalories,
                        int minCarbs, int maxCarbs, int minProteins, int maxProteins, int minFats, int maxFats);

        /**
         * The main /recipes list service to be used when pagination is requested.
         * Generates a "page" with paging metadata and a list of recipes with the given
         * filtering.
         * 
         * For the purpose of simplifying the format of our database queries it is
         * expected that sensible defaults are provided if a query string does not give
         * requirements for some of the parameters.
         * 
         * @param identifiers a list of Ingredient IDs to filter by
         * @param limit       the amount of recipes to show on the page
         * @param offset      how many recipes the selection should be offset by
         * @param sort        the name of the "column" to order the recipes by
         * @param minCalories the minimum allowed calories of the recipe
         * @param maxCalories the maximum allowed calories of the recipe
         * @param minCarbs    the minimum allowed carbs of the recipe
         * @param maxCarbs    the maximum allowed carbs of the recipe
         * @param minProteins the minimum allowed proteins of the recipe
         * @param maxProteins the maximum allowed proteins of the recipe
         * @param minFats     the minimum allowed fats of the recipe
         * @param maxFats     the maximum allowed fats of the recipe
         * @return a CompositeRecipe page with information matching that of the request
         */
        CompositeRecipe findAllSimpleRecipesPaginated(List<Long> identifiers, int limit, int offset, String sort,
                        int minCalories, int maxCalories, int minCarbs, int maxCarbs, int minProteins, int maxProteins,
                        int minFats, int maxFats);

        // Ingredient stuff

        /**
         * Standard function to obtain all the ingredients stored in the system.
         * 
         * @return a list of all ingredients in the system
         */
        List<Ingredient> findAllIngredients();

        /**
         * Standard function to obtain all the ingredients stored in the system. This is
         * generally used by the /ingredients route to provide a list of viable
         * ingredient filters.
         * 
         * @return a list of all ingredients, which are in use by Recipes, in the system
         */
        List<Ingredient> findAllUsedIngredients();

        /**
         * Standard function to save an ingredient gotten from a request body.
         * 
         * @param ingredient the IngredientDto made from the request body information
         * @return the Ingredient entity created from saving it
         */
        Ingredient createIngredient(IngredientDto ingredient);

        /**
         * Standard function to delete an ingredient based on its entity.
         * 
         * @param ingredient the Ingredient entity to delete
         */
        void deleteIngredient(Ingredient ingredient);

}
