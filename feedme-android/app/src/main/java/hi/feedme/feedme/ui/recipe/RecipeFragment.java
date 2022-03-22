package hi.feedme.feedme.ui.recipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hi.feedme.feedme.R;
import hi.feedme.feedme.models.Recipe;

public class RecipeFragment extends Fragment {
    private Recipe shownRecipe;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();

        if (b != null) {
            shownRecipe = (Recipe) b.getSerializable("recipe");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println(shownRecipe.getName());

        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }
}