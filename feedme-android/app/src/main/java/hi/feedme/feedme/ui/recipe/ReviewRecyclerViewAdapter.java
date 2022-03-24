package hi.feedme.feedme.ui.recipe;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import hi.feedme.feedme.databinding.FragmentReviewBinding;
import hi.feedme.feedme.models.Review;

/**
 * Adapter for the Review list displayed on the RecipeFragment
 *
 * This mostly provides a translation layer between the Java object and the xml view
 */
public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.ViewHolder> {
    private final List<Review> mValues;

    public ReviewRecyclerViewAdapter(List<Review> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentReviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Review r = mValues.get(position);

        String postedBy = "Posted by: " + r.getUsername();
        String dateString;

        try {
            dateString = new SimpleDateFormat("dd MMMM yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(r.getDate()));
        } catch (Exception e) {
           dateString = r.getDate();
        }


        holder.mItem = r;
        holder.mReviewTitle.setText(r.getTitle());
        holder.mReviewOwner.setText(postedBy);
        holder.mReviewSubTitle.setText(r.getSubtitle());
        holder.mReviewRating.setRating((float) r.getRating());
        holder.mReviewDate.setText(dateString);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mReviewTitle;
        public final TextView mReviewOwner;
        public final TextView mReviewSubTitle;
        public final RatingBar mReviewRating;
        public final TextView mReviewDate;

        public Review mItem;

        public ViewHolder(FragmentReviewBinding binding) {
            super(binding.getRoot());

            mReviewTitle = binding.reviewTitle;
            mReviewOwner = binding.reviewOwner;
            mReviewSubTitle = binding.reviewSubtitle;
            mReviewRating = binding.reviewRating;
            mReviewDate = binding.reviewDate;
        }

        @NonNull
        @Override
        public String toString() {
            return mItem.toString();
        }
    }
}