package hi.feedme.feedme.listeners;

import com.android.volley.VolleyError;

import hi.feedme.feedme.models.LoginInformation;

public interface RegisterNwCallback {
    void notifySuccess(String response);
    void notifyError(VolleyError error);
}
