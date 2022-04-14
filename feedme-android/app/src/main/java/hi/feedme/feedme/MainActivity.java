package hi.feedme.feedme;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import hi.feedme.feedme.databinding.ActivityMainBinding;
import hi.feedme.feedme.logic.Networking;
import hi.feedme.feedme.logic.Storage;
import hi.feedme.feedme.models.IngredientInfo;
import hi.feedme.feedme.models.LoginInformation;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private Networking network;
    private LoginInformation user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = this;
        network = new Networking(context);

        hi.feedme.feedme.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_login, R.id.recipe)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        try {
            user = Storage.getLoginInformation(this);
        } catch (JsonProcessingException e) {
            // Ignore, leave null
        }

        if (user != null) System.out.println(user.getUser().getEmail());
    }

    public Networking getNetwork() {
        return network;
    }

    public NavController getNavController() {
        return navController;
    }

    public LoginInformation getActiveUser() { return user; }

    public void setActiveUser(LoginInformation l) { user = l; }
}