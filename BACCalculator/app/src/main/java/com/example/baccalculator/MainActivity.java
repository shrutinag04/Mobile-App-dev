package com.example.baccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTextWeight;
    RadioGroup radioGroupGender,radioGroupDrink;
    TextView textViewWeight,textAlcoholPercentage,textViewNoDrinks,textViewBACLevel,textViewStatus;
    SeekBar seekBar;
    //Button ButtonReset, ButtonAddDrink;
    View viewStatus;
    Profile profile;
    ArrayList<Drink> drinks=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextWeight=findViewById(R.id.editTextWeight);
        radioGroupGender=findViewById(R.id.radioGroupGender);
        radioGroupDrink=findViewById(R.id.radioGroupDrink);
        textViewWeight=findViewById(R.id.textViewWeight);
        textAlcoholPercentage=findViewById(R.id.textAlcoholPercentage);
        textViewNoDrinks=findViewById(R.id.textViewNoDrinks);
        textViewBACLevel=findViewById(R.id.textViewBACLevel);
        textViewStatus=findViewById(R.id.textViewStatus);
        seekBar=findViewById(R.id.seekBar);
        viewStatus=findViewById(R.id.viewStatus);
        findViewById(R.id.buttonSetWeight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredWeight=editTextWeight.getText().toString();
                try{
                    Double weight=Double.valueOf(enteredWeight);
                    if(weight>0){
                        String gender="Female";
                        if(radioGroupGender.getCheckedRadioButtonId()==R.id.radioButtonMale){
                            gender="Male";
                        }
                        profile=new Profile(gender,weight);
                        textViewWeight.setText(String.valueOf(weight)+"("+gender+")");
                        editTextWeight.setText("");

                    }else{
                        Toast.makeText(MainActivity.this, "Enter Valid Weight!!", Toast.LENGTH_SHORT).show();
                    }
                    drinks.clear();
                    calculateBAC();


                }catch(NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Enter Valid Weight!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textAlcoholPercentage.setText(String.valueOf(progress)+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        findViewById(R.id.buttonAddDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(profile==null){
                    Toast.makeText(MainActivity.this,"Setup your weight and gender!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    double size=1;
                    if(radioGroupDrink.getCheckedRadioButtonId()==R.id.radioButton5oz){
                        size=5;
                    }
                    else if(radioGroupDrink.getCheckedRadioButtonId()==R.id.radioButton12oz){
                        size=12;
                    }
                    double percentage=seekBar.getProgress();
                    if(percentage>0){
                        Drink drink=new Drink(size,percentage);
                        drinks.add(drink);
                        calculateBAC();

                    }
                    else{
                        Toast.makeText(MainActivity.this,"Setup Alcohol %!!", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }

    void calculateBAC(){
        if(drinks.size()==0){
            textViewBACLevel.setText("BAC Level:0.000");
            textViewStatus.setText("You're Safe");
            viewStatus.setBackgroundColor(getColor(R.color.safe_color));


        }
        else{
            textViewNoDrinks.setText("#Drinks:" + String.valueOf(drinks.size()));
            double valueA=0.0;
            for(Drink drink:drinks){
                valueA=valueA+ drink.getSize()*drink.getPercentage()/100.0;
            }
            double valueR=0.73;
            if(profile.getGender()=="Female"){
                valueR=0.66;
            }
            double bac=valueA*5.14/(profile.getWeight()*valueR);
            textViewBACLevel.setText("BAC Level:"+String.format("%.3f",bac));
            if(bac<=0.08){
                textViewStatus.setText("You're Safe");
                viewStatus.setBackgroundColor(getColor(R.color.safe_color));
            }
            else if(bac<=0.2){
                textViewStatus.setText("Be Careful");
                viewStatus.setBackgroundColor(getColor(R.color.becareful_color));

            }
            else{
                textViewStatus.setText("Over The Limit");
                viewStatus.setBackgroundColor(getColor(R.color.overlimit_color));

            }

        }

    }
}