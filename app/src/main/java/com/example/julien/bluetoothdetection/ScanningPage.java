package com.example.julien.bluetoothdetection;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.color.black;
import static android.R.color.holo_orange_light;
import static android.R.color.holo_red_dark;
import static android.R.color.white;

public class ScanningPage extends AppCompatActivity {

    TextView AccelStatic;
    TextView AccelValue; //2m/s2
    TextView GyroStatic;
    TextView GyroValue; //0.8rad/s2
    TextView AlertText;
    Button AlertTrigger;
    Button AlertReset;

    ConstraintLayout Background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_page);

        AccelValue = (TextView) findViewById(R.id.AccelValue);
        GyroValue = (TextView) findViewById(R.id.GyroValue);

        AccelStatic = (TextView) findViewById(R.id.AccelStatic);
        GyroStatic = (TextView) findViewById(R.id.GyroStatic);

        AlertText = (TextView) findViewById(R.id.alertText);
        AlertReset = (Button) findViewById(R.id.alertReset);


        SpannableString DaAccel = new SpannableString("2.11675 m/s2");
        DaAccel.setSpan(new SuperscriptSpan(), 11, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AccelValue.setText(DaAccel);

        SpannableString DaGyro = new SpannableString("0.812576 rad/s2");
        DaGyro.setSpan(new SuperscriptSpan(), 14, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        GyroValue.setText(DaGyro);

        AccelStatic.setOnLongClickListener(new TextView.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                alertMode(v);
                return true;
            }

        });

        GyroStatic.setOnLongClickListener(new TextView.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                warningPPG(v);
                return true;
            }

        });
    }

    public void alertMode (View v){
        Background = (ConstraintLayout) findViewById(R.id.scanningLayout);
        Background.setBackgroundColor(getResources().getColor(holo_red_dark));

        AccelStatic.setTextColor(getResources().getColor(white));
        AccelValue.setTextColor(getResources().getColor(white));
        SpannableString DaAccel = new SpannableString("21.7212 m/s2");
        DaAccel.setSpan(new SuperscriptSpan(), 11, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AccelValue.setText(DaAccel);
        GyroStatic.setTextColor(getResources().getColor(white));
        GyroValue.setTextColor(getResources().getColor(white));
        AlertText.setTextColor(getResources().getColor(white));

        AlertText.setVisibility(View.VISIBLE);
        AlertReset.setVisibility(View.VISIBLE);
    }

    public void reset (View v){
        Background = (ConstraintLayout) findViewById(R.id.scanningLayout);
        Background.setBackgroundColor(getResources().getColor(white));

        AccelStatic.setTextColor(getResources().getColor(black));
        AccelValue.setTextColor(getResources().getColor(black));
        SpannableString DaAccel = new SpannableString("2.11675 m/s2");
        DaAccel.setSpan(new SuperscriptSpan(), 11, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AccelValue.setText(DaAccel);
        GyroStatic.setTextColor(getResources().getColor(black));
        GyroValue.setTextColor(getResources().getColor(black));
        AlertText.setTextColor(getResources().getColor(black));

        AlertText.setVisibility(View.INVISIBLE);
        AlertReset.setVisibility(View.INVISIBLE);
    }

    public void warningPPG (View v){
        Background = (ConstraintLayout) findViewById(R.id.scanningLayout);
        Background.setBackgroundColor(getResources().getColor(holo_orange_light));

        AccelStatic.setTextColor(getResources().getColor(black));
        AccelValue.setTextColor(getResources().getColor(black));
        SpannableString DaAccel = new SpannableString("0.00000 m/s2");
        DaAccel.setSpan(new SuperscriptSpan(), 11, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AccelValue.setText(DaAccel);

        SpannableString DaGyro = new SpannableString("0.00000 rad/s2");
        DaGyro.setSpan(new SuperscriptSpan(), 13, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        GyroValue.setText(DaGyro);

        GyroStatic.setTextColor(getResources().getColor(black));
        GyroValue.setTextColor(getResources().getColor(black));
        AlertText.setTextColor(getResources().getColor(black));

        AlertReset.setText("Try Again");

        AlertText.setText("WARNING: PPG not connected");
        AlertText.setVisibility(View.VISIBLE);
        AlertReset.setVisibility(View.VISIBLE);
    }



























}
