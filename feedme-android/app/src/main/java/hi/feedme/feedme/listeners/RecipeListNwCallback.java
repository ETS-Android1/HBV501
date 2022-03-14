package hi.feedme.feedme.listeners;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;

import hi.feedme.feedme.models.Recipe;
import hi.feedme.feedme.models.SimplifiedRecipe;

public interface RecipeListNwCallback {
    void notifySuccess(ArrayList<SimplifiedRecipe> response) throws JsonProcessingException;
    void notifyError(VolleyError error);
}
