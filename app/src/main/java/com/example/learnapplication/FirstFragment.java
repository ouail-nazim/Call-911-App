package com.example.learnapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.learnapplication.databinding.FragmentFirstBinding;

import java.util.UUID;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        super.onViewCreated(view, savedInstanceState);
        // check if user is logged in
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();

        String token = preferences.getString("Auth-Token", "");
        if (!token.equalsIgnoreCase("")) {
            navigateTo(R.id.action_FirstFragment_to_SecondFragment);
        } else {
            // on click sur login
            binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = binding.inputEmail.getText().toString();
                    String password = binding.inputPassword.getText().toString();
                    if (email.equals("") || password.equals("")) {
                        alert.setMessage(R.string.empty_data)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                })
                                .show();
                    } else {
                        String newToken = authAttempt(email, password);
                        if (newToken.equalsIgnoreCase("")) {
                            alert.setMessage(R.string.invalide_login_data)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                        }
                                    })
                                    .show();
                        } else {
                            makeLogin(newToken);
                        }

                    }
                }
            });

            binding.forgotpass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alert.setMessage(R.string.comming_soon).show();
                }
            });

            binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navigateTo(R.id.action_to_RegisterFragment);
                }
            });
        }
    }

    private void navigateTo(@IdRes int resId) {
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(resId);
    }

    private void makeLogin(String token) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Auth-Token", token);
        editor.apply();

        navigateTo(R.id.action_to_HomeFragment);
    }

    private String authAttempt(String email, String password) {
        //TODO: make api call to make login and return the token
        String token = "Bearer " + UUID.randomUUID().toString();
        if (email.equals("admin") || password.equals("admin")) {
            return token;
        }
        return "";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}