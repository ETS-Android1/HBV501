package hi.feedme.feedme.ui.recipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hi.feedme.feedme.R;
import hi.feedme.feedme.models.Ingredient;
import hi.feedme.feedme.models.Recipe;

public class RecipeFragment extends Fragment {
    private ExpandableListView expandableIngredientListView;
    private ExpandableListAdapter expandableIngredientListAdapter;
    private List<String> expandableIngredientTitleList;
    private HashMap<String, List<Ingredient>> expandableIngredientDetailList;
    private Recipe shownRecipe;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();

        if (b != null) {
            shownRecipe = (Recipe) b.getSerializable("recipe");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        TextView txt = view.findViewById(R.id.recipe_name);

        txt.setText(shownRecipe.getName());

        ArrayList<Ingredient> ingredients = shownRecipe.getIngredients();

        expandableIngredientListView = (ExpandableListView) view.findViewById(R.id.ingredients_list);
        expandableIngredientDetailList = new HashMap<String, List<Ingredient>>();
        expandableIngredientDetailList.put("Ingredients", ingredients);
        expandableIngredientTitleList = new ArrayList<String>(expandableIngredientDetailList.keySet());
        expandableIngredientListAdapter = new ExpandableIngredientListAdapter(view.getContext(), expandableIngredientTitleList, expandableIngredientDetailList);
        expandableIngredientListView.setAdapter(expandableIngredientListAdapter);

        return view;
    }
}