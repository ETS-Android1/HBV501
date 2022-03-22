package hi.feedme.feedme.logic;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import hi.feedme.feedme.listeners.IngredientListNwCallback;
import hi.feedme.feedme.listeners.LoginNwCallback;
import hi.feedme.feedme.listeners.RecipeListNwCallback;
import hi.feedme.feedme.listeners.RecipeNwCallback;
import hi.feedme.feedme.listeners.RegisterNwCallback;
import hi.feedme.feedme.models.Ingredient;
import hi.feedme.feedme.models.IngredientInfo;
import hi.feedme.feedme.models.Recipe;
import hi.feedme.feedme.models.SimplifiedRecipe;

public class Networking {
    // Database stuff
    private Context context;
    public static final String ROOT = "https://obscure-headland-63204.herokuapp.com/";

    public Networking(Context context) {
        this.context = context;
    }


    //GET

    public void getRecipeById(String id, RecipeNwCallback nwcb) {
        String url = ROOT + "recipes/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        Recipe r = JSONParser.parseRecipe(response.toString());
                        nwcb.notifySuccess(r);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    System.out.println("Error!: " + error.toString());
                    nwcb.notifyError(error);
                });
        ReqQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void getRecipes(RecipeListNwCallback nwcb, String q) {
        String url = ROOT + "recipes" + q;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, response -> {
                try {
                    ArrayList<SimplifiedRecipe> recipeList = new ArrayList();
                    JSONArray recipes = response.getJSONArray("recipes");
                    for(int i = 0; i < recipes.length(); i++) {
                        String js = recipes.getJSONObject(i).toString();
                        SimplifiedRecipe r = JSONParser.parseSimplifiedRecipe(js);
                        recipeList.add(r);
                    }
                    nwcb.notifySuccess(recipeList);
                } catch (JSONException | JsonProcessingException e) {
                    e.printStackTrace();
                }
            }, error -> {
                System.out.println("Error!: " + error.toString());
                nwcb.notifyError(error);
            });
        ReqQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void getIngredients(IngredientListNwCallback nwcb) {
        String url = ROOT + "ingredients";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            ArrayList<IngredientInfo> ingList = new ArrayList<>();
            for(int i=0;i<response.length();i++) {
                try {
                    JSONObject ing = response.getJSONObject(i);
                    IngredientInfo inginfo = JSONParser.parseIngredientInfo(ing.toString());
                    ingList.add(inginfo);
                } catch (JSONException | JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            try {
                nwcb.notifySuccess(ingList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }, nwcb::notifyError);
        ReqQueue.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }


    //POST

    public void postLogin(String user, String pw, LoginNwCallback nwcb) {
        String url = ROOT + "users/login";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", user);
        params.put("password", pw);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), response -> {
            try {
                Storage.setJsonLogin(context, response.toString());
                nwcb.notifySuccess(Storage.getLoginInformation(context));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }, nwcb::notifyError);
        ReqQueue.getInstance(context).addToRequestQueue(req);
    }

    public void postRegister(String user, String pw, String email, Boolean admin, RegisterNwCallback nwcb) {
        String url = ROOT + "users/register";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", user);
        params.put("password", pw);
        params.put("email", email);
        params.put("admin", admin.toString());
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), response -> {
            nwcb.notifySuccess("Success");
        }, nwcb::notifyError);
        ReqQueue.getInstance(context).addToRequestQueue(req);
    }


}


