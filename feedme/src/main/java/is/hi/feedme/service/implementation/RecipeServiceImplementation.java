package is.hi.feedme.service.implementation;

import java.util.ArrayList;
import java.util.List;
import is.hi.feedme.repository.RecipeRepository;
import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;
import is.hi.feedme.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "recipeService")
public class RecipeServiceImplementation implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> findAll() {
        List<Recipe> list = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Recipe save(RecipeDto recipe) {

        Recipe nRecipe = recipe.getRecipeFromDto();
        return recipeRepository.save(nRecipe);
    }
}