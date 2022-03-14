package hi.feedme.feedme.logic;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

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
    private Context context;
    public static final String ROOT = "https://obscure-headland-63204.herokuapp.com/";

    public Networking(Context context) {
        this.context = context;
    }

    public void getRecipeById(String id, NetworkCallback nwcb) {
        String url = ROOT + "recipes/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                    try {
                        nwcb.notifySuccess(response);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    // TODO: Handle error
                    System.out.println("Error!: " + error.toString());
                });
        ReqQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
