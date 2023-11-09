package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView[][] textView = new TextView[9][9];
    private List<Button> buttons;
    private int selectedNumber = 1;
    private int prevNumber;
    private float defaultTextSize;

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
        clickButton(1);
    }

    private void setOnclick(){
        for (Integer i = 0; i < 9; i++){
            for (Integer j = 1; j < 10; j++){
                try {
                    Field idField = R.id.class.getDeclaredField(("textView"+i.toString()+j.toString()));
                    textView[i][j-1] = findViewById(idField.getInt(idField));
                    Integer finalI = i;
                    Integer finalJ = j;
                    textView[i][j-1].setOnClickListener(view -> clickView(finalI, finalJ - 1));
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        defaultTextSize = textView[0][0].getTextSize();
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
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(view -> reset());

    }

    private void clickView(int x, int y){
        TextView element = textView[x][y];
        if (selectedNumber==10)
            element.setText(" ");
        else {
            if (!element.getText().equals(selectedNumber+"")) {
                element.setText(selectedNumber + "");
                element.setTextColor(Color.parseColor("#f3f3f3"));
            } else element.setText(" ");
        }
    }

    private void reset(){
        for (Integer x = 0; x < 9; x++){
            for (Integer y = 0; y < 9; y++){


                textView[x][y].setText(" ");

//                generate();

            }
        }
    }

    private void clickButton(Integer number){
        prevNumber = selectedNumber;
        selectedNumber = number.intValue();
        for(TextView row[]: textView){
            for (TextView element: row){
                element.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                element.setTextColor(Color.parseColor("#000000"));
            }
        }
        if (selectedNumber != 10){
            for(TextView row[]: textView){
                for (TextView element: row){
                    if (element.getText().equals(number.toString())){
                        element.setTextColor(Color.parseColor("#f3f3f3"));
                    }
                }
            }
        }
        ((Button)buttons.get(prevNumber-1)).setBackgroundColor(Color.parseColor("#00000000"));
        ((Button)buttons.get(number-1)).setBackgroundColor(Color.parseColor("#f3f3f3"));
    }
}