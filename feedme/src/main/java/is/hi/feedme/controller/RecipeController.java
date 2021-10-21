package is.hi.feedme.controller;

import is.hi.feedme.model.CompositeRecipe;
import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;
import is.hi.feedme.model.SimplifiedRecipe;
import is.hi.feedme.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/recipes")
public class RecipeController {

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public CompositeRecipe getAllRecipes(
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
        List<Long> l;

        if (ingredients != null) {
            l = generateIngredientIDList(ingredients);
        } else {
            l = new ArrayList<Long>();
        }

        if (limit != null) {
            return recipeService.findAllSimpleRecipesPaginated(l, limit, offset == null ? 0 : offset,
                    sort != null ? sort : "id", minCalories == null ? 0 : minCalories,
                    maxCalories == null ? Integer.MAX_VALUE : maxCalories, minCarbs == null ? 0 : minCarbs,
                    maxCarbs == null ? Integer.MAX_VALUE : maxCarbs, minProteins == null ? 0 : minProteins,
                    maxProteins == null ? Integer.MAX_VALUE : maxProteins, minFats == null ? 0 : minFats,
                    maxFats == null ? Integer.MAX_VALUE : maxFats);
        }

        return recipeService.findAllSimpleRecipes(l, sort != null ? sort : "id", minCalories == null ? 0 : minCalories,
                maxCalories == null ? Integer.MAX_VALUE : maxCalories, minCarbs == null ? 0 : minCarbs,
                maxCarbs == null ? Integer.MAX_VALUE : maxCarbs, minProteins == null ? 0 : minProteins,
                maxProteins == null ? Integer.MAX_VALUE : maxProteins, minFats == null ? 0 : minFats,
                maxFats == null ? Integer.MAX_VALUE : maxFats);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Recipe saveRecipe(@RequestBody RecipeDto recipe) {
        return recipeService.save(recipe);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Recipe getRecipe(@PathVariable long id) {
        return recipeService.findOneRecipe(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteRecipe(@PathVariable long id) {
        Recipe recipe = recipeService.findOneRecipe(id);
        recipeService.delete(recipe);

        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted", Boolean.TRUE);

        return res;
    }

}
