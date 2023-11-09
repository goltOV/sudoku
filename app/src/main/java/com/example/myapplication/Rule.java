package com.example.myapplication;

import android.widget.TextView;

public class Rule {
    private int[][] board;     //create same in MainAct for optim.

    private boolean[][] RuleInBoxes = {{false,false,false},{false,false,false},{false,false,false}};
    private boolean[] RuleInHorizontal = {false,false,false,false,false,false,false,false,false};
    private boolean[] RuleInVertical = {false,false,false,false,false,false,false,false,false};

    public void check(final int[][] BOARD, final int LAST_X, final int LAST_Y){
        this.board = board;

//        RuleInBoxes[(int) Math.floor(LAST_X/3)][(int) Math.floor(LAST_Y/3)]

        checkBox((int) Math.floor(LAST_X/3), (int) Math.floor(LAST_Y/3));

    }

    private void checkBox(int x, int y){
        for (int num = 0; num < 9; num++) {
            int numCount = 0;
            for (int i = x * 3; i < x * 3 + 3; i++) {
                for (int j = x * 3; j < x * 3 + 3; j++) {
                    if (board[i][j]==num) {
                        ++numCount;
                        if (numCount>1) RuleInBoxes[x][y] = false;
                    }

                }

            }
        }


    }





}
