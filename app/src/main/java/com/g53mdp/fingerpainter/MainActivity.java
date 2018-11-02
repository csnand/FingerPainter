package com.g53mdp.fingerpainter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int selectedColour;
    private int selectedSize;
    private String selectedShape;
    private FingerPainterView myFingerPainterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFingerPainterView = (FingerPainterView)findViewById(R.id.fingerpainterview);
        myFingerPainterView.load(getIntent().getData());
    }

    static final int ACTIVITY_COLOUR_REQUEST_CODE = 1;
    public void onColourClick(View view) {
        Intent colour = new Intent(getApplicationContext(), Colour.class);
        Bundle extras = new Bundle();
        colour.putExtras(extras);
        startActivityForResult(colour, ACTIVITY_COLOUR_REQUEST_CODE);
    }


    static final int ACTIVITY_BRUSH_REQUEST_CODE = 2;
    public void onBrushClick(View view) {

        Intent brush = new Intent(getApplicationContext(), Brush.class);
        Bundle extras = new Bundle();
        extras.putInt("currentSize", myFingerPainterView.getBrushWidth());
        brush.putExtras(extras);
        startActivityForResult(brush, ACTIVITY_BRUSH_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ACTIVITY_COLOUR_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                selectedColour = bundle.getInt("selectedColour");
                myFingerPainterView.setColour(selectedColour);
            }
        } else if (requestCode == ACTIVITY_BRUSH_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                selectedSize = bundle.getInt("selectedSize");
                selectedShape = bundle.getString("selectedShape");
                Log.d("selected size", selectedSize+"");
                Log.d("selected shape", selectedShape);

                if (selectedShape == "round" || selectedShape == ""){
                    myFingerPainterView.setBrush(Paint.Cap.ROUND);
                } else if(selectedShape == "square"){
                    myFingerPainterView.setBrush(Paint.Cap.SQUARE);
                }


                myFingerPainterView.setBrushWidth(selectedSize);

            }
        }
    }




}
