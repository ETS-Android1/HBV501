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

public class IngredientDeserializer extends StdDeserializer<Ingredient> {

    public IngredientDeserializer() {
        this(null);
    }

    public IngredientDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Ingredient deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        double quantity = node.get("quantity").asDouble();
        String unit = node.get("unit").asText();
        IngredientInfo ingInfo = new ObjectMapper().readValue(node.get("ingredient").toString(), IngredientInfo.class);
        return new Ingredient(ingInfo, quantity, unit);
    }
}
