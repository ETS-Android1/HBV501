package hi.feedme.feedme.ui.recipe;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hi.feedme.feedme.R;
import hi.feedme.feedme.models.Ingredient;
import hi.feedme.feedme.models.Recipe;
import hi.feedme.feedme.models.Review;
import hi.feedme.feedme.models.SimplifiedRecipe;
import hi.feedme.feedme.ui.home.RecipeContent;
import hi.feedme.feedme.ui.home.RecipeRecyclerViewAdapter;

public class RecipeFragment extends Fragment {
    private Recipe shownRecipe;
    private RecyclerView recyclerView;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments(); // Should receive a serialized Recipe object

        if (b != null) {
            shownRecipe = (Recipe) b.getSerializable("recipe");
        }
    }

    /**
     * Helper function to provide size collapsing for an ExpandableListView
     * Collapsing feature does not work by default when the list is embedded in a ScrollView
     *
     * @param listView  The list to expand
     * @param group     The position of the group
     */
    private void setListViewHeight(ExpandableListView listView, int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
                //Add Divider Height
                totalHeight += listView.getDividerHeight() * (listAdapter.getChildrenCount(i) - 1);
            } else {
                totalHeight += 72; // Seems to break with one collapsed group, add back some padding
            }
        }
        //Add Divider Height
        totalHeight += listView.getDividerHeight() * (listAdapter.getGroupCount() - 1);

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /**
     * Initialize and set the recyclerView adapter for the list reviews
     *
     * @param v the view the recyclerView is in
     */
    private void initAdapter(View v) {
        this.recyclerView = v.findViewById(R.id.review_list);

        Context context = recyclerView.getContext();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ReviewContent.items.clear();
        ArrayList<Review> rs = shownRecipe.getReviews();

        for (int i = 0; i < rs.size(); i++) {
            Review r = rs.get(i);
            ReviewContent.items.add(r);
            ReviewContent.itemMap.put(i, r);
        }

        this.recyclerView.setAdapter(new ReviewRecyclerViewAdapter(ReviewContent.items));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        initAdapter(view);

        ((TextView) view.findViewById(R.id.recipe_name)).setText(shownRecipe.getName());
        ((TextView) view.findViewById(R.id.recipe_description)).setText(shownRecipe.getDescription());
        ((TextView) view.findViewById(R.id.recipe_instructions)).setText(shownRecipe.getInstructions());
        ((TextView) view.findViewById(R.id.cal_quant)).setText(String.format("%s", shownRecipe.getCalories()));
        ((TextView) view.findViewById(R.id.fat_quant)).setText(String.format("%s", shownRecipe.getFats()));
        ((TextView) view.findViewById(R.id.carb_quant)).setText(String.format("%s", shownRecipe.getCarbs()));
        ((TextView) view.findViewById(R.id.protein_quant)).setText(String.format("%s", shownRecipe.getProteins()));

        ArrayList<Ingredient> ingredients = shownRecipe.getIngredients();
        ExpandableListView expandableIngredientListView = (ExpandableListView) view.findViewById(R.id.ingredients_list);
        HashMap<String, List<Ingredient>> expandableIngredientDetailList = new HashMap<String, List<Ingredient>>();
        expandableIngredientDetailList.put("Ingredients:", ingredients);
        List<String> expandableIngredientTitleList = new ArrayList<String>(expandableIngredientDetailList.keySet());
        ExpandableListAdapter expandableIngredientListAdapter = new ExpandableIngredientListAdapter(view.getContext(), expandableIngredientTitleList, expandableIngredientDetailList);
        expandableIngredientListView.setAdapter(expandableIngredientListAdapter);
        expandableIngredientListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        return view;
    }
}