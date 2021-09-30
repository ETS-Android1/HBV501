package is.hi.feedme.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import is.hi.feedme.models.Recipe;
import is.hi.feedme.repositories.RecipeRepository;
import is.hi.feedme.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable(value = "id") Long recipeId)
            throws ResourceNotFoundException {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found for this id :: " + recipeId));
        return ResponseEntity.ok().body(recipe);
    }

    @PostMapping("/recipes")
    public Recipe createRecipe(@Valid @RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @PutMapping("/recipes/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable(value = "id") Long recipeId,
            @Valid @RequestBody Recipe recipeDetails) throws ResourceNotFoundException {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found for this id :: " + recipeId));

        recipe.setName(recipeDetails.getName());
        recipe.setInstructions(recipeDetails.getInstructions());
        recipe.setImage(recipeDetails.getImage());
        final Recipe updatedRecipe = recipeRepository.save(recipe);
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("/recipes/{id}")
    public Map<String, Boolean> deleteRecipe(@PathVariable(value = "id") Long recipeId)
            throws ResourceNotFoundException {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found for this id :: " + recipeId));

        recipeRepository.delete(recipe);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
