package hi.feedme.feedme.ui.recipe;

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
import hi.feedme.feedme.listeners.RecipeNwCallback;
import hi.feedme.feedme.logic.Networking;
import hi.feedme.feedme.models.Recipe;
import hi.feedme.feedme.models.Review;
import hi.feedme.feedme.models.SimplifiedRecipe;
import hi.feedme.feedme.databinding.FragmentReviewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the SimplifiedRecipe list displayed on the HomeFragment
 *
 * This mostly provides a translation layer between the Java object and the xml view
 */
public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<hi.feedme.feedme.ui.recipe.ReviewRecyclerViewAdapter.ViewHolder> {

    private final List<Review> mValues;

    public ReviewRecyclerViewAdapter(List<Review> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentReviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Review r = mValues.get(position);

        holder.mItem = r;
        holder.mReviewText.setText(r.getTitle());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mReviewText;
        public Review mItem;

        public ViewHolder(FragmentReviewBinding binding) {
            super(binding.getRoot());

            mReviewText = binding.reviewText;
        }

        @NonNull
        @Override
        public String toString() {
            return mItem.toString();
        }
    }
}