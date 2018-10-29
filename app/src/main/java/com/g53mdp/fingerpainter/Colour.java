package com.g53mdp.fingerpainter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.Arrays;

public class Colour extends AppCompatActivity {
    private int selectedColour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour);
        init();
    }

    private void init(){
        for (int i = 0; i < colourarr.length; i++){
            Button btn = (Button) findViewById(buttonarr[i]);
            btn.setBackgroundColor(colourarr[i]);
        }
    }

    public void onColourClick(View v){
        int index = Arrays.asList(buttonarr).indexOf(v.getId());
        selectedColour = colourarr[index];

    }

    public void onSetClick(){
        Intent in = getIntent();
        in.putExtra("colourcode", selectedColour);
        Log.d("Colour", String.valueOf(selectedColour));
    }

    public void onCancelClick() {
        finish();
    }

    private int buttonarr[] = {
            R.id.c0,
            R.id.c1,
            R.id.c2,
            R.id.c3,
            R.id.c4,
            R.id.c5,
            R.id.c6,
            R.id.c7,
            R.id.c8
    };

    private int colourarr[] = {
            Color.BLACK,
            Color.YELLOW,
            Color.BLUE,
            Color.RED,
            Color.WHITE,
            Color.GREEN,
            Color.LTGRAY,
            Color.CYAN,
            Color.MAGENTA
    };
}
