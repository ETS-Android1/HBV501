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
    private NetworkCallback resultCallback = null;
    private Context mContext;
    private final String ROOT = "https://obscure-headland-63204.herokuapp.com/";

    public Networking(NetworkCallback result, Context ctx) {
        mContext = ctx;
        resultCallback = result;
    }

    public void fetchGET(String path) {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, ROOT + path, null,
                response -> {
                    if (resultCallback != null) {
                        try {
                            resultCallback.notifySuccess(response);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> {
                    if (resultCallback != null)
                        resultCallback.notifyError(error);
                });
        queue.add(jsonObjectRequest);
    }

    public void sendReq() {
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
