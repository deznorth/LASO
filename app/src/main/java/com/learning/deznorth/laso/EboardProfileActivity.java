package com.learning.deznorth.laso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
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

public class EboardProfileActivity extends AppCompatActivity {

    private TextView textViewUsername, textViewName, textViewEmail, textViewRole;
    private String mRoleTitle, mRoleDescription;
    private int mRolePower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eboard_profile);
        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        textViewUsername = (TextView) findViewById(R.id.usernameLabel);
        textViewName = (TextView) findViewById(R.id.nameText);
        textViewEmail = (TextView) findViewById(R.id.emailText);
        textViewRole = (TextView) findViewById(R.id.roleText);

        textViewUsername.setText(SharedPrefManager.getInstance(this).getUserName());
        textViewName.setText(SharedPrefManager.getInstance(this).getName());
        textViewEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        textViewRole.setText(String.valueOf(SharedPrefManager.getInstance(this).getUserRole()));



    }

    @Override
    protected void onStart() {
        super.onStart();
        getRole();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LasoLobby.class));
                break;
        }
        return true;
    }

    private void getRole(){

        final String userRoleId = String.valueOf(SharedPrefManager.getInstance(this).getUserRole());

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, Constants.URL_ROLES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error")){
                        mRoleTitle = obj.getString("title");
                        mRolePower = obj.getInt("power");
                        mRoleDescription = obj.getString("description");
                        //Toast.makeText(getApplicationContext(),String.valueOf(mRolePower),Toast.LENGTH_LONG).show();
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
                params.put("roleId", userRoleId);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
