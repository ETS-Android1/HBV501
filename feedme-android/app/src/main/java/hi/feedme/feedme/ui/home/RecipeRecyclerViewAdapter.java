package hi.feedme.feedme.ui.home;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import hi.feedme.feedme.models.SimplifiedRecipe;
import hi.feedme.feedme.ui.home.RecipeContent.PlaceholderItem;
import hi.feedme.feedme.databinding.FragmentRecipeBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {

    private final List<SimplifiedRecipe> mValues;

    public RecipeRecyclerViewAdapter(List<SimplifiedRecipe> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentRecipeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String r = "";
        double cr = mValues.get(position).getRating();
        for (int i = 0; i < 5; i++) {
            r += i >= cr ? "☆" : "★";
        }


        holder.mItem = mValues.get(position);
        holder.mRatingView.setText(r);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mRatingView;
        public SimplifiedRecipe mItem;

        public ViewHolder(FragmentRecipeBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            mRatingView = binding.rating;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}