package is.hi.feedme.controller;

import is.hi.feedme.model.CompositeRecipe;
import is.hi.feedme.model.Ingredient;
import is.hi.feedme.model.IngredientQuantityDto;
import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;
import is.hi.feedme.model.Review;
import is.hi.feedme.model.ReviewDto;
import is.hi.feedme.model.User;
import is.hi.feedme.service.RecipeService;
import is.hi.feedme.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP routes for requests to /recipes
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private UserService userService;

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
    public ResponseEntity<CompositeRecipe> findAllRecipes(
            @RequestParam(required = false, name = "ingredients") String ingredients,
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

        CompositeRecipe c = null;

        try {
            if (limit != null) {
                c = recipeService.findAllSimpleRecipesPaginated(l, limit, offset == null ? 0 : offset,
                        sort != null ? sort : "name", minCalories == null ? 0 : minCalories,
                        maxCalories == null ? Integer.MAX_VALUE : maxCalories, minCarbs == null ? 0 : minCarbs,
                        maxCarbs == null ? Integer.MAX_VALUE : maxCarbs, minProteins == null ? 0 : minProteins,
                        maxProteins == null ? Integer.MAX_VALUE : maxProteins, minFats == null ? 0 : minFats,
                        maxFats == null ? Integer.MAX_VALUE : maxFats);
            } else {
                c = recipeService.findAllSimpleRecipes(l, sort != null ? sort : "name",
                        minCalories == null ? 0 : minCalories, maxCalories == null ? Integer.MAX_VALUE : maxCalories,
                        minCarbs == null ? 0 : minCarbs, maxCarbs == null ? Integer.MAX_VALUE : maxCarbs,
                        minProteins == null ? 0 : minProteins, maxProteins == null ? Integer.MAX_VALUE : maxProteins,
                        minFats == null ? 0 : minFats, maxFats == null ? Integer.MAX_VALUE : maxFats);
            }
        } catch (Exception e) {
            // Unused
        }

        if (c == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(c);
    }

    /**
     * GET on /recipes/{id}, no authentication required.
     * 
     * @param id the id of the recipe to show
     * @return the information of the requested Recipe entity ( some fields not
     *         shown )
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findRecipeInfoById(@PathVariable long id) {
        User user = null;
        
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            user = userService.findUserByUsername(auth.getName());
        } catch (Exception e) {
            // Unused
        }

        Recipe r = null;
        try {
            r = recipeService.findRecipeById(id);
        } catch (Exception e) {
            // Unused
        }

        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no recipe with that id exists");
        }

        if (user != null && user.getRecipes().contains(r)) {
            r.setUserStored(true);
        }

        return ResponseEntity.status(HttpStatus.OK).body(r);
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
    public ResponseEntity<?> createRecipe(@RequestBody RecipeDto recipe) {
        Recipe r = null;

        try {
            r = recipeService.findRecipeByName(recipe.getName());
        } catch (Exception e) {
            // Unused
        }

        if (r != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("recipe with that name already exists");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createRecipe(recipe));
    }

    /**
     * PATCH on /recipes/{id}, admin authentication required. Used to change a
     * recipe.
     * 
     * @param id the id of the recipe to remove
     * @return a response indicating whether update was successful
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateRecipeById(@PathVariable long id, @RequestBody Map<String, Object> changes) {
        Recipe recipe = null;

        try {
            recipe = recipeService.findRecipeById(id);
        } catch (Exception e) {
            // Unused
        }

        if (recipe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no recipe with that id exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(recipeService.updateRecipe(recipe, changes));
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
    public ResponseEntity<?> deleteRecipeById(@PathVariable long id) {
        Recipe recipe = null;

        try {
            recipe = recipeService.findRecipeById(id);
        } catch (Exception e) {
            // Unused
        }

        if (recipe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no recipe with that id exists");
        }

        recipeService.deleteRecipe(recipe);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * POST on /recipes/{id}/ingredients, admin authentication required. Used to add
     * an ingredient to a recipe
     * 
     * @param recipe the recipe info from the request body
     * @return the saved recipe entity
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "{id}/ingredients", method = RequestMethod.POST)
    public ResponseEntity<?> addRecipeIngredient(@PathVariable long id, @RequestBody IngredientQuantityDto iDto) {
        Recipe r = null;
        Ingredient i = null;

        try {
            r = recipeService.findRecipeById(id);
        } catch (Exception e) {
            // Unused
        }

        try {
            i = recipeService.findIngredientById(iDto.getIngredient());
        } catch (Exception e) {
            // Unused
        }

        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no recipe with that id exists");
        }

        if (i == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no ingredient with that id exists");
        }

        recipeService.addNewIngredient(id, iDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * DELETE on /recipes/{recipeId}/ingredients/{ingredientId}, admin
     * authentication required. Used to remove an ingredient from a recipe.
     * 
     * @param id the id of the recipe to remove
     * @return a response indicating whether deletion was successful
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "{id}/ingredients/{ingredientId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRecipeIngredientById(@PathVariable long id, @PathVariable long ingredientId) {
        Recipe recipe = null;
        Ingredient i = null;

        try {
            recipe = recipeService.findRecipeById(id);
        } catch (Exception e) {
            // Unused
        }

        try {
            i = recipeService.findIngredientById(ingredientId);
        } catch (Exception e) {
            // Unused
        }

        if (recipe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no recipe with that id exists");
        }

        if (i == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no ingredient with that id exists");
        }

        recipeService.deleteRecipeIngredient(id, ingredientId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * POST on /recipes/{id}/reviews, user authentication is required
     * 
     * @param id the id of the recipe to add a review to
     * @param rDto the request body of the review to add
     * @return the status of the creation request
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "{id}/reviews", method = RequestMethod.POST)
    public ResponseEntity<?> addUserReview(@PathVariable long id, @RequestBody ReviewDto rDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Review r = null;

        try {
            r = recipeService.findReview(id, user.getId());
        } catch (Exception e) {
            // Unused
        }

        if (r != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("a review with those key values already exists");
        }

        int res = 0;

        try {
            res = recipeService.createReview(id, user.getId(), rDto);
        } catch (Exception e) {
            // Unused
        }

        if (res == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
    }

    /**
     * DELETE on /recipes/{id}/reviews/{userId}, user authentication is required
     * 
     * @param id the id of the recipe to add a review to
     * @param userId the id of the user
     * @return the status of the deletion request
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "{id}/reviews/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserReview(@PathVariable long id, @PathVariable long userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        if (!(user.getAdmin() || user.getId() == userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Review r = null;

        try {
            r = recipeService.findReview(id, userId);
        } catch (Exception e) {
            // Unused
        }

        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no review with those key values exists");
        }

        recipeService.deleteReview(r);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
