package is.hi.feedme.service.implementation;

import java.util.ArrayList;
import java.util.List;
import is.hi.feedme.repository.RecipeRepository;
import is.hi.feedme.repository.IngredientRepository;
import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;
import is.hi.feedme.model.Ingredient;
import is.hi.feedme.model.IngredientDto;
import is.hi.feedme.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "recipeService")
public class RecipeServiceImplementation implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Recipe> findAllRecipes() {
        List<Recipe> list = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Recipe save(RecipeDto recipe) {

        Recipe nRecipe = recipe.getRecipeFromDto();
        return recipeRepository.save(nRecipe);
    }

    public List<Ingredient> findAllIngredients() {
        List<Ingredient> list = new ArrayList<>();
        ingredientRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Ingredient save(IngredientDto ingredient) {

        Ingredient nIngredient = ingredient.getIngredientFromDto();
        return ingredientRepository.save(nIngredient);
    }

}