package hi.feedme.feedme.ui.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;

import hi.feedme.feedme.MainActivity;
import hi.feedme.feedme.R;
import hi.feedme.feedme.listeners.RecipeListNwCallback;
import hi.feedme.feedme.listeners.RecipeNwCallback;
import hi.feedme.feedme.logic.Networking;
import hi.feedme.feedme.models.Recipe;
import hi.feedme.feedme.models.SimplifiedRecipe;
import hi.feedme.feedme.databinding.FragmentSimplifiedRecipeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the SimplifiedRecipe list displayed on the HomeFragment
 *
 * This mostly provides a translation layer between the Java object and the xml view
 */
public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {

    private final List<SimplifiedRecipe> mValues;

    public RecipeRecyclerViewAdapter(List<SimplifiedRecipe> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentSimplifiedRecipeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SimplifiedRecipe sr = mValues.get(position);

        String r = "";
        double cr = sr.getRating();
        for (int i = 0; i < 5; i++) {
            r += i >= cr ? "☆" : "★"; // Placeholder, replace with rating view thingy
        }

        holder.mItem = sr;
        holder.mRatingView.setText(r);
        holder.mIdView.setText(sr.getName());
        holder.mContentView.setText(sr.getDescription());
        holder.mRealIdView.setText(sr.getId()+""); // Hidden element
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mRatingView;
        public final TextView mRealIdView;
        public SimplifiedRecipe mItem;

        public ViewHolder(FragmentSimplifiedRecipeBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            mRatingView = binding.rating;
            mRealIdView = binding.ID;

            View v = binding.getRoot();
            v.setOnClickListener(this);
        }

        /**
         * Hacky onClick for list recipes, performance would be better
         * if onClick were provided to a specific element in the
         * onBindViewHolder function, but that creates an inconsistent
         * user experience compared to making the entire list component clickable...
         *
         * @param view the View to bind the onClick function to
         */
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) view.getContext();
            Networking conn = activity.getNetwork();

            // Backend call
            conn.getRecipeById(mRealIdView.getText().toString(), new RecipeNwCallback() {
                @Override
                public void notifySuccess(Recipe response) throws JsonProcessingException {
                    // Before we load the RecipeFragment we fetch the recipe and bundle it
                    Bundle b = new Bundle();
                    b.putSerializable("recipe", response);
                    activity.getNavController().navigate(R.id.recipe, b);
                };

                @Override
                public void notifyError(VolleyError error) {
                    Toast.makeText((Context) activity, "Failed to fetch recipe!", Toast.LENGTH_SHORT).show();
                };
            });
        }

        @NonNull
        @Override
        public String toString() {
            return mItem.toString();
        }
    }
}