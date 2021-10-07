package is.hi.feedme.controller;

import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;
import is.hi.feedme.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/recipes")
public class RecipeController {

   @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Recipe> getAllRecipes() {
        return recipeService.findAllRecipes();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Recipe saveRecipe(@RequestBody RecipeDto recipe) {
        return recipeService.save(recipe);
    }

}
