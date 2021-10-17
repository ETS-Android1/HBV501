package is.hi.feedme.service;

import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;
import is.hi.feedme.model.SimplifiedRecipe;
import is.hi.feedme.model.Ingredient;
import is.hi.feedme.model.IngredientDto;

import java.util.List;

public interface RecipeService {
    Recipe findOneRecipe(long id);

    Recipe save(RecipeDto recipe);

    void delete(Recipe recipe);

    List<Recipe> findAllRecipes();

    List<SimplifiedRecipe> findAllSimpleRecipes();

    Ingredient save(IngredientDto ingredient);

    List<Ingredient> findAllIngredients();
}
