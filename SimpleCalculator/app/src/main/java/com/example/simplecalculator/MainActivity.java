package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextNo1,editTextNo2;
    TextView textViewAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNo1=findViewById(R.id.editTextNo1);
        editTextNo2=findViewById(R.id.editTextNo2);
        textViewAns=findViewById(R.id.textViewAns);
        findViewById(R.id.buttonCal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((String.valueOf(editTextNo1.getText()).matches("")) || (String.valueOf(editTextNo2.getText()).matches(""))  ){
                    Toast.makeText(MainActivity.this, "Enter Valid Input!! ", Toast.LENGTH_SHORT).show();

                }
                else{

                    double no1= Double.valueOf(editTextNo1.getText().toString());
                    double no2= Double.valueOf(editTextNo2.getText().toString());
                    double Ans=0;
                    if(no2==0){
                        Toast.makeText(MainActivity.this, "Can't divide by zero!", Toast.LENGTH_SHORT).show();
                    }
                    else if(no2>0){
                         Ans=no1/no2;

                    }


                    textViewAns.setText(String.format("%.2f",Ans));

                }




            }
        });
        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNo1.setText("");
                editTextNo2.setText("");
                textViewAns.setText("");
            }
        });



    }
}