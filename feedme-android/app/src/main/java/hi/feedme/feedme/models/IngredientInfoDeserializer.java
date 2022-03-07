package hi.feedme.feedme.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;

public class IngredientInfoDeserializer extends StdDeserializer<IngredientInfo> {

    public IngredientInfoDeserializer() {
        this(null);
    }

    public IngredientInfoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public IngredientInfo deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        int id = (Integer) ((IntNode) node.get("id")).numberValue();
        String name = node.get("name").asText();
        return new IngredientInfo(id, name);
    }
}
