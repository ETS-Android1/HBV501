package hi.feedme.feedme.ui.home;

import hi.feedme.feedme.models.*;
import hi.feedme.feedme.listeners.*;
import hi.feedme.feedme.logic.*;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.material.slider.RangeSlider;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hi.feedme.feedme.R;
import hi.feedme.feedme.MainActivity;


/**
 * A fragment representing a list of Items.
 */
public class HomeFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HomeFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HomeFragment newInstance(int columnCount) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    private void initToolbar(View v) {
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar2);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        // May want to show something here later
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initAdapter(View v) {
        this.recyclerView = v.findViewById(R.id.list);
        Context context = recyclerView.getContext();

        if (mColumnCount <= 1) {
            this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            this.recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        this.recyclerView.setAdapter(new RecipeRecyclerViewAdapter(RecipeContent.ITEMS));
    }

    public void setData(ArrayList<SimplifiedRecipe> rs) {
        RecipeContent.ITEMS.clear();

        for(SimplifiedRecipe r : rs) {
            RecipeContent.ITEMS.add(r);
            RecipeContent.ITEM_MAP.put(r.getId(), r);
        }

        // This is fine for the purpose of changing the entire set
        // It is only more efficient to use ItemChanged if changing single items
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void refreshRecipes(RangeSlider scals, RangeSlider sf, RangeSlider scarbs, RangeSlider sp) {
        Networking conn = ((MainActivity) getActivity()).getNetwork();
        // ?mincalories=0&maxcalories=2147483647&mincarbs=0&maxcarbs=2147483647&minproteins=0&maxproteins=2147483647&minfats=0&maxfats=2147483647&sort=name"
        List<Float> currVals;

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

        conn.getRecipes(new RecipeListNwCallback() {
            @Override
            public void notifySuccess(ArrayList<SimplifiedRecipe> response) throws JsonProcessingException {
                setData(response);
            }

            @Override
            public void notifyError(VolleyError error) {

            }
        }, query);
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initToolbar(view);
        initAdapter(view);

        RangeSlider scals = view.findViewById(R.id.slider_calories);
        scals.setValues(0.0f,1000.0f);

        RangeSlider sf = view.findViewById(R.id.slider_fats);
        sf.setValues(0.0f,100.0f);

        RangeSlider scarbs = view.findViewById(R.id.slider_carbs);
        scarbs.setValues(0.0f,100.0f);

        RangeSlider sp = view.findViewById(R.id.slider_protein);
        sp.setValues(0.0f,100.0f);

        Button btn = view.findViewById(R.id.search_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshRecipes(scals, sf, scarbs, sp);

                /*
                c.getRecipeById("5", new RecipeNwCallback() {

                    @Override
                    public void notifySuccess(Recipe response) throws JsonProcessingException {
                        Toast.makeText(((MainActivity) getActivity()), response.getName(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void notifyError(VolleyError error) {

                    }
                });
                 */ //getRecipeById example

                //getRecipes example
                /*
                c.postLogin("admin", "1234567890", new LoginNwCallback() {
                    @Override
                    public void notifySuccess(LoginInformation response) {
                        Toast.makeText(getActivity(), "Successful login!", Toast.LENGTH_SHORT).show();
                        System.out.println("Token: " + response.getToken());
                    }

                    @Override
                    public void notifyError(VolleyError error) {
                        Toast.makeText(getActivity(), "Wrong username/password combination!", Toast.LENGTH_SHORT).show();
                    }
                });
                */ //login example
                /*c.postRegister("testuser126", "1234567890", "test6@test.com", false, new RegisterNwCallback() {
                    @Override
                    public void notifySuccess(String response) {
                        System.out.println("test register: " + response);
                    }

                    @Override
                    public void notifyError(VolleyError error) {
                        System.out.println(error.toString());
                    }
                }); */
            }
        });

        return view;
    }
}