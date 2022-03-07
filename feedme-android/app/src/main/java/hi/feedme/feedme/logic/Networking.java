package hi.feedme.feedme.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hi.feedme.feedme.models.Recipe;

public class Networking {
    // Database stuff

    public Networking() throws JsonProcessingException {
        String json = "{\"id\":5,\"name\":\"French Chocolate Cake\",\"description\":\"The best chocolate cake you will ever eat\",\"instructions\":\"Preheat the oven to 350Â°F (180Â°C) and grease a 9-inch (23 cm) spring form. \\n    In a medium-size saucepan place on low heat, melt the chocolate with the milk. \\n    Add the butter and allow to melt, stirring as needed. \\n    Turn off the heat, then add the sugar and the yolks, one at a time, \\n    stirring between each addition. Sift in the flour and stir well.\\n    In a medium-sized bowl, whisk the whites with a pinch of salt until soft peaks form. \\n    Carefully incorporate to the chocolate mixture in two or three additions. \\n    Pour into the prepared spring form and bake for about 30-35 minutes.\",\"rating\":-1.0,\"calories\":265.2,\"carbs\":21.5,\"proteins\":3.0,\"fats\":20.1,\"image\":\"Get image\",\"comments\":[],\"reviews\":[],\"user_stored\":false,\"ingredients\":[{\"ingredient\":{\"id\":16,\"name\":\"egg whites\"},\"quantity\":2.0,\"unit\":\"\"},{\"ingredient\":{\"id\":42,\"name\":\"salt\"},\"quantity\":1.0,\"unit\":\"pinch\"},{\"ingredient\":{\"id\":30,\"name\":\"flour\"},\"quantity\":2.0,\"unit\":\"tbsp\"},{\"ingredient\":{\"id\":22,\"name\":\"butter\"},\"quantity\":125.0,\"unit\":\"g\"},{\"ingredient\":{\"id\":15,\"name\":\"egg yolks\"},\"quantity\":2.0,\"unit\":\"\"},{\"ingredient\":{\"id\":40,\"name\":\"baking chocolate\"},\"quantity\":125.0,\"unit\":\"g\"},{\"ingredient\":{\"id\":31,\"name\":\"sugar\"},\"quantity\":125.0,\"unit\":\"g\"},{\"ingredient\":{\"id\":17,\"name\":\"milk\"},\"quantity\":3.0,\"unit\":\"tbsp\"}]}";
        Recipe itemWithOwner = new ObjectMapper().readValue(json, Recipe.class);
    }
}
