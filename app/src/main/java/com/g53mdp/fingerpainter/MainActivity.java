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

        //set default values
        selectedColour = Color.BLACK;
        selectedShape = "round";
        selectedSize = 20;
    }

    static final int ACTIVITY_COLOUR_REQUEST_CODE = 1;
    public void onColourClick(View view) {
        Intent colour = new Intent(getApplicationContext(), Colour.class);
        Bundle extras = new Bundle();
        int currentColour = myFingerPainterView.getColour();
        extras.putInt("currentColour", currentColour);
        colour.putExtras(extras);
        startActivityForResult(colour, ACTIVITY_COLOUR_REQUEST_CODE);
    }


    static final int ACTIVITY_BRUSH_REQUEST_CODE = 2;
    public void onBrushClick(View view) {

        Intent brush = new Intent(getApplicationContext(), Brush.class);
        Bundle extras = new Bundle();
        extras.putInt("currentSize", myFingerPainterView.getBrushWidth());

        if (myFingerPainterView.getBrush() == Paint.Cap.ROUND){
            extras.putString("currentShape", "round");
        } else if (myFingerPainterView.getBrush() == Paint.Cap.SQUARE){
            extras.putString("currentShape", "square");
        }

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


                if (selectedShape.equals("round") || selectedShape.equals("")){
                    myFingerPainterView.setBrush(Paint.Cap.ROUND);
                    Log.d("selected shape", "round");
                } else if(selectedShape.equals("square")){
                    myFingerPainterView.setBrush(Paint.Cap.SQUARE);
                    Log.d("selected shape", "square");
                }




                myFingerPainterView.setBrushWidth(selectedSize);

            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("selectedSize", selectedSize);
        outState.putInt("selectedColour", selectedColour);
        outState.putString("selectedShape", selectedShape);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        selectedSize = savedInstanceState.getInt("selectedSize");
        selectedColour = savedInstanceState.getInt("selectedColour");
        selectedShape = savedInstanceState.getString("selectedShape");

        myFingerPainterView.setColour(selectedColour);
        myFingerPainterView.setBrushWidth(selectedSize);

        if (selectedShape.equals("round")){
            myFingerPainterView.setBrush(Paint.Cap.ROUND);
        } else if (selectedShape.equals("square")){
            myFingerPainterView.setBrush(Paint.Cap.SQUARE);
        }

    }
}
