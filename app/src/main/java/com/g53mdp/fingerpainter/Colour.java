package com.g53mdp.fingerpainter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.Arrays;
import java.util.stream.IntStream;

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

        //using IntStream to find the index of anb element in an array
        int index = IntStream.range(0, buttonarr.length)
                .filter(i -> v.getId() == buttonarr[i])
                .findFirst().orElse(-1);
        if (index == -1){
            return;
        }

        selectedColour = colourarr[index];

    }

    public void onSetClick(View v){
        Intent in = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("selectedColour", selectedColour);
        in.putExtras(bundle);
        setResult(Activity.RESULT_OK, in);
        finish();

    }

    public void onCancelClick(View v) {
        setResult(Activity.RESULT_CANCELED);
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
