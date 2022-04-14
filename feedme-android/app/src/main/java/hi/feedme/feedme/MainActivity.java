package hi.feedme.feedme;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import hi.feedme.feedme.databinding.ActivityMainBinding;
import hi.feedme.feedme.logic.Networking;
import hi.feedme.feedme.logic.Storage;
import hi.feedme.feedme.models.IngredientInfo;
import hi.feedme.feedme.models.LoginInformation;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private Networking network;
    private Menu navMenu;
    private LoginInformation user = null;

    private void toggleDash(boolean b) {
        MenuItem dash = navMenu.getItem(1);
        dash.setVisible(b);
        dash.setEnabled(b);
    }

    private void removeActiveUser() {
        user = null;
        Storage.removeLoginInformation(this);
        toggleDash(false);
        navMenu.getItem(2).setTitle("Login");
    }

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

        navMenu = binding.navView.getMenu();

        try {
            user = Storage.getLoginInformation(this);
        } catch (Exception e) {
            toggleDash(false);
        }

        MenuItem home = navMenu.getItem(0);
        home.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                navController.navigate(R.id.navigation_home);

                return false;
            }
        });

        MenuItem login = navMenu.getItem(2);
        login.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (user == null) {
                    navController.navigate(R.id.navigation_login);
                } else {
                    removeActiveUser();
                }

                return true;
            }
        });
    }

    public Networking getNetwork() {
        return network;
    }

    public NavController getNavController() {
        return navController;
    }

    public LoginInformation getActiveUser() { return user; }

    public void setActiveUser(LoginInformation l) {
        user = l;
        toggleDash(true);
        navMenu.getItem(2).setTitle("Logout");
    }
}