package hi.feedme.feedme.ui.recipe;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;

import hi.feedme.feedme.R;
import hi.feedme.feedme.models.Ingredient;
import hi.feedme.feedme.models.Recipe;

public class ExpandableIngredientListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> expandableTitleList;
    private HashMap<String, List<Ingredient>> expandableDetailList;

    public ExpandableIngredientListAdapter(Context context, List<String> expandableListTitle,
                                           HashMap<String, List<Ingredient>> expandableListDetail) {
        this.context = context;
        this.expandableTitleList = expandableListTitle;
        this.expandableDetailList = expandableListDetail;
    }

    @Override
    public Object getChild(int lstPosn, int expanded_ListPosition) {
        return this.expandableDetailList.get(this.expandableTitleList.get(lstPosn)).get(expanded_ListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expanded_ListPosition) {
        return expanded_ListPosition;
    }

    @Override
    public View getChildView(int lstPosn, final int expanded_ListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final Ingredient curr = (Ingredient) getChild(lstPosn, expanded_ListPosition);

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
        return this.expandableDetailList.get(this.expandableTitleList.get(listPosition)).size();
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