package hi.feedme.feedme.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;

import hi.feedme.feedme.MainActivity;
import hi.feedme.feedme.R;
import hi.feedme.feedme.databinding.FragmentLoginBinding;
import hi.feedme.feedme.listeners.LoginNwCallback;
import hi.feedme.feedme.listeners.RegisterNwCallback;
import hi.feedme.feedme.models.LoginInformation;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity act = (MainActivity) requireActivity();
        final EditText usernameEditText = binding.username;
        final EditText emailEditText = binding.email;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final TextView typeSwap = binding.prompt;
        final ProgressBar loadingProgressBar = binding.loading;

        typeSwap.setOnClickListener(v -> {
            if (emailEditText.getVisibility() == View.VISIBLE) {
                emailEditText.setVisibility(View.GONE);
                loginButton.setText(R.string.action_sign_in_short);
                typeSwap.setText(R.string.register_prompt);
            } else {
                emailEditText.setVisibility(View.VISIBLE);
                loginButton.setText(R.string.action_register_short);
                typeSwap.setText(R.string.login_prompt);
            }
        });

        loginButton.setOnClickListener(v -> {
            if (usernameEditText.length() != 0 && passwordEditText.length() != 0) {
                if (emailEditText.length() != 0 && loginButton.getText().equals(getResources().getString(R.string.action_register_short))) {
                    // Current setup is register
                    loadingProgressBar.setVisibility(View.VISIBLE);

                    act.getNetwork().postRegister(usernameEditText.getText().toString(), passwordEditText.getText().toString(), emailEditText.getText().toString(), false, new RegisterNwCallback() {
                        @Override
                        public void notifySuccess(String response) {
                            loadingProgressBar.setVisibility(View.GONE);
                            Toast.makeText(act, "Account created!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void notifyError(VolleyError error) {
                            loadingProgressBar.setVisibility(View.GONE);
                            Toast.makeText(act, "Failed to create account!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Current setup is login
                    loadingProgressBar.setVisibility(View.VISIBLE);

                    act.getNetwork().postLogin(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LoginNwCallback() {
                        @Override
                        public void notifySuccess(LoginInformation response) {
                            act.setActiveUser(response);
                            loadingProgressBar.setVisibility(View.GONE);
                            act.getNavController().navigate(R.id.navigation_home);
                        }

                        @Override
                        public void notifyError(VolleyError error) {
                            loadingProgressBar.setVisibility(View.GONE);
                            Toast.makeText(act, "Login failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                usernameEditText.setText("");
                emailEditText.setText("");
                passwordEditText.setText("");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}