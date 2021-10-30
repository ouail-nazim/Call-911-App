package com.example.learnapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.learnapplication.databinding.FragmentFirstBinding;

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
                    if (!authAttempt(email, password)) {
                        alert.setMessage(R.string.invalide_login_data)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                })
                                .show();
                    } else {
                        makeLogin();
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

    private void navigateTo(@IdRes int resId) {
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(resId);
    }

    private void makeLogin() {
        //TODO:create login, not just redirect to next page
        navigateTo(R.id.action_FirstFragment_to_SecondFragment);
    }

    private boolean authAttempt(String email, String password) {
        if (email.equals("admin") || password.equals("admin")) {
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}