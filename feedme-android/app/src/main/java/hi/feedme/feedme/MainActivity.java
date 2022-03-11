package hi.feedme.feedme;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONObject;

import hi.feedme.feedme.databinding.ActivityMainBinding;
import hi.feedme.feedme.logic.NetworkCallback;
import hi.feedme.feedme.logic.Networking;
import hi.feedme.feedme.models.Recipe;

import hi.feedme.feedme.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;
    private Networking network;
    private NetworkCallback callBackListener;
    private Context context;
    private Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initCallBack();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_login)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        network = new Networking(callBackListener, context);
    }

    private void initCallBack() {

        callBackListener = new NetworkCallback() {
            @Override
            public void notifySuccess(JSONObject response) throws JsonProcessingException {
                System.out.println("Great success! " + response.toString());
                recipe = new ObjectMapper().readValue(response.toString(), Recipe.class);

                NavHostFragment nhf = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
                ((HomeFragment) nhf.getChildFragmentManager().getFragments().get(0)).setData();

                System.out.println("xd");
            }

            @Override
            public void notifyError(VolleyError error) {
                System.out.println("Failure! " + error.toString());
            }
        };
    }

    public Networking getNetwork() { return network; }

}