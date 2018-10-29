package com.g53mdp.fingerpainter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int selectedColour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBrushClick(View view) {
    }

    public void onColourClick(View view) {
        Intent colour = new Intent(getApplicationContext(), Colour.class);
        Bundle extras = new Bundle();
        colour.putExtras(extras);
        startActivity(colour);


    }
}
