package hi.feedme.feedme.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;

import java.util.List;

import hi.feedme.feedme.MainActivity;
import hi.feedme.feedme.R;
import hi.feedme.feedme.databinding.FragmentSimplifiedRecipeBinding;
import hi.feedme.feedme.listeners.RecipeNwCallback;
import hi.feedme.feedme.logic.Networking;
import hi.feedme.feedme.models.Recipe;
import hi.feedme.feedme.models.SimplifiedRecipe;

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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentSimplifiedRecipeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    @SuppressLint("SetTextI18n")
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        SimplifiedRecipe sr = mValues.get(position);

        holder.mItem = sr;
        holder.mTitleView.setText(sr.getName());
        holder.mRatingView.setRating((float) sr.getRating());
        holder.mDescriptionView.setText(sr.getDescription());
        holder.mHiddenIdView.setText(sr.getId()+""); // Hidden element
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public SimplifiedRecipe mItem;
        public final TextView mTitleView;
        public final RatingBar mRatingView;
        public final TextView mDescriptionView;
        public final TextView mHiddenIdView;

        public ViewHolder(FragmentSimplifiedRecipeBinding binding) {
            super(binding.getRoot());
            mTitleView = binding.simpleTitle;
            mRatingView = binding.simpleRating;
            mDescriptionView = binding.simpleDescription;
            mHiddenIdView = binding.simpleHiddenId;

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
            conn.getRecipeById(mHiddenIdView.getText().toString(), new RecipeNwCallback() {
                @Override
                public void notifySuccess(Recipe response) {
                    // Before we load the RecipeFragment we fetch the recipe and bundle it
                    Bundle b = new Bundle();
                    b.putSerializable("recipe", response);
                    activity.getNavController().navigate(R.id.recipe, b);
                }

                @Override
                public void notifyError(VolleyError error) {
                    Toast.makeText((Context) activity, "Failed to fetch recipe!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @NonNull
        @Override
        public String toString() {
            return mItem.toString();
        }
    }
}