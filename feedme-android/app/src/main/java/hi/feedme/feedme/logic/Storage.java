package hi.feedme.feedme.logic;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;

import hi.feedme.feedme.models.LoginInformation;

/**
 * SharedPreference storage for login information
 */
public class Storage {
    private static final String APP_TOKEN = "APP_TOKEN";

    private static final String LOGIN_INFORMATION = "LOGIN_INFORMATION";

    private Storage() {}

    /**
     * The main storage get-method.
     *
     * @param context the context of the request
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_TOKEN, Context.MODE_PRIVATE);
    }

    /**
     * Gets our login information in JSON form
     *
     * @param context the context of the request
     */
    public static String getJsonLogin(Context context) {
        return getSharedPreferences(context).getString(LOGIN_INFORMATION, null);
    }

    /**
     * Saves a json token to the  stored login information
     *
     * @param context the context of the request
     * @return Our json login information in a LoginInformation object
     * @throws JsonProcessingException If the json to LoginInformation parsing is invalid or unsuccessful.
     */
    public static LoginInformation getLoginInformation(Context context) throws JsonProcessingException {
        String json = getJsonLogin(context);
        return JSONParser.parseLogin(json);
    }

    /**
     * Saves a json token to the  stored login information
     *
     * @param context the context of the request
     * @param token the JWT token from the login attempt
     */
    public static void setJsonLogin(Context context, String token) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LOGIN_INFORMATION, token);
        editor.commit();
    }
}
