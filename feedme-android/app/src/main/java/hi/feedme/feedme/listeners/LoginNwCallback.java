package hi.feedme.feedme.listeners;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;

import hi.feedme.feedme.models.LoginInformation;

public interface LoginNwCallback {
    void notifySuccess(LoginInformation response);
    void notifyError(VolleyError error);
}
