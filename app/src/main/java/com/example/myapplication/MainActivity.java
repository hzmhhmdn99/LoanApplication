package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;

    EditText editName, editLoan, editDuration;
    TextView result;
    Button buttonSubmit, buttonReset;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        editName  = (EditText) findViewById(R.id.editName);
        editLoan = (EditText) findViewById(R.id.editLoan);
        editDuration = (EditText) findViewById(R.id.editDuration);
        result = (TextView) findViewById(R.id.showResult);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonReset = (Button) findViewById(R.id.buttonReset);

    }

    public void showResult(View v) {


        isAllFieldsChecked = CheckAllFields();

        if (isAllFieldsChecked) {
            double loan = Double.parseDouble(editLoan.getText().toString());
            int duration =Integer.parseInt(editDuration.getText().toString());
            double intValue = loan * 0.03;
            double yearly = (intValue + loan) / duration;

            String msg = "Yearly is " + yearly;
            result.setText(msg);
            // result.setText(name + ", Don't let your assumptions hold you back from what could be. Think big.");
        }

    }

    public void reset(View v) {
        editName.setText("");
        editLoan.setText("");
        editDuration.setText("");
        result.setText("");
        editName.requestFocus();
    }


    private boolean CheckAllFields() {
        if (editName.length() == 0) {
            editName.setError("This field is required");
            return false;
        }

        if (editLoan.length() == 0) {
            editLoan.setError("This field is required");
            return false;
        }

        if (editDuration.length() == 0) {
            editDuration.setError("This field is required");
            return false;
        }

        // after all validation return true.
        return true;
    }

}