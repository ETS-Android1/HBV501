package hi.feedme.feedme.ui.recipe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import hi.feedme.feedme.R;
import hi.feedme.feedme.models.Ingredient;

/**
 * Adapter for the Ingredient list displayed on the RecipeFragment
 */
public class ExpandableIngredientListAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final List<String> expandableTitleList;
    private final HashMap<String, List<Ingredient>> expandableDetailList;

    public ExpandableIngredientListAdapter(Context context, List<String> expandableListTitle,
                                           HashMap<String, List<Ingredient>> expandableListDetail) {
        this.context = context;
        this.expandableTitleList = expandableListTitle;
        this.expandableDetailList = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return Objects.requireNonNull(this.expandableDetailList.get(this.expandableTitleList.get(listPosition))).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final Ingredient curr = (Ingredient) getChild(listPosition, expandedListPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ingredient_list_item, null);
        }

        TextView expandedListTextViewName = (TextView) convertView.findViewById(R.id.expanded_ingredient_name);
        expandedListTextViewName.setText(curr.getIngredient().getName());

        String displayedQuant = curr.getQuantity() + " " + curr.getUnit();
        TextView expandedListTextViewQuantity = (TextView) convertView.findViewById(R.id.expanded_ingredient_quantity);
        expandedListTextViewQuantity.setText(displayedQuant);

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return Objects.requireNonNull(this.expandableDetailList.get(this.expandableTitleList.get(listPosition))).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableTitleList.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableTitleList.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ingredient_list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.ingredient_name);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}