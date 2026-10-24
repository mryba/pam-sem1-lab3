package com.example.am4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView title = findViewById(R.id.title);
        TextView content = findViewById(R.id.content);
        Button backButton = findViewById(R.id.backButton);

        if ("POINTS".equals(getIntent().getStringExtra("MODE"))) {
            title.setText("Obiekty Punkt");
            ArrayList<Point> points = getIntent().getParcelableArrayListExtra("POINTS", Point.class);
            StringBuilder result = new StringBuilder();
            if (points != null) {
                for (Point point : points) {
                    result.append(point.getDescription()).append('\n');
                }
            }
            content.setText(result.toString());
        } else {
            title.setText("Aktywność druga");
            String result = "Imię: " + getIntent().getStringExtra("NAME") + "\n"
                    + "Nazwisko: " + getIntent().getStringExtra("SURNAME") + "\n"
                    + "Wiek: " + getIntent().getIntExtra("AGE", 0) + "\n"
                    + "Aktywny: " + getIntent().getBooleanExtra("ACTIVE", false) + "\n"
                    + "Kolor: " + getIntent().getStringExtra("COLOR");
            content.setText(result);
        }

        backButton.setOnClickListener(view -> finish());
    }
}
