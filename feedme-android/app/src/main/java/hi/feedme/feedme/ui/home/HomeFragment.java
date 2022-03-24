package hi.feedme.feedme.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import hi.feedme.feedme.MainActivity;
import hi.feedme.feedme.R;
import hi.feedme.feedme.listeners.IngredientListNwCallback;
import hi.feedme.feedme.listeners.RecipeListNwCallback;
import hi.feedme.feedme.logic.Networking;
import hi.feedme.feedme.models.IngredientInfo;
import hi.feedme.feedme.models.SimplifiedRecipe;

/**
 * The main fragment of the application.
 * <p>
 * It contains a filters toolbar as well as a list of SimplifiedRecipes
 */
public class HomeFragment extends Fragment {
    // Column count currently unused, may used it for landscape layout if time allows
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private String[] appIngredients;
    private Map<String, Integer> ingredientMap = new HashMap<String, Integer>();

    private RecyclerView recyclerView;
    private AutoCompleteTextView ingredientSuggestView;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /**
     * Helper class to populate autosuggestions when backend response returns
     *
     * @param a The Activity the adapter should be bound to
     * @param m The menu that contains the autosuggestion item
     */
    private void searchHelper(MainActivity a, Menu m) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(a, android.R.layout.select_dialog_item, appIngredients);

        MenuItem searchItem = m.findItem(R.id.search);
        ingredientSuggestView = (AutoCompleteTextView) searchItem.getActionView();
        ingredientSuggestView.setWidth((int) Math.round(a.getWindow().getDecorView().getWidth() * 0.8));
        ingredientSuggestView.setAdapter(adapter);
        ingredientSuggestView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                InputMethodManager in = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(arg1.getApplicationWindowToken(), 0);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MainActivity act = (MainActivity) requireActivity();

        if (appIngredients == null) {
            act.getNetwork().getIngredients(new IngredientListNwCallback() {
                @Override
                public void notifySuccess(ArrayList<IngredientInfo> response) throws JsonProcessingException {
                    appIngredients = new String[response.size()];

                    for (int i = 0; i < response.size(); i++) {
                        String name = response.get(i).getName();
                        appIngredients[i] = name;
                        ingredientMap.put(name, response.get(i).getId());
                    }

                    searchHelper(act, menu);
                }

                @Override
                public void notifyError(VolleyError error) {
                    // Unused
                }
            });
        } else {
            searchHelper(act, menu);
        }
    }

    /**
     * Use fragment toolbar as main supporting action bar for the main activity
     *
     * @param v the view to get the toolbar from
     */
    private void initToolbar(View v) {
        Toolbar toolbar = v.findViewById(R.id.toolbar2);

        ((MainActivity) requireActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        // May want to show something here later
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    /**
     * Initialize and set the recyclerView adapter for the list simplified recipes
     *
     * @param v the view the recyclerView is in
     */
    private void initAdapter(View v) {
        this.recyclerView = v.findViewById(R.id.list);
        Context context = recyclerView.getContext();

        if (mColumnCount <= 1) {
            this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            this.recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        this.recyclerView.setAdapter(new RecipeRecyclerViewAdapter(RecipeContent.items));
    }

    /**
     * Helper function to repopulate the recyclerView content items
     *
     * @param rs a list of SimplifiedRecipe objects to replace the current content with
     */
    @SuppressLint("NotifyDataSetChanged")
    private void setData(ArrayList<SimplifiedRecipe> rs) {
        RecipeContent.items.clear();

        for (SimplifiedRecipe r : rs) {
            RecipeContent.items.add(r);
            RecipeContent.itemMap.put(r.getId(), r);
        }

        // This is fine for the purpose of changing the entire set
        // It is only more efficient to use ItemChanged if changing single items
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
    }

    /**
     * Helper function to request new recipes based on the chosen filter settings
     *
     * @param scals  The calorie slider
     * @param sf     The fat slider
     * @param scarbs The carbs slider
     * @param sp     The proteins slider
     */
    private void refreshRecipes(RangeSlider scals, RangeSlider sf, RangeSlider scarbs, RangeSlider sp) {
        Networking conn = ((MainActivity) getActivity()).getNetwork();
        List<Float> currVals;

        // Populate querystring based on filters
        String query = "?sort=rating";
        currVals = scals.getValues();
        query += "&mincalories=" + Math.round(currVals.get(0));
        query += "&maxcalories=" + Math.round(currVals.get(1));

        currVals = sf.getValues();
        query += "&minfats=" + Math.round(currVals.get(0));
        query += "&maxfats=" + Math.round(currVals.get(1));

        currVals = scarbs.getValues();
        query += "&mincarbs=" + Math.round(currVals.get(0));
        query += "&maxcarbs=" + Math.round(currVals.get(1));

        currVals = sp.getValues();
        query += "&minproteins=" + Math.round(currVals.get(0));
        query += "&maxproteins=" + Math.round(currVals.get(1));

        query += "&ingredients=" + ingredientMap.get(ingredientSuggestView.getText().toString());

        // Backend call
        conn.getRecipes(new RecipeListNwCallback() {
            @Override
            public void notifySuccess(ArrayList<SimplifiedRecipe> response) throws JsonProcessingException {
                // Replace recyclerview items
                setData(response);
            }

            @Override
            public void notifyError(VolleyError error) {
                Toast.makeText((Context) getActivity(), "Failed to fetch recipes!", Toast.LENGTH_SHORT).show();
            }
        }, query);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initToolbar(view);
        initAdapter(view);

        // Would split this initialization into another function using view as argument
        // But we need these sliders anyway for the onclick method
        RangeSlider scals = view.findViewById(R.id.slider_calories);
        RangeSlider sf = view.findViewById(R.id.slider_fats);
        RangeSlider scarbs = view.findViewById(R.id.slider_carbs);
        RangeSlider sp = ((RangeSlider) view.findViewById(R.id.slider_protein));

        scals.setValues(0.0f, 1000.0f);
        sf.setValues(0.0f, 100.0f);
        scarbs.setValues(0.0f, 100.0f);
        sp.setValues(0.0f, 100.0f);

        Button btn = view.findViewById(R.id.search_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshRecipes(scals, sf, scarbs, sp);
            }
        });

        return view;
    }
}