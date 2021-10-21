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
import is.hi.feedme.model.CompositeRecipe;
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

    public CompositeRecipe findAllSimpleRecipes(List<Long> identifiers, String sort, int minCalories, int maxCalories,
            int minCarbs, int maxCarbs, int minProteins, int maxProteins, int minFats, int maxFats) {
        CompositeRecipe cr = new CompositeRecipe();
        List<SimplifiedRecipe> list = new ArrayList<>();
        Iterator<Recipe> recipeIterator = null;

        String url = "/recipes?mincalories=" + minCalories + "&maxcalories=" + maxCalories + "&mincarbs=" + minCarbs
                + "&maxcarbs=" + maxCarbs + "&minproteins=" + minProteins + "&maxproteins=" + maxProteins + "&minfats="
                + minFats + "&maxfats=" + maxFats;

        if (identifiers.size() > 0) {
            url += "&ingredients=";

            for (int i = 0; i < identifiers.size() - 1; i++) {
                url += (identifiers.get(i) + ",");
            }

            url += identifiers.get(identifiers.size() - 1);
        }

        url += "&sort=";
        switch (sort) {
        case "calories":
            url += "calories";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedByCalories(identifiers, identifiers.size(),
                        minCalories, maxCalories, minCarbs, maxCarbs, minProteins, maxProteins, minFats, maxFats)
                        .iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedByCalories(minCalories, maxCalories, minCarbs, maxCarbs,
                        minProteins, maxProteins, minFats, maxFats).iterator();
            }
            break;
        case "carbs":
            url += "carbs";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedByCarbs(identifiers, identifiers.size(),
                        minCalories, maxCalories, minCarbs, maxCarbs, minProteins, maxProteins, minFats, maxFats)
                        .iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedByCarbs(minCalories, maxCalories, minCarbs, maxCarbs,
                        minProteins, maxProteins, minFats, maxFats).iterator();
            }
            break;
        case "proteins":
            url += "proteins";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedByProteins(identifiers, identifiers.size(),
                        minCalories, maxCalories, minCarbs, maxCarbs, minProteins, maxProteins, minFats, maxFats)
                        .iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedByProteins(minCalories, maxCalories, minCarbs, maxCarbs,
                        minProteins, maxProteins, minFats, maxFats).iterator();
            }
            break;
        case "fats":
            url += "fats";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedByFats(identifiers, identifiers.size(),
                        minCalories, maxCalories, minCarbs, maxCarbs, minProteins, maxProteins, minFats, maxFats)
                        .iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedByFats(minCalories, maxCalories, minCarbs, maxCarbs,
                        minProteins, maxProteins, minFats, maxFats).iterator();
            }
            break;
        default:
            url += "id";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedById(identifiers, identifiers.size(),
                        minCalories, maxCalories, minCarbs, maxCarbs, minProteins, maxProteins, minFats, maxFats)
                        .iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedById(minCalories, maxCalories, minCarbs, maxCarbs,
                        minProteins, maxProteins, minFats, maxFats).iterator();
            }
            break;
        }

        while (recipeIterator.hasNext()) {
            list.add(createSimpleRecipe(recipeIterator.next()));
        }

        cr.setSimplifiedRecipes(list);
        cr.setPrevious(null);
        cr.setCurrent(url);
        cr.setNext(null);
        cr.setPages(1);
        return cr;
    }

    public CompositeRecipe findAllSimpleRecipesPaginated(List<Long> identifiers, int limit, int offset, String sort,
            int minCalories, int maxCalories, int minCarbs, int maxCarbs, int minProteins, int maxProteins, int minFats,
            int maxFats) {
        CompositeRecipe cr = new CompositeRecipe();
        List<SimplifiedRecipe> list = new ArrayList<>();
        Iterator<Recipe> recipeIterator = null;

        String url = "/recipes?mincalories=" + minCalories + "&maxcalories=" + maxCalories + "&mincarbs=" + minCarbs
                + "&maxcarbs=" + maxCarbs + "&minproteins=" + minProteins + "&maxproteins=" + maxProteins + "&minfats="
                + minFats + "&maxfats=" + maxFats;

        int totalCount;
        if (identifiers.size() > 0) {
            url += "&ingredients=";

            for (int i = 0; i < identifiers.size() - 1; i++) {
                url += (identifiers.get(i) + ",");
            }

            url += identifiers.get(identifiers.size() - 1);

            totalCount = recipeRepository.findCountFiltered(identifiers, identifiers.size());
        } else {
            totalCount = recipeRepository.findCount();
        }

        url += "&sort=";
        switch (sort) {
        case "calories":
            url += "calories";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedByCaloriesPaginated(identifiers,
                        identifiers.size(), limit, offset, minCalories, maxCalories, minCarbs, maxCarbs, minProteins,
                        maxProteins, minFats, maxFats).iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedByCaloriesPaginated(limit, offset, minCalories,
                        maxCalories, minCarbs, maxCarbs, minProteins, maxProteins, minFats, maxFats).iterator();
            }

            break;
        case "carbs":
            url += "carbs";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedByCarbsPaginated(identifiers,
                        identifiers.size(), limit, offset, minCalories, maxCalories, minCarbs, maxCarbs, minProteins,
                        maxProteins, minFats, maxFats).iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedByCarbsPaginated(limit, offset, minCalories, maxCalories,
                        minCarbs, maxCarbs, minProteins, maxProteins, minFats, maxFats).iterator();
            }

            break;
        case "proteins":
            url += "proteins";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedByProteinsPaginated(identifiers,
                        identifiers.size(), limit, offset, minCalories, maxCalories, minCarbs, maxCarbs, minProteins,
                        maxProteins, minFats, maxFats).iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedByProteinsPaginated(limit, offset, minCalories,
                        maxCalories, minCarbs, maxCarbs, minProteins, maxProteins, minFats, maxFats).iterator();
            }

            break;
        case "fats":
            url += "fats";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedByFatsPaginated(identifiers,
                        identifiers.size(), limit, offset, minCalories, maxCalories, minCarbs, maxCarbs, minProteins,
                        maxProteins, minFats, maxFats).iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedByFatsPaginated(limit, offset, minCalories, maxCalories,
                        minCarbs, maxCarbs, minProteins, maxProteins, minFats, maxFats).iterator();
            }

            break;
        default:
            url += "id";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedByIdPaginated(identifiers,
                        identifiers.size(), limit, offset, minCalories, maxCalories, minCarbs, maxCarbs, minProteins,
                        maxProteins, minFats, maxFats).iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedByIdPaginated(limit, offset, minCalories, maxCalories,
                        minCarbs, maxCarbs, minProteins, maxProteins, minFats, maxFats).iterator();
            }
            break;
        }

        while (recipeIterator.hasNext()) {
            list.add(createSimpleRecipe(recipeIterator.next()));
        }

        url += "&limit=" + limit;
        cr.setSimplifiedRecipes(list);
        cr.setPrevious(
                offset == 0 ? null : offset - limit <= 0 ? url + "&offset=" + 0 : url + "&offset=" + (offset - limit));
        cr.setCurrent(url + "&offset=" + offset);
        cr.setNext(offset >= (totalCount - limit) ? null
                : (offset + limit) >= (totalCount - limit) ? url + "&offset=" + (totalCount - limit)
                        : url + "&offset=" + (offset + limit));
        cr.setPages(totalCount / limit + ((totalCount % limit == 0) ? 0 : 1));
        return cr;
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