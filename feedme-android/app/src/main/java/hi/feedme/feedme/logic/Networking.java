package hi.feedme.feedme.logic;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import hi.feedme.feedme.models.Ingredient;
import hi.feedme.feedme.models.Recipe;

public class Networking {
    // Database stuff
    private ReqQueue req;
    public Networking(Context ctx) throws JsonProcessingException {
        String json = "{\"id\":1,\"name\":\"Chicken and Broccoli Stir-Fry\",\"description\":\"An easy and delicious stir-fry anyone could make to impress their loved ones! Great with rice or by itself.\",\"instructions\":\"1. Make the Sauce: Combine broth, soy sauce, brown sugar, corn starch, sesame oil, ginger, galic and black pepper, and stir to dissolve the sugar. \\n    The warm broth will help the sugar to dissolve easily.\\n    2. Cook Chicken: Cut chicken into small 3/4â€³ bite-sized pieces and season lightly with black pepper. Heat a large heavy skillet over medium-high heat with 1 Tbsp oil. \\n    Add chicken and cook 1 minute undisturbed then stir fry 5 minutes until golden and just cooked through. Remove chicken to a bowl.\\n    3. Sautee Veggies: In the same skillet over medium-high heat, add olive oil, cut broccoli florets, sliced onion and mushrooms, and stir fry for about 3 minutes \\n    until mushrooms are softened and then reduce heat.\\n    4. Add Stir Fry Sauce: Give the sauce a quick stir in case there was settling and add it to the skillet. \\n    Simmer the sauce over medium/low heat for 3-4 minutes until thickened and garlic and ginger have mellowed in flavor. \\n    To thin the sauce, add water as needed and you can add more soy sauce to taste.\\n    5. Return Chicken to the Pan and stir until heated through then serve over white rice garnished with sesame seeds if desired.\",\"rating\":3.0,\"calories\":325.0,\"carbs\":21.0,\"proteins\":31.0,\"fats\":14.0,\"image\":\"Get image\",\"comments\":[{\"id\":1,\"body\":\"Dry\",\"username\":\"johndoe\",\"user_id\":2}],\"reviews\":[{\"title\":\"Bleh\",\"subtitle\":\"Meh\",\"date\":\"2022-03-07T14:37:17.728+00:00\",\"rating\":3,\"username\":\"johndoe\",\"user_id\":2}],\"user_stored\":false,\"ingredients\":[{\"ingredient\":{\"id\":50,\"name\":\"onion\"},\"quantity\":0.25,\"unit\":\"cups\"},{\"ingredient\":{\"id\":2,\"name\":\"chicken broth\"},\"quantity\":0.67,\"unit\":\"cups\"},{\"ingredient\":{\"id\":59,\"name\":\"soy sauce\"},\"quantity\":3.0,\"unit\":\"tbsp\"},{\"ingredient\":{\"id\":1,\"name\":\"chicken\"},\"quantity\":1.0,\"unit\":\"lb\"},{\"ingredient\":{\"id\":23,\"name\":\"olive oil\"},\"quantity\":2.0,\"unit\":\"tbsp\"},{\"ingredient\":{\"id\":53,\"name\":\"mushroom\"},\"quantity\":0.5,\"unit\":\"lb\"},{\"ingredient\":{\"id\":43,\"name\":\"black pepper\"},\"quantity\":0.25,\"unit\":\"tsp\"},{\"ingredient\":{\"id\":52,\"name\":\"ginger\"},\"quantity\":1.0,\"unit\":\"tbsp\"},{\"ingredient\":{\"id\":57,\"name\":\"broccoli\"},\"quantity\":5.0,\"unit\":\"cups\"},{\"ingredient\":{\"id\":46,\"name\":\"garlic\"},\"quantity\":1.0,\"unit\":\"tsp\"},{\"ingredient\":{\"id\":39,\"name\":\"corn starch\"},\"quantity\":1.0,\"unit\":\"tbsp\"},{\"ingredient\":{\"id\":32,\"name\":\"brown sugar\"},\"quantity\":2.0,\"unit\":\"tbsp\"}]}";
        Recipe itemWithOwner = new ObjectMapper().readValue(json, Recipe.class);
        req = new ReqQueue(ctx);
        for(Ingredient r : itemWithOwner.getIngredients()) {
            System.out.println("Ingredient name: " + r.getIngredient().getName());
            System.out.println("Ingredient quantity: " + r.getQuantity());
            System.out.println("Ingredient unit: " + r.getUnit());
        }
    }

    public void sendReq(android.content.Context ctx) {
        String url = "https://obscure-headland-63204.herokuapp.com/recipes/5";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
        (Request.Method.GET, url, null, response -> System.out.println("Response: " + response.toString())
                , error -> {
            // TODO: Handle error
            System.out.println("Error!: " + error.toString());
        });
        req.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
