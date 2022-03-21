package hi.feedme.feedme.ui.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import hi.feedme.feedme.models.SimplifiedRecipe;
import hi.feedme.feedme.databinding.FragmentSimplifiedRecipeBinding;

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
        String r = "";
        double cr = mValues.get(position).getRating();
        for (int i = 0; i < 5; i++) {
            r += i >= cr ? "☆" : "★"; // Placeholder, replace with rating view thingy
        }

        holder.mItem = mValues.get(position);
        holder.mRatingView.setText(r);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getDescription());
        holder.mRealIdView.setText(mValues.get(position).getId()+""); // Hidden element
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}