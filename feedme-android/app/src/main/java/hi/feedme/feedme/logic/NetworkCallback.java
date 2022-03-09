package hi.feedme.feedme.logic;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;

public interface NetworkCallback {
    void notifySuccess(JSONObject response) throws JsonProcessingException;
    void notifyError(VolleyError error);
}
