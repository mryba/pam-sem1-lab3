package com.example.am4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        EditText textInput = findViewById(R.id.textInput);
        CheckBox valueCheckBox = findViewById(R.id.valueCheckBox);
        Button returnButton = findViewById(R.id.returnButton);

        textInput.setText(getIntent().getStringExtra("NAME"));
        returnButton.setOnClickListener(view -> {
            Intent result = new Intent();
            result.putExtra("RESULT_TEXT", textInput.getText().toString());
            result.putExtra("RESULT_VALUE", valueCheckBox.isChecked());
            setResult(RESULT_OK, result);
            finish();
        });
    }
}
