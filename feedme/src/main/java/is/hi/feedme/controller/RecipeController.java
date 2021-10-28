package is.hi.feedme.controller;

import is.hi.feedme.model.CompositeRecipe;
import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;
import is.hi.feedme.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HTTP routes for requests to /recipes
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/recipes")
public class RecipeController {

    // TODO: Routing for PATCH (updateRecipeById)

    /**
     * Helper function to parse a comma separated String of ingredient IDs
     * 
     * @param ingredientQuery The String of ingredients to try parse
     * @return a List<Long> of successfully parsed ID numbers
     */
    private List<Long> generateIngredientIDList(String ingredientQuery) {
        List<Long> l = new ArrayList<Long>();
        String[] tokens = ingredientQuery.split(",");

        for (String token : tokens) {
            try {
                Long id = Long.parseLong(token);
                l.add(id);
            } catch (Exception e) {
                // Unused, skip adding
            }
        }

        return l;
    }

    @Autowired
    private RecipeService recipeService;

    /**
     * GET on /recipes, no authentication required - optional querystring parameters
     * can be provided
     * 
     * @param ingredients String representing a list of comma separated ingredient
     *                    IDs to filter for
     * @param limit       the pagination limit
     * @param offset      the pagination offset
     * @param sort        String representing the 'column' to use for sorting (
     *                    sensible defaults are used )
     * @param minCalories the minimum calories of recipes to show
     * @param maxCalories the maximum calories of recipes to show
     * @param minCarbs    the minimum carbs of recipes to show
     * @param maxCarbs    the maximum carbs of recipes to show
     * @param minProteins the minimum proteins of recipes to show
     * @param maxProteins the maximum proteins of recipes to show
     * @param minFats     the minimum fats of recipes to show
     * @param maxFats     the maximum fats of recipes to show
     * @return a CompositeRecipe representing a list of simplified recipe objects
     *         with pagination information
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public CompositeRecipe findAllRecipes(@RequestParam(required = false, name = "ingredients") String ingredients,
            @RequestParam(required = false, name = "limit") Integer limit,
            @RequestParam(required = false, name = "offset") Integer offset,
            @RequestParam(required = false, name = "sort") String sort,
            @RequestParam(required = false, name = "mincalories") Integer minCalories,
            @RequestParam(required = false, name = "maxcalories") Integer maxCalories,
            @RequestParam(required = false, name = "mincarbs") Integer minCarbs,
            @RequestParam(required = false, name = "maxcarbs") Integer maxCarbs,
            @RequestParam(required = false, name = "minproteins") Integer minProteins,
            @RequestParam(required = false, name = "maxproteins") Integer maxProteins,
            @RequestParam(required = false, name = "minfats") Integer minFats,
            @RequestParam(required = false, name = "maxfats") Integer maxFats) {

        // Since all parameter fields are optional we provide non-intrusive default
        // values
        // For the purpose of simplifying the query structure
        List<Long> l;

        if (ingredients != null) {
            l = generateIngredientIDList(ingredients);
        } else {
            l = new ArrayList<Long>();
        }

        if (limit != null) {
            return recipeService.findAllSimpleRecipesPaginated(l, limit, offset == null ? 0 : offset,
                    sort != null ? sort : "name", minCalories == null ? 0 : minCalories,
                    maxCalories == null ? Integer.MAX_VALUE : maxCalories, minCarbs == null ? 0 : minCarbs,
                    maxCarbs == null ? Integer.MAX_VALUE : maxCarbs, minProteins == null ? 0 : minProteins,
                    maxProteins == null ? Integer.MAX_VALUE : maxProteins, minFats == null ? 0 : minFats,
                    maxFats == null ? Integer.MAX_VALUE : maxFats);
        }

        return recipeService.findAllSimpleRecipes(l, sort != null ? sort : "name", minCalories == null ? 0 : minCalories,
                maxCalories == null ? Integer.MAX_VALUE : maxCalories, minCarbs == null ? 0 : minCarbs,
                maxCarbs == null ? Integer.MAX_VALUE : maxCarbs, minProteins == null ? 0 : minProteins,
                maxProteins == null ? Integer.MAX_VALUE : maxProteins, minFats == null ? 0 : minFats,
                maxFats == null ? Integer.MAX_VALUE : maxFats);
    }

    /**
     * GET on /recipes/{id}, no authentication required.
     * 
     * @param id the id of the recipe to show
     * @return the information of the requested Recipe entity ( some fields not
     *         shown )
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Recipe findRecipeInfoById(@PathVariable long id) {
        return recipeService.findRecipeById(id);
    }

    /**
     * POST on /recipes, admin authentication required. Used to add a recipe to the
     * database.
     * 
     * @param recipe the recipe info from the request body
     * @return the saved recipe entity
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Recipe createRecipe(@RequestBody RecipeDto recipe) {
        return recipeService.createRecipe(recipe);
    }

    /**
     * DELETE on /recipes/{id}, admin authentication required. Used to remove a
     * recipe from the database.
     * 
     * @param id the id of the recipe to remove
     * @return a response indicating whether deletion was successful
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteRecipeById(@PathVariable long id) {
        Recipe recipe = recipeService.findRecipeById(id);
        recipeService.deleteRecipe(recipe);

        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted", Boolean.TRUE);

        return res;
    }

}
