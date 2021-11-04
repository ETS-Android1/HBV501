package is.hi.feedme.service;

import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;
import is.hi.feedme.model.Review;
import is.hi.feedme.model.ReviewDto;
import is.hi.feedme.model.CompositeRecipe;
import is.hi.feedme.model.Ingredient;
import is.hi.feedme.model.IngredientDto;
import is.hi.feedme.model.IngredientQuantityDto;

import java.util.List;
import java.util.Map;

/**
 * Interface definitions for the RecipeService.
 * 
 * Details for the individual implementation can be seen in the /implementation
 * directory.
 */
public interface RecipeService {

    // Recipe stuff

    /**
     * Standard function to find one complete Recipe by its name
     * 
     * @param name the name of the recipe to find
     * @return the recipe that corresponds to that name if any
     */
    Recipe findRecipeByName(String name);

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
     * Standard function to update a recipe in the database
     * 
     * @param recipe  the current recipe information to update
     * @param changes the list of changes to apply
     * @return the Recipe entity created from saving it
     */
    public Recipe updateRecipe(Recipe recipe, Map<String, Object> changes);

    /**
     * Standard function to delete a recipe based on its entity.
     * 
     * @param recipe the Recipe entity to delete
     */
    void deleteRecipe(Recipe recipe);

    /**
     * Adds a new ingredient to a recipe.
     * 
     * @param recipeId the id of the recipe to add to
     * @param iDto     the ingredient to add
     */
    public void addNewIngredient(long recipeId, IngredientQuantityDto iDto);

    /**
     * Removes an ingredient from a recipe.
     * 
     * @param recipeId     the id of the recipe to remove from
     * @param ingredientId the id of the ingredient to remove
     */
    public void deleteRecipeIngredient(long recipeId, long ingredientId);

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
            int minCalories, int maxCalories, int minCarbs, int maxCarbs, int minProteins, int maxProteins, int minFats,
            int maxFats);

    // Ingredient stuff

    /**
     * Standard function to find a recipe based on its name
     *
     * @param name
     * @return The ingredient that has this name, if any
     */
    public Ingredient findIngredientByName(String name);

    /**
     * Standard function to find a recipe based on its id
     *
     * @param name
     * @return The ingredient that has this name, if any
     */
    public Ingredient findIngredientById(long id);

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

    /**
     * Standard function to find a review with the primary key pair of recipeId and
     * userId
     * 
     * @param recipeId the recipe to find the review for
     * @param userId   the user to find the review for
     * @return the Review entity if one is found
     */
    public Review findReview(long recipeId, long userId);

    /**
     * Standard function to save a review gotten from a request body.
     * 
     * @param review the ReviewDto made from the request body information
     */
    public int createReview(long recipeId, long userId, ReviewDto review);

    /**
     * Standard function to delete a review based on its entity
     * 
     * @param review the Review entity to delete
     */
    public void deleteReview(Review review);

}
