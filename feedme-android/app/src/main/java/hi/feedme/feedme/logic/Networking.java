package hi.feedme.feedme.logic;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hi.feedme.feedme.listeners.IngredientListNwCallback;
import hi.feedme.feedme.listeners.LoginNwCallback;
import hi.feedme.feedme.listeners.RecipeListNwCallback;
import hi.feedme.feedme.listeners.RecipeNwCallback;
import hi.feedme.feedme.listeners.RegisterNwCallback;
import hi.feedme.feedme.models.IngredientInfo;
import hi.feedme.feedme.models.LoginInformation;
import hi.feedme.feedme.models.Recipe;
import hi.feedme.feedme.models.SimplifiedRecipe;

/**
 * Networking logic to communicate with the heroku back-end
 */
public class Networking {
    // REST
    private Context context;
    public static final String ROOT = "https://obscure-headland-63204.herokuapp.com/";

    public Networking(Context context) {
        this.context = context;
    }


    /**
     * Fetches a recipe from the backend, to be returned in the callback
     *
     * @param id the target id of the recipe
     * @param nwcb instance of the callback listener when fetching has finished
     */
    public void getRecipeById(String id, RecipeNwCallback nwcb) {
        String url = ROOT + "recipes/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        //Parsing the json -> Recipe object
                        Recipe r = JSONParser.parseRecipe(response.toString());
                        //Returning the Recipe object via notifying the listener.
                        nwcb.notifySuccess(r);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    System.out.println("Error!: " + error.toString());
                    nwcb.notifyError(error);
                });
        ReqQueue.getInstance(context).addToRequestQueue(jsonObjectRequest); //Sending the HTTP request
    }

    /**
     * Fetches all the recipes from the backend satisfying the query, to be returned in the callback
     *
     * @param q query to be used for the Recipe filtering
     * @param nwcb instance of the callback listener when fetching has finished
     */
    public void getRecipes(RecipeListNwCallback nwcb, String q) {
        String url = ROOT + "recipes" + q;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, response -> {
                try {
                    ArrayList<SimplifiedRecipe> recipeList = new ArrayList();
                    //Getting all of the JSON from the JSON recipes array
                    JSONArray recipes = response.getJSONArray("recipes");
                    for(int i = 0; i < recipes.length(); i++) {
                        //Getting i-th recipe json, parsing it to a model and adding it to the return list.
                        String js = recipes.getJSONObject(i).toString();
                        SimplifiedRecipe r = JSONParser.parseSimplifiedRecipe(js);
                        recipeList.add(r);
                    }
                    //Returning the SimplifiedRecipe list to the listener.
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

    /**
     * Fetches all the ingredients from the backend currently in use, to be returned in the callback
     *
     * @param nwcb instance of the callback listener when fetching has finished
     */
    public void getIngredients(IngredientListNwCallback nwcb) {
        String url = ROOT + "ingredients";
        //Getting the ingredients JSON array
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            ArrayList<IngredientInfo> ingList = new ArrayList<>();
            for(int i=0;i<response.length();i++) {
                try {
                    //Getting i-th ingredient info, parsing it and adding it to the return list
                    JSONObject ing = response.getJSONObject(i);
                    IngredientInfo inginfo = JSONParser.parseIngredientInfo(ing.toString());
                    ingList.add(inginfo);
                } catch (JSONException | JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            try {
                //Returning the IngredientInfo list to the listener.
                nwcb.notifySuccess(ingList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }, nwcb::notifyError);
        ReqQueue.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }


    //POST

    /**
     * Attempts to login the user with a username and password combination.
     *
     * @param user username of the login request
     * @param pw password of the login request
     * @param nwcb instance of the callback listener, returning LoginInformation or an error.
     * @return LoginInformation from the callback upon a successful login
     */
    public void postLogin(String user, String pw, LoginNwCallback nwcb) {
        String url = ROOT + "users/login";
        //Create HashMap to handle our parameters that are sent with the HTTP request
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", user);
        params.put("password", pw);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), response -> {
            try {
                //If login is successful, store our token and login information in the storage
                Storage.setJsonLogin(context, response.toString());
                //Return the LoginInformation object of the logged in user.
                nwcb.notifySuccess(Storage.getLoginInformation(context));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }, nwcb::notifyError);
        ReqQueue.getInstance(context).addToRequestQueue(req);
    }

    /**
     * Attempts to register the user with a username, password and email combination.
     *
     * @param user username of the register request
     * @param pw password of the register request
     * @param email email of the register request
     * @param admin indicates whether the register should be treated as an admin or not
     * @param nwcb instance of the callback listener
     */
    public void postRegister(String user, String pw, String email, Boolean admin, RegisterNwCallback nwcb) {
        String url = ROOT + "users/register";
        //Create HashMap to handle our parameters that are sent with the HTTP request
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", user);
        params.put("password", pw);
        params.put("email", email);
        params.put("admin", admin.toString());
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), response -> {
            //If the register is successful, notify the listener.
            nwcb.notifySuccess("Success");
        }, nwcb::notifyError);
        ReqQueue.getInstance(context).addToRequestQueue(req);
    }

    /**
     * Favorites a given recipe, provided the user is logged in.
     *
     * @param recipeId id of the recipe that's being favorited
     */
    public void postFavorite(String recipeId) {
        LoginInformation current = null;
        try {
            current = Storage.getLoginInformation(context);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(current == null) return; //User isn't logged or has an expired token
        String url = ROOT + "users/me/recipes/" + recipeId;
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, null, response -> {

        }, error -> {})
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //We need to override the headers in order to place our custom authorization
                return Storage.authHeader(context);
            }
        };
        ReqQueue.getInstance(context).addToRequestQueue(req);
    }

    public void deleteFavorite(String recipeId) {
        LoginInformation current = null;
        try {
            current = Storage.getLoginInformation(context);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(current == null) return; //User isn't logged or has an expired token
        String url = ROOT + "users/me/recipes/" + recipeId;
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.DELETE, url, null, response -> {

        }, error -> {})
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //We need to override the headers in order to place our custom authorization
                return Storage.authHeader(context);
            }
        };
        ReqQueue.getInstance(context).addToRequestQueue(req);
    }

    public void getFavoriteRecipes(RecipeListNwCallback nwcb) {
        LoginInformation current = null;
        try {
            current = Storage.getLoginInformation(context);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(current == null) return; //User isn't logged or has an expired token
        String url = ROOT + "users/me";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                ArrayList<SimplifiedRecipe> recipeList = new ArrayList();
                //Getting all of the JSON from the JSON recipes array
                JSONArray recipes = response.getJSONArray("recipes");
                for (int i = 0; i < recipes.length(); i++) {
                    //Getting i-th recipe json, parsing it to a model and adding it to the return list.
                    String js = recipes.getJSONObject(i).toString();
                    SimplifiedRecipe r = JSONParser.parseSimplifiedRecipe(js);
                    recipeList.add(r);
                }
                //Returning the SimplifiedRecipe list to the listener.
                nwcb.notifySuccess(recipeList);
            }
            catch (JSONException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }, nwcb::notifyError)
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return Storage.authHeader(context);
            }
        };
        ReqQueue.getInstance(context).addToRequestQueue(req);
    }
}


