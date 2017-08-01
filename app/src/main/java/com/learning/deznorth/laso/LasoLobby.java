package com.learning.deznorth.laso;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LasoLobby extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout websiteLink,eventsLink,loginLink;
    private TextView loginText;
    private ImageView loginImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laso_lobby);

        websiteLink = (LinearLayout) findViewById(R.id.websiteLinkLayout);
        eventsLink = (LinearLayout) findViewById(R.id.eventsLinkLayout);
        loginLink = (LinearLayout) findViewById(R.id.loginLayout);

        websiteLink.setOnClickListener(this);
        eventsLink.setOnClickListener(this);
        loginLink.setOnClickListener(this);

        loginText = loginLink.findViewById(R.id.loginTextView);
        loginImage = loginLink.findViewById(R.id.loginImageView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            loginText.setText(R.string.profileButton);
            loginImage.setImageResource(R.drawable.ic_face_white_48dp);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == websiteLink){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.LASO_URL)));
        }else if(view == eventsLink){
            startActivity(new Intent(this, EventsActivity.class));
        }else if(view == loginLink){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
