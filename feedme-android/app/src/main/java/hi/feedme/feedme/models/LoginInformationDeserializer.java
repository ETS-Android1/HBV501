package hi.feedme.feedme.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;


public class LoginInformationDeserializer extends StdDeserializer<LoginInformation> {

    public LoginInformationDeserializer() {
        this(null);
    }

    public LoginInformationDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LoginInformation deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String token = node.get("token").asText();
        int expiration = node.get("expiration").asInt();
        User user = new ObjectMapper().readValue(node.get("user").toString(), User.class);
        return new LoginInformation(user, token, expiration);
    }
}
