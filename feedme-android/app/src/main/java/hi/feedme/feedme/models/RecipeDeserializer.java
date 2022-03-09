package hi.feedme.feedme.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class RecipeDeserializer extends StdDeserializer<Recipe> {

    public RecipeDeserializer() {
        this(null);
    }

    public RecipeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Recipe deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        int id = (Integer) ((IntNode) node.get("id")).numberValue();
        String name = node.get("name").asText();
        String desc = node.get("description").asText();
        String inst = node.get("instructions").asText();
        String img = node.get("image").asText();
        double rating = node.get("rating").asDouble();
        double calories = node.get("calories").asDouble();
        double carbs = node.get("carbs").asDouble();
        double fats = node.get("carbs").asDouble();
        double proteins = node.get("proteins").asDouble();
        Boolean user_stored = (Boolean) node.get("user_stored").asBoolean();
        Recipe nrec = new Recipe();
        nrec.setName(name);
        nrec.setId(id);
        nrec.setDescription(desc);
        nrec.setInstructions(inst);
        nrec.setImage(img);
        nrec.setRating(rating);
        nrec.setCalories(calories);
        nrec.setCarbs(carbs);
        nrec.setFats(fats);
        nrec.setProteins(proteins);
        nrec.setUser_stored(user_stored);
        JsonNode ing_arr = node.get("ingredients");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        if (ing_arr.isArray()) {
            Iterator<JsonNode> itr = ing_arr.iterator();
            while (itr.hasNext()) {
                JsonNode item=itr.next();
                Ingredient in = new ObjectMapper().readValue(item.toString(), Ingredient.class);
                ingredients.add(in);
            }
        }
        nrec.setIngredients(ingredients);

        JsonNode rev_arr = node.get("reviews");
        ArrayList<Review> revs = new ArrayList<>();
        if (rev_arr.isArray()) {
            Iterator<JsonNode> itr = rev_arr.iterator();
            while (itr.hasNext()) {
                JsonNode item = itr.next();
                Review rev = new ObjectMapper().readValue(item.toString(), Review.class);
                revs.add(rev);
            }
        }
        nrec.setReviews(revs);
        System.out.println("Test: " + revs.size());
        return nrec;
    }
}
