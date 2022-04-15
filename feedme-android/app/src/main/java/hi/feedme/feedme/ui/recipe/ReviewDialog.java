package hi.feedme.feedme.ui.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import hi.feedme.feedme.R;
import hi.feedme.feedme.models.Recipe;

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
            System.out.println(reviewRecipe.getName());
            dismiss();
        });
    }
}