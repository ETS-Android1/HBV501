package hi.feedme.feedme.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
        double rating = (double) ((DoubleNode) node.get("rating")).numberValue();
        double calories = (double) ((DoubleNode) node.get("calories")).numberValue();
        double carbs = (double) ((DoubleNode) node.get("carbs")).numberValue();
        double fats = (double) ((DoubleNode) node.get("carbs")).numberValue();
        double proteins = (double) ((DoubleNode) node.get("proteins")).numberValue();
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
        JsonNode t = node.get("ingredients");
        ArrayList<Ingredient> ingrs = new ArrayList<Ingredient>();
        if (t.isArray()) {
            Iterator<JsonNode> itr = t.iterator();
            while (itr.hasNext()) {
                JsonNode item=itr.next();
                // do something with array elements
                Ingredient in = new ObjectMapper().readValue(item.toString(), Ingredient.class);
                ingrs.add(in);
            }
        }
        nrec.setIngredients(ingrs);
        return nrec;
    }
}
