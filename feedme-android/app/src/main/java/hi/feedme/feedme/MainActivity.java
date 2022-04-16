package hi.feedme.feedme;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.VolleyError;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Collections;

import hi.feedme.feedme.databinding.ActivityMainBinding;
import hi.feedme.feedme.listeners.RecipeListNwCallback;
import hi.feedme.feedme.logic.Networking;
import hi.feedme.feedme.logic.Storage;
import hi.feedme.feedme.models.LoginInformation;
import hi.feedme.feedme.models.SimplifiedRecipe;
import hi.feedme.feedme.ui.dashboard.FavoritesContent;

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
        FavoritesContent.items.clear();
        navMenu.getItem(2).setTitle("Login");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = this;
        network = new Networking(context);

        hi.feedme.feedme.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_login, R.id.recipe)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navMenu = binding.navView.getMenu();

        try {
            user = Storage.getLoginInformation(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (null != user && null != user.getUser()) {
            navMenu.getItem(2).setTitle("Logout");
        } else {
            toggleDash(false);
        }

        MenuItem home = navMenu.getItem(0);
        home.setOnMenuItemClickListener(menuItem -> {
            navController.navigate(R.id.navigation_home);

            return true;
        });

        MenuItem favorites = navMenu.getItem(1);
        favorites.setOnMenuItemClickListener(menuItem -> {
            network.getFavoriteRecipes(new RecipeListNwCallback() {
                @Override
                public void notifySuccess(ArrayList<SimplifiedRecipe> response) {
                    FavoritesContent.items.clear();

                    for (SimplifiedRecipe r : response) {
                        FavoritesContent.items.add(r);
                        FavoritesContent.itemMap.put(r.getId(), r);
                    }

                    // Force sort by rating, descending
                    Collections.sort(FavoritesContent.items, (r1, r2) -> Double.compare(r2.getRating(), r1.getRating()));
                    navController.navigate(R.id.navigation_dashboard);
                }

                @Override
                public void notifyError(VolleyError error) {
                    Toast.makeText(context, "Failed to fetch favorites!", Toast.LENGTH_SHORT).show();
                }
            });

            return true;
        });

        MenuItem login = navMenu.getItem(2);
        login.setOnMenuItemClickListener(menuItem -> {
            if (user == null || user.getUser() == null) {
                navController.navigate(R.id.navigation_login);
            } else {
                removeActiveUser();
                navController.navigate(R.id.navigation_home,null, new NavOptions.Builder().setPopUpTo((navController.getGraph().getStartDestinationId()), true).build());
            }

            return true;
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
        navController.navigate(R.id.navigation_home,null, new NavOptions.Builder().setPopUpTo((navController.getGraph().getStartDestinationId()), true).build());
    }
}