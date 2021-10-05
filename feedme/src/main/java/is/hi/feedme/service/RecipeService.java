package is.hi.feedme.service;

import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;

import java.util.List;

public interface RecipeService {
    Recipe save(RecipeDto recipe);
    List<Recipe> findAll();
}
