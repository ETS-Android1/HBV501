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

/**
 * The main service used by routes that handle recipes. It pulls information
 * from the RecipeRepository as well as assisting information from the
 * Ingredient and ReviewRepository. Comments are handled implicitly by Jpa by
 * adding them as JSON based on the list of Comments a recipe has associated
 * with it.
 */
@Service(value = "recipeService")
public class RecipeServiceImplementation implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Basic find all service, returns a list of all the recipes ordered by their
     * IDs.
     */
    public List<Recipe> findAllRecipes() {
        List<Recipe> list = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    /**
     * Helper function to convert Recipe entities to a simpler format. This is used
     * to pull unnecessary information out of Recipes that are shown on the
     * front-end as a list format.
     * 
     * @param curr the recipe to make a SimplifiedRecipe from
     * @return the SimplifiedRecipe version of the given Recipe object
     */
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

    /**
     * The main /recipes list service to be used when no pagination is requested.
     * Generates a "page" with paging metadata and a list of recipes with the given
     * filtering. ( For this variant the paging data isn't necessary as both prev
     * and next are always null, but we retain the format of the response for the
     * front-end. )
     * 
     * For the purpose of simplifying the format of our database queries it is
     * expected that sensible defaults are provided if a query string does not give
     * requirements for some of the parameters.
     * 
     * @param identifiers a list of Ingredient IDs to filter by
     * @param sort        the name of the "column" to order the recipes by
     * @param minCalories the minimum allowed calories of the recipe
     * @param maxCalories the maximum allowed calories of the recipe
     * @param minCarbs    the minimum allowed carbs of the recipe
     * @param maxCarbs    the maximum allowed carbs of the recipe
     * @param minProteins the minimum allowed proteins of the recipe
     * @param maxProteins the maximum allowed proteins of the recipe
     * @param minFats     the minimum allowed fats of the recipe
     * @param maxFats     the maximum allowed fats of the recipe
     * @return a CompositeRecipe page with information matching that of the request
     */
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
            url += "name";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedByName(identifiers, identifiers.size(),
                        minCalories, maxCalories, minCarbs, maxCarbs, minProteins, maxProteins, minFats, maxFats)
                        .iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedByName(minCalories, maxCalories, minCarbs, maxCarbs,
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

    /**
     * The main /recipes list service to be used when pagination is requested.
     * Generates a "page" with paging metadata and a list of recipes with the given
     * filtering.
     * 
     * For the purpose of simplifying the format of our database queries it is
     * expected that sensible defaults are provided if a query string does not give
     * requirements for some of the parameters.
     * 
     * @param identifiers a list of Ingredient IDs to filter by
     * @param limit       the amount of recipes to show on the page
     * @param offset      how many recipes the selection should be offset by
     * @param sort        the name of the "column" to order the recipes by
     * @param minCalories the minimum allowed calories of the recipe
     * @param maxCalories the maximum allowed calories of the recipe
     * @param minCarbs    the minimum allowed carbs of the recipe
     * @param maxCarbs    the maximum allowed carbs of the recipe
     * @param minProteins the minimum allowed proteins of the recipe
     * @param maxProteins the maximum allowed proteins of the recipe
     * @param minFats     the minimum allowed fats of the recipe
     * @param maxFats     the maximum allowed fats of the recipe
     * @return a CompositeRecipe page with information matching that of the request
     */
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
            url += "name";
            if (identifiers.size() > 0) {
                recipeIterator = recipeRepository.findByIngredientIdsSortedByNamePaginated(identifiers,
                        identifiers.size(), limit, offset, minCalories, maxCalories, minCarbs, maxCarbs, minProteins,
                        maxProteins, minFats, maxFats).iterator();
            } else {
                recipeIterator = recipeRepository.findAllSortedByNamePaginated(limit, offset, minCalories, maxCalories,
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

    /**
     * Standard function to find one complete Recipe by its ID number
     * 
     * @param id the ID number of the recipe to find
     * @return the recipe that corresponds to that ID if any
     */
    @Override
    public Recipe findRecipeById(long id) {
        return recipeRepository.findById(id);
    }

    /**
     * Standard function to save a recipe gotten from a request body. It is worth
     * noting that newly stored recipes have no ingredients associated with them.
     * 
     * @param recipe the RecipeDto made from the request body information
     * @return the Recipe entity created from saving it
     */
    @Override
    public Recipe createRecipe(RecipeDto recipe) {

        Recipe nRecipe = recipe.getRecipeFromDto();
        return recipeRepository.save(nRecipe);
    }

    /**
     * Standard function to delete a recipe based on its entity.
     * 
     * @param recipe the Recipe entity to delete
     */
    @Override
    public void deleteRecipe(Recipe recipe) {

        recipeRepository.delete(recipe);
    }

    /**
     * Standard function to obtain all the ingredients stored in the system. This is
     * generally used by the /ingredients route to provide a list of viable
     * ingredient filters.
     * 
     * @return a list of all ingredients in the system
     */
    public List<Ingredient> findAllIngredients() {
        List<Ingredient> list = new ArrayList<>();
        ingredientRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    /**
     * Standard function to save an ingredient gotten from a request body.
     * 
     * @param ingredient the IngredientDto made from the request body information
     * @return the Ingredient entity created from saving it
     */
    @Override
    public Ingredient createIngredient(IngredientDto ingredient) {

        Ingredient nIngredient = ingredient.getIngredientFromDto();
        return ingredientRepository.save(nIngredient);
    }

    /**
     * Standard function to delete an ingredient based on its entity.
     * 
     * @param ingredient the Ingredient entity to delete
     */
    @Override
    public void deleteIngredient(Ingredient ingredient) {

        ingredientRepository.delete(ingredient);
    }

}