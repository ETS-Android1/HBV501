package is.hi.feedme.service;

import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;
import is.hi.feedme.model.Ingredient;
import is.hi.feedme.model.IngredientDto;

import java.util.List;

public interface RecipeService {
    Recipe save(RecipeDto recipe);

    List<Recipe> findAllRecipes();

    Ingredient save(IngredientDto ingredient);

    List<Ingredient> findAllIngredients();
}
