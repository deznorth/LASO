package com.learning.deznorth.laso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewUsername, textViewName, textViewEmail, textViewRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
}
