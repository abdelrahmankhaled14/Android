package com.example.salarypredict;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.salarypredict.R;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextView display;
    Button Predictbutton;
    AutoCompleteTextView jop;
    TextInputEditText experience;   // fixed type
    String[] jobs = {"Engineer", "Teacher", "Doctor", "Designer", "Manager"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.txtResult);
        Predictbutton = findViewById(R.id.btnPredict);
        jop = findViewById(R.id.inputJob);
        experience = findViewById(R.id.inputExperience);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                jobs
        );
        jop.setAdapter(adapter);

        Predictbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String job = jop.getText().toString();
                String expText = experience.getText().toString();

                // guard: no crash if experience is empty
                if (expText.isEmpty()) {
                    display.setText("Please enter years of experience");
                    return;
                }

                int years = Integer.parseInt(expText);

                // placeholder salary logic — change the numbers however you like
                int base;
                switch (job) {
                    case "Engineer": base = 60000; break;
                    case "Doctor":   base = 80000; break;
                    case "Teacher":  base = 40000; break;
                    case "Designer": base = 50000; break;
                    case "Manager":  base = 70000; break;
                    default:         base = 45000;   // covers "nothing selected"
                }

                int salary = base + years * 3000;

                display.setText("Estimated salary: $" + salary);
            }
        });
    }
}