package com.example.learnapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learnapplication.databinding.FragmentFireListBinding;

public class FireListFragment extends Fragment {

    private FragmentFireListBinding binding;
    ListView fireList;

    String mTitle[] = {
            "Bousbaa Moussa,Didouche Mourad,Constantine",
            "UV15 nouvell ville,El khroub ,Constantine",
            "Sidi Mabrouk,Constantine",
    };
    String mDescription[] = {
            "By: @ouail_nazim (0540037061)",
            "By: @kira_oussama (0554795175)",
            "By: @lina_djaalb (07503461)",
    };
    int status[] = {1,2,3};

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFireListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fireList = binding.fireList;

        ListAdapter adapter = new ListAdapter(getContext(), mTitle,status, mDescription);
        fireList.setAdapter(adapter);

        fireList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Show Fire Num : "+ position+1, Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}