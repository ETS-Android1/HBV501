package hi.feedme.feedme.ui.recipe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import hi.feedme.feedme.MainActivity;
import hi.feedme.feedme.R;
import hi.feedme.feedme.models.Recipe;
import hi.feedme.feedme.models.Review;

public class ReviewDialog extends DialogFragment {
    Recipe reviewRecipe;

    public ReviewDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static ReviewDialog newInstance(Recipe r) {
        ReviewDialog d = new ReviewDialog();
        Bundle b = new Bundle();
        b.putSerializable("recipe", r);
        d.setArguments(b);

        return d;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_review_dialog, container);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle b = getArguments();

        if (b != null) {
            reviewRecipe = (Recipe) b.getSerializable("recipe");
        }

        // Placeholder, would send post request here
        Button send = view.findViewById(R.id.review_button);
        send.setOnClickListener(v -> {
            String title = ((EditText) view.findViewById(R.id.title)).getText().toString();
            String subTitle = ((EditText) view.findViewById(R.id.subtitle)).getText().toString();
            // Steps are integers so we can truncate
            int rating = (int) ((RatingBar) view.findViewById(R.id.rating)).getRating();

            MainActivity act = (MainActivity) requireActivity();
            act.getNetwork().postReview(title, subTitle, rating, "" + reviewRecipe.getId());

            Review nr = new Review();
            nr.setUser_id(act.getActiveUser().getUser().getId());
            nr.setUsername(act.getActiveUser().getUser().getUsername());
            nr.setTitle(title);
            nr.setSubtitle(subTitle);
            nr.setRating(rating);

            Date curr = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            nr.setDate(df.format(curr));
            reviewRecipe.getReviews().add(nr);

            Bundle refresh = new Bundle();
            refresh.putSerializable("recipe", reviewRecipe);

            dismiss();
            act.reload(refresh);
        });
    }
}