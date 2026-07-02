package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        int[] appendButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnDot, R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv
        };

        for (int id : appendButtons) {
            Button b = findViewById(id);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String current = display.getText().toString();
                    String tapped  = ((Button) v).getText().toString();

                    if (current.equals("0")) {
                        display.setText(tapped);
                    } else {
                        display.setText(current + tapped);
                    }
                }
            });
        }

        Button clearBtn = findViewById(R.id.btnClear);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("0");
            }
        });

        Button deleteBtn = findViewById(R.id.btnDelete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current = display.getText().toString();
                if (current.length() <= 1) {
                    display.setText("0");
                } else {
                    display.setText(current.substring(0, current.length() - 1));
                }
            }
        });

        Button equalsBtn = findViewById(R.id.btnEquals);
        equalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expr = display.getText().toString();
                try {
                    Expression expression = new ExpressionBuilder(expr).build();
                    double result = expression.evaluate();

                    if (result == Math.floor(result) && !Double.isInfinite(result)) {
                        display.setText(String.valueOf((long) result));
                    } else {
                        display.setText(String.valueOf(result));
                    }
                } catch (Exception e) {
                    display.setText("Error");
                }
            }
        });
    }
}