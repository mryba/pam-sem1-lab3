package com.example.am4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText surnameInput;
    private EditText ageInput;
    private CheckBox activeCheckBox;
    private RadioGroup colorGroup;

    private final ActivityResultLauncher<Intent> thirdActivityLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            String returnedText = result.getData().getStringExtra("RESULT_TEXT");
                            boolean returnedValue = result.getData().getBooleanExtra("RESULT_VALUE", false);
                            Toast.makeText(this, returnedText + " | Aktywne: " + returnedValue,
                                    Toast.LENGTH_LONG).show();
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput) ;
        surnameInput = findViewById(R.id.surnameInput);
        ageInput = findViewById(R.id.ageInput);
        activeCheckBox = findViewById(R.id.activeCheckBox);
        colorGroup = findViewById(R.id.colorGroup);

        Button sendButton = findViewById(R.id.sendButton);
        Button openThirdButton = findViewById(R.id.openThirdButton);
        Button sendPointsButton = findViewById(R.id.sendPointsButton);

        sendButton.setOnClickListener(view -> sendValuesToSecondActivity());
        openThirdButton.setOnClickListener(view -> openThirdActivity());
        sendPointsButton.setOnClickListener(view -> sendPointsToSecondActivity());
    }

    private void sendValuesToSecondActivity() {
        String name = nameInput.getText().toString().trim();
        String surname = surnameInput.getText().toString().trim();
        String ageText = ageInput.getText().toString().trim();
        if (name.isEmpty() || surname.isEmpty() || ageText.isEmpty()) {
            Toast.makeText(this, "Uzupełnij imię, nazwisko i wiek", Toast.LENGTH_SHORT).show();
            return;
        }
        int age = Integer.parseInt(ageText);

        String color = colorGroup.getCheckedRadioButtonId() == R.id.redRadio ? "Czerwony" : "Zielony";
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("MODE", "VALUES");
        intent.putExtra("NAME", name);
        intent.putExtra("SURNAME", surname);
        intent.putExtra("AGE", age);
        intent.putExtra("ACTIVE", activeCheckBox.isChecked());
        intent.putExtra("COLOR", color);
        startActivity(intent);
    }

    private void openThirdActivity() {
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("NAME", nameInput.getText().toString());
        thirdActivityLauncher.launch(intent);
    }

    private void sendPointsToSecondActivity() {
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            points.add(new Point(i, i * 2, Color.rgb((i * 17) % 256, (i * 29) % 256, (i * 41) % 256)));
        }

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("MODE", "POINTS");
        intent.putParcelableArrayListExtra("POINTS", points);
        startActivity(intent);
    }
}
