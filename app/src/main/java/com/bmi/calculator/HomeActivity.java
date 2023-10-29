package com.bmi.calculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        EditText weight = findViewById(R.id.wt);
        EditText height = findViewById(R.id.ht);
        TextView res = findViewById(R.id.textView1);
        TextView cat = findViewById(R.id.category);
        final Button calcBtn = findViewById(R.id.calc);

        calcBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(weight.getText().toString()) && TextUtils.isEmpty(height.getText().toString())){
                cat.setVisibility(View.INVISIBLE);
                res.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Please Fill All The Fields...", Toast.LENGTH_SHORT).show();
            } else if (Float.parseFloat(height.getText().toString()) == 0.0) {
                cat.setVisibility(View.INVISIBLE);
                res.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Height Cannot Be Zero...", Toast.LENGTH_SHORT).show();
            } else {
                float w = Float.parseFloat(weight.getText().toString());
                float h = Float.parseFloat(height.getText().toString());
                float h_sq = h * h;
                float bmi = w/h_sq;
                String resText = "BMI = " + bmi;
                res.setVisibility(View.VISIBLE);
                res.setText(resText);
                String catText = "";
                if (bmi < 18.5)
                    catText = "Category: Underweight";
                else if (bmi >= 18.5 && bmi < 24.9)
                    catText = "Category: Normal Weight";
                else if (bmi >= 25 && bmi < 29.9)
                    catText = "Category: Overweight";
                else if (bmi >= 30)
                    catText = "Category: Obese";
                cat.setVisibility(View.VISIBLE);
                cat.setText(catText);
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finishAffinity();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}