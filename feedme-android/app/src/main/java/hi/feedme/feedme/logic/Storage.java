package hi.feedme.feedme.logic;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;

import hi.feedme.feedme.models.LoginInformation;

public class Storage {
    private static final String APP_TOKEN = "APP_TOKEN";

    private static final String LOGIN_INFORMATION = "LOGIN_INFORMATION";

    private Storage() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_TOKEN, Context.MODE_PRIVATE);
    }

    public static String getJsonLogin(Context context) {
        return getSharedPreferences(context).getString(LOGIN_INFORMATION, null);
    }

    public static LoginInformation getLoginInformation(Context context) throws JsonProcessingException {
        String json = getJsonLogin(context);
        return JSONParser.parseLogin(json);
    }

    public static void setJsonLogin(Context context, String token) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LOGIN_INFORMATION, token);
        editor.commit();
    }
}
