package is.hi.feedme.controller;

import is.hi.feedme.model.Ingredient;
import is.hi.feedme.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Ingredient> getAllRecipes() {
        return recipeService.findAllIngredients();
    }

}
