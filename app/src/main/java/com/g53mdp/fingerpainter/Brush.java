package com.g53mdp.fingerpainter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class Brush extends AppCompatActivity {

    private int selectedSize;
    private String selectedShape;
    private TextView progresstext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush);

        Bundle bundle = getIntent().getExtras();
        selectedSize = bundle.getInt("currentSize");

        progresstext = (TextView)findViewById(R.id.progresstext);

        SeekBar brushsize = (SeekBar)findViewById(R.id.seekBarSize);

        //set default value -- current size of brush
        brushsize.setProgress(selectedSize);
        progresstext.setText(selectedSize + "/" + brushsize.getMax());

        //set default button -- current shape of brush

        RadioGroup buttonGroup = (RadioGroup) findViewById(R.id.buttonGroup);
        String currentShape = bundle.getString("currentShape");

        selectedShape = currentShape;

        if (currentShape.equals("round")){
            buttonGroup.check(R.id.roundbutton);
        } else if (currentShape.equals("square")){
            buttonGroup.check(R.id.squarebutton);
        }


        brushsize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresstext.setText(progress + "/" + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                selectedSize = seekBar.getProgress();
            }
        });


    }

    public void onSetClick(View v){

        Intent in = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("selectedSize", selectedSize);
        bundle.putString("selectedShape", selectedShape);
        in.putExtras(bundle);
        setResult(Activity.RESULT_OK, in);
        finish();
    }

    public void onCancelClick(View v) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public void onShapeButtonClick(View v) {

        if (v.getId() == R.id.roundbutton){
            selectedShape = "round";
        } else if(v.getId() == R.id.squarebutton){
            selectedShape = "square";
        }

    }
}
