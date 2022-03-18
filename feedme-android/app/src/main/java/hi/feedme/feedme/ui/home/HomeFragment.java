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

import org.json.JSONObject;

import java.util.ArrayList;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        // May want to show something here later
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.recyclerView = view.findViewById(R.id.list);
        Context context = recyclerView.getContext();

        // Set the adapter
        if (mColumnCount <= 1) {
            this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            this.recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        this.recyclerView.setAdapter(new RecipeRecyclerViewAdapter(RecipeContent.ITEMS));

        Button btn = view.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Networking c = ((MainActivity) getActivity()).getNetwork();
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

                c.getRecipes(new RecipeListNwCallback() {
                    @Override
                    public void notifySuccess(ArrayList<SimplifiedRecipe> response) throws JsonProcessingException {
                        setData(response);
                        // for(SimplifiedRecipe r : response) {
                        //    System.out.println("recipe name: " + r.getName());
                        //}
                    }

                    @Override
                    public void notifyError(VolleyError error) {

                    }
                });
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

    public void setData(ArrayList<SimplifiedRecipe> rs) {
        //RecipeContent.ITEMS = rs; // CHANGE TO SIMPLIFIED RECIPE INSTEAD OF PLACEHOLDER
        for(SimplifiedRecipe r : rs) {
            RecipeContent.ITEMS.add(r);
            RecipeContent.ITEM_MAP.put(r.getId(), r);
            }

        recyclerView.getAdapter().notifyDataSetChanged();
    }
}