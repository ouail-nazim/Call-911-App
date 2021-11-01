package com.example.learnapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.learnapplication.databinding.FragmentSecondBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendGetRequest();
            }
        });
        binding.buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPostRequest();
            }
        });
    }

    // use Volley library to send api request
    private void sendGetRequest() {
        // url for the request
        String url = "https://jsonplaceholder.typicode.com/todos";
        // create RequestQueue instance
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // create stringRequest instance
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            // get the response
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonarray = new JSONArray(response);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String id = jsonobject.getString("id");
                        String title = jsonobject.getString("title");
                        binding.textviewSecond.append(id + ":" + title + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    binding.textviewSecond.setText("Failed to Parse Json");
                }
            }
        }, new Response.ErrorListener() {
            // response fail
            @Override
            public void onErrorResponse(VolleyError error) {
                binding.textviewSecond.setText("Data : Response Failed");
            }
        });
        // send the request
        queue.add(stringRequest);
    }

    private void sendPostRequest() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    binding.textviewSecond.append("Posted Created \n");
                    JSONObject jsonObject = new JSONObject(response);
                    binding.textviewSecond.append("Title : " + jsonObject.getString("id") + "\n");
                    binding.textviewSecond.append("Title : " + jsonObject.getString("title") + "\n");
                    binding.textviewSecond.append("Body : " + jsonObject.getString("body") + "\n");

                } catch (Exception e) {
                    e.printStackTrace();
                    binding.textviewSecond.setText("POST DATA : unable to Parse Json");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                binding.textviewSecond.setText("Post Data : Response Failed");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("title", "just a title");
                params.put("body", "this is the body of the post");
                params.put("userId", "1");
                params.put("data_4_post", "Value 4 Data");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                //params.put("Authorization", "Bearer xxxxxxxxx... ");
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}