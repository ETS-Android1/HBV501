package hi.feedme.feedme;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import hi.feedme.feedme.databinding.ActivityMainBinding;
import hi.feedme.feedme.listeners.IngredientListNwCallback;
import hi.feedme.feedme.logic.Networking;
import hi.feedme.feedme.models.IngredientInfo;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private Networking network;
    private Context context;
    private ArrayList<IngredientInfo> appIngredients;

    public void refreshIngredients() { // Because of Heroku delays we provide access to this
        network.getIngredients(new IngredientListNwCallback() {
            @Override
            public void notifySuccess(ArrayList<IngredientInfo> response) throws JsonProcessingException {
                appIngredients = response;
            }

            @Override
            public void notifyError(VolleyError error) {
                Toast.makeText(context, "Heroku cold start...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        network = new Networking(context);

        refreshIngredients();

        hi.feedme.feedme.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_login, R.id.recipe)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public Networking getNetwork() { return network; }

    public NavController getNavController() { return navController; }


    public ArrayList<IngredientInfo> getIngredients() { return appIngredients; }
}