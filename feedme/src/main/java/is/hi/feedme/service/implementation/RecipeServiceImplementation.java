package is.hi.feedme.service.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import is.hi.feedme.repository.RecipeRepository;
import is.hi.feedme.repository.ReviewRepository;
import is.hi.feedme.repository.IngredientRepository;
import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.RecipeDto;
import is.hi.feedme.model.SimplifiedRecipe;
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

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Recipe> findAllRecipes() {
        List<Recipe> list = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    private SimplifiedRecipe createSimpleRecipe(Recipe curr) {
        long currId = curr.getId();

        SimplifiedRecipe s = new SimplifiedRecipe();
        s.setId(currId);
        s.setName(curr.getName());

        try {
            s.setRating(reviewRepository.averageRatingByRecipeId(currId));
        } catch (Exception e) {
            // No rating exists, apply 0.0
            s.setRating(0.0);
        }

        s.setDescription(curr.getDescription());
        s.setCalories(curr.getCalories());
        s.setProteins(curr.getProteins());
        s.setCarbs(curr.getCarbs());
        s.setFats(curr.getFats());
        s.setImage(curr.getImage());

        return s;
    }

    public List<SimplifiedRecipe> findAllSimpleRecipes(List<Long> identifiers) {
        List<SimplifiedRecipe> list = new ArrayList<>();
        Iterator<Recipe> recipeIterator;

        if (identifiers.size() > 0) {
            recipeIterator = recipeRepository.findByIngredientIds(identifiers, identifiers.size()).iterator();
        } else {
            recipeIterator = recipeRepository.findAll().iterator();
        }

        while(recipeIterator.hasNext()) {
            list.add(createSimpleRecipe(recipeIterator.next()));
        }

        return list;
    }

    @Override
    public Recipe findOneRecipe(long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public Recipe save(RecipeDto recipe) {

        Recipe nRecipe = recipe.getRecipeFromDto();
        return recipeRepository.save(nRecipe);
    }

    @Override
    public void delete(Recipe recipe) {

        recipeRepository.delete(recipe);
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