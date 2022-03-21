package hi.feedme.feedme.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import hi.feedme.feedme.models.Ingredient;
import hi.feedme.feedme.models.IngredientInfo;
import hi.feedme.feedme.models.LoginInformation;
import hi.feedme.feedme.models.Recipe;
import hi.feedme.feedme.models.SimplifiedRecipe;

public class JSONParser {
    public static Recipe parseRecipe(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json.toString(), Recipe.class);
    }

    public static SimplifiedRecipe parseSimplifiedRecipe(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json.toString(), SimplifiedRecipe.class);
    }

    public static ArrayList<Recipe> parseRecipes(String json) throws JsonProcessingException {
        ArrayList<Recipe> recipeList = new ArrayList<>();
        return recipeList;
    }

    public static Ingredient parseIngredient(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json.toString(), Ingredient.class);
    }

    public static IngredientInfo parseIngredientInfo(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json.toString(), IngredientInfo.class);
    }

    public static LoginInformation parseLogin(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json.toString(), LoginInformation.class);
    }
}
