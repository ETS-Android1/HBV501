package is.hi.feedme.controller;

import is.hi.feedme.model.Ingredient;
import is.hi.feedme.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Ingredient> getAllRecipes() {
        return recipeService.findAllUsedIngredients();
    }

}
