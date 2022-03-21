package hi.feedme.feedme.listeners;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;

import hi.feedme.feedme.models.IngredientInfo;

public interface IngredientListNwCallback {
    void notifySuccess(ArrayList<IngredientInfo> response) throws JsonProcessingException;
    void notifyError(VolleyError error);
}
