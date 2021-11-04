package is.hi.feedme.controller;

import is.hi.feedme.model.Ingredient;
import is.hi.feedme.model.IngredientDto;
import is.hi.feedme.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * HTTP routes for requests to /ingredients
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private RecipeService recipeService;

    /**
     * GET on /ingredients, no authentication required
     * 
     * @return a list of all used ingredient objects
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Ingredient>> getAllUsedIngredients() {
        List<Ingredient> tmp = recipeService.findAllUsedIngredients();

        if (tmp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(tmp);
    }

    /**
     * GET on /ingredients/all, no authentication requried
     * 
     * @return a list of all ingredient objects
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> tmp = recipeService.findAllIngredients();

        if (tmp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(tmp);
    }

    /**
     * POST on /ingredients, admin authentication required
     * 
     * @param ingredient The data transfer object ingredient from the request body
     * @return the information about the insertion request
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createIngredient(@RequestBody IngredientDto ingredient) {
        Ingredient i = null;
        try {
            i = recipeService.findIngredientByName(ingredient.getName());
        } catch (Exception e) {
            // Unused
        }

        // Block database request, already exists - respond with 409
        if (i != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ingredient with that name already exists");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createIngredient(ingredient));
    }

    /**
     * DELETE on /ingredients, admin authentication required
     * 
     * @param id the id number of the ingredient to delete
     * @return the information about the deletion request
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteIngredient(@PathVariable long id) {
        Ingredient i = null;
        try {
            i = recipeService.findIngredientById(id);
        } catch (Exception e) {
            // Unused
        }

        // Block database request, already exists - respond with 409
        if (i == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no ingredient with that id exists");
        }

        recipeService.deleteIngredient(i);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
