package hi.feedme.feedme.listeners;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;

import hi.feedme.feedme.models.Recipe;

public interface RecipeNwCallback {
    void notifySuccess(Recipe response) throws JsonProcessingException;
    void notifyError(VolleyError error);
}
