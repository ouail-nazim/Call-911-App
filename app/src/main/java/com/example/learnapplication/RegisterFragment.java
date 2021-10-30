package com.example.learnapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.learnapplication.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        super.onViewCreated(view, savedInstanceState);
        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the new data
                String firstName = binding.inputFirstName.getText().toString();
                String lastName = binding.inputLastName.getText().toString();
                String phone = binding.inputPhoneNumber.getText().toString();
                String email = binding.inputEmail.getText().toString();
                String password = binding.inputPassword.getText().toString();
                // try to create new account
                boolean registered = register(firstName, lastName, phone, email, password);
                // redirect or show error msg
                if (registered) {
                    NavHostFragment.findNavController(RegisterFragment.this).navigate(R.id.action_registerd);
                }else{
                    alert.setMessage("Registration Failed")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .show();
                }

            }
        });

    }
    private boolean register(String firstName, String lastName, String phone, String email, String password) {
        //TODO: send api request to register new user in bd and return the response
        //TODO: save the user token to use it later in api calls
        return false;
    }
}