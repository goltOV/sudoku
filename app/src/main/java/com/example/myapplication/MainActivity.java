package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView[][] textView = new TextView[9][9];
    private List<Button> buttons;
    private int selectedNumber = 1;
    private int prevNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        buttons = new ArrayList<>();
        setOnclick();
    }

    private void setOnclick(){
        for (Integer i = 0; i < 9; i++){
            for (Integer j = 1; j < 10; j++){
                try {
                    Field idField = R.id.class.getDeclaredField(("textView"+i.toString()+j.toString()));
                    textView[i][j-1] = findViewById(idField.getInt(idField));
                    textView[i][j-1].setOnClickListener(view -> clickView());
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        for(Integer i = 1; i <= 10;i++){
            try {
                Field idField = R.id.class.getDeclaredField("button"+i.toString());
                buttons.add(findViewById(idField.getInt(idField)));
                Integer finalI = i;
                buttons.get(i-1).setOnClickListener(view -> clickButton(finalI));
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void clickView(){

    }

    private void clickButton(Integer number){
        prevNumber = selectedNumber;
        selectedNumber = number.intValue();
        ((Button)buttons.get(prevNumber-1)).setBackgroundColor(Color.parseColor("#00000000"));
        ((Button)buttons.get(number-1)).setBackgroundColor(Color.parseColor("#f3f3f3"));
    }
}