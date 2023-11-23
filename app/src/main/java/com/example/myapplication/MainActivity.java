package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView[][] textView = new TextView[9][9];
    private List<Button> buttons;
    private int selectedNumber = 1;
    private Rule rule;

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
        generate();
    }

    /**
     * add textView to table of textViews
     * @param x - int position
     * @param y - int position
     */
    private void setTextView(int x, int y){
        try{
            Field idField = R.id.class.getDeclaredField(("textView"+ x + y));
            textView[x][y-1] = findViewById(idField.getInt(idField));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * add button to list of buttons
     * @param number - int
     */
    private void setButtons(int number){
        try{
            Field idField = R.id.class.getDeclaredField("button"+ number);
            buttons.add(findViewById(idField.getInt(idField)));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * set Onclick and fill textView table | buttons list
     */
    private void setOnclick(){
        for (int x = 0; x < 9; x++){
            for (int y = 1; y < 10; y++){
                setTextView(x,y);
                int finalX = x;
                int finalY = y;
                textView[x][y-1].setOnClickListener(view -> clickView(finalX, finalY - 1));
        }}
        for(int i = 1; i <= 10; i++){
            setButtons(i);
            Integer finalI = i;
            buttons.get(i-1).setOnClickListener(view -> clickButton(finalI));
        }

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(view -> generate());
    }

    /**
     * set selectedNumber on clicked textView
     * @param x - int position
     * @param y - int position
     */
    private void clickView(int x, int y){
        TextView element = textView[x][y];
        if (selectedNumber==10)     element.setText(" ");
        else {
            if (!element.getText().equals(selectedNumber+"")) {
                element.setText(selectedNumber + "");
                element.setTextColor(Color.parseColor("#f3f3f3"));
                checkRule(x, y);
            } else element.setText(" ");
        }
    }

    /**
     * check rules in changed row, column and box
     * @param x - int position of changed element
     * @param y - int position of changed element
     */
    private void checkRule(int x, int y){
        if(rule.check(getBoardValues(), x, y)) win();
    }

    private void win(){
        System.out.println("WIN!!!");
    }

    /**
     * get board table in int and return them
     * @return board - int[9][9]
     */
    private int[][] getBoardValues(){
        int[][] board = new int[9][9];
        for (int x = 0; x < 9; x++){
            for (int y = 0; y < 9; y++) {
                try {
                    board[x][y] = Integer.parseInt(textView[x][y].getText().toString());
                }catch (Exception e){
                    board[x][y] = 0;
        }}}
        return board;
    }

    /**
     * generate new board, reset rules
     */
    private void generate(){
        FullTestBoard test = new FullTestBoard();
        String[][] testB = test.generate();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                textView[i][j].setText(testB[i][j]);
        }}
        clickButton(selectedNumber);
        rule = new Rule();
    }

    /**
     * color selected values and set selectedNumber
     * @param number - int selected value
     */
    private void clickButton(Integer number){
        int prevNumber = selectedNumber;
        selectedNumber = number;
        for(TextView[] row : textView){
            for (TextView element: row){
                element.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                element.setTextColor(Color.parseColor("#000000"));
        }}
        if (selectedNumber != 10){
            for(TextView[] row : textView){
                for (TextView element: row){
                    if (element.getText().equals(number.toString())){
                        element.setTextColor(Color.parseColor("#f3f3f3"));
        }}}}
        buttons.get(prevNumber-1).setBackgroundColor(Color.parseColor("#00000000"));
        buttons.get(number-1).setBackgroundColor(Color.parseColor("#f3f3f3"));
    }
}