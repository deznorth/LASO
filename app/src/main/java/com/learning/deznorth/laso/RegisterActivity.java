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

public class RegisterActivity extends AppCompatActivity {

    private EditText fName, lName, user, pass, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }

        fName = (EditText) findViewById(R.id.fNameEditText);
        lName = (EditText) findViewById(R.id.lNameEditText);
        user = (EditText) findViewById(R.id.usernameEditText);
        pass = (EditText) findViewById(R.id.passwordEditText);
        email = (EditText) findViewById(R.id.emailEditText);
    }

    public void onJoin(View view){
        Intent screen1 = new Intent(this, LoginActivity.class);
        final String fn = fName.getText().toString().trim();
        final String ln = lName.getText().toString().trim();
        final String u = user.getText().toString().trim();
        final String p = pass.getText().toString().trim();
        final String e = email.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstName", fn);
                params.put("lastName", ln);
                params.put("username", u);
                params.put("pass", p);
                params.put("email", e);


                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        startActivity(screen1);
    }
}
