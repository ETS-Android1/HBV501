package hi.feedme.feedme.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hi.feedme.feedme.models.Ingredient;
import hi.feedme.feedme.models.Recipe;

public class JSONParser {
    public static Recipe toRecipe(String json) throws JsonProcessingException {
        Recipe rec = new ObjectMapper().readValue(json.toString(), Recipe.class);
        return rec;
    }

    public static Ingredient toIngredient(String json) throws JsonProcessingException {
        Ingredient in = new ObjectMapper().readValue(json.toString(), Ingredient.class);
        return in;
    }
}
