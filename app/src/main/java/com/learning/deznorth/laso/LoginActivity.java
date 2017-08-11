package com.learning.deznorth.laso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            if(SharedPrefManager.getInstance(this).getUserRole()<1) {
                finish();
                startActivity(new Intent(this, ProfileActivity.class));
                return;
            }else if(SharedPrefManager.getInstance(this).getUserRole()>0) {
                finish();
                startActivity(new Intent(this, EboardProfileActivity.class));
                return;
            }
        }

        editTextUsername = (EditText) findViewById(R.id.usernameEditText);
        editTextPassword = (EditText) findViewById(R.id.passwordEditText);

    }

    public void onNoAccount(View view){
        Intent registerScreen = new Intent(this, RegisterActivity.class);
        startActivity(registerScreen);
    }

    public void onLogin(View view){

        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error")){
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(obj.getInt("id"), obj.getString("username"),
                                obj.getString("email"), obj.getString("fName"), obj.getString("lName"), obj.getInt("role"));

                        //Toast.makeText(getApplicationContext(),obj.getInt("role"),Toast.LENGTH_LONG).show();
                        if(obj.getInt("role")<1) {
                            changeActivity(1);
                        } else if(obj.getInt("role")>0) {
                            changeActivity(2);
                        }


                    }else{
                        Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_LONG).show();
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("pass", password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void changeActivity(int a){
        switch(a){
            case 1:
                finish();
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                break;
            case 2:
                finish();
                startActivity(new Intent(getApplicationContext(), EboardProfileActivity.class));
                break;
        }
    }

}
