package com.bmi.calculator;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
        EditText feet = findViewById(R.id.ht);
        EditText inches = findViewById(R.id.ht2);
        TextView res = findViewById(R.id.textView1);
        TextView cat = findViewById(R.id.category);
        LinearLayout wt_linear = findViewById(R.id.kg_linear);
        LinearLayout ht_linear = findViewById(R.id.ht_linear);
        final Button calcBtn = findViewById(R.id.calc);

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.parseColor("#81D1C7AE"));
        gd.setCornerRadius(100);
        wt_linear.setBackground(gd);
        ht_linear.setBackground(gd);

        GradientDrawable gd2 = new GradientDrawable();
        gd2.setColor(Color.parseColor("#65CDBA8D"));
        gd2.setCornerRadius(100);
        weight.setBackground(gd2);

        GradientDrawable gd3 = new GradientDrawable();
        gd3.setColor(Color.parseColor("#65CDBA8D"));
        gd3.setCornerRadius(100);
        feet.setBackground(gd3);
        inches.setBackground(gd3);

        calcBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(weight.getText().toString()) && TextUtils.isEmpty(feet.getText().toString()) && TextUtils.isEmpty(inches.getText().toString())){
                cat.setVisibility(View.INVISIBLE);
                res.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Please Fill All The Fields...", Toast.LENGTH_SHORT).show();
            } else if (Float.parseFloat(feet.getText().toString()) == 0.0) {
                cat.setVisibility(View.INVISIBLE);
                res.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Height Cannot Be Zero...", Toast.LENGTH_SHORT).show();
            } else {
                float w = Float.parseFloat(weight.getText().toString());
                float ft = Float.parseFloat(feet.getText().toString());
                float inc = Float.parseFloat(inches.getText().toString());
                float mts = (float) (ft * 0.3048) + (float) (inc * 0.0254);
                float bmi = w/(mts * mts);
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