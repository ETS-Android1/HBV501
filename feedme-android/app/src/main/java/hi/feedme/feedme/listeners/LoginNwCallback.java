package hi.feedme.feedme.listeners;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;

public interface LoginNwCallback {
    void notifySuccess(JSONObject response) throws JsonProcessingException;
    void notifyError(VolleyError error);
}
