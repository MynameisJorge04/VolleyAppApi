package com.example.javavolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    EditText userText, bodyText, titleText;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userText = findViewById(R.id.userText);
        bodyText = findViewById(R.id.bodyText);
        titleText = findViewById(R.id.titleText);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> {
            //readWS();
            //createWS(titleText.getText().toString(), bodyText.getText().toString(), userText.getText().toString());
            //updateWS(titleText.getText().toString(), bodyText.getText().toString(), userText.getText().toString());
            deleteWS();
        });
    }

    private void createWS(String titleText, String bodyText, String userText) {
        String url = "https://jsonplaceholder.typicode.com/posts";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error.Response", error.getMessage());
            }
        }){
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userId", titleText);
                params.put("title", bodyText);
                params.put("body", userText);
                return params;
            }

        };

        Volley.newRequestQueue(this).add(postRequest);
    }

    private void updateWS(String titleText, String bodyText, String userText) {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        StringRequest postRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error.Response", error.getMessage());
            }
        }){
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userId", titleText);
                params.put("title", bodyText);
                params.put("body", userText);
                params.put("id", "1");
                return params;
            }

        };

        Volley.newRequestQueue(this).add(postRequest);
    }

    private void deleteWS() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        StringRequest postRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error.Response", error.getMessage());
            }
        });

        Volley.newRequestQueue(this).add(postRequest);
    }

    private void readWS() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    userText.setText(jsonObject.getString("userId"));
                    String title = jsonObject.getString("title");
                    titleText.setText(title);
                    bodyText.setText(jsonObject.getString("body"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error.Response", error.getMessage());
            }
        });

        Volley.newRequestQueue(this).add(postRequest);
    }
}