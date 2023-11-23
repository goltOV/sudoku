package com.example.myapplication;

public class Rule {
    private int[][] board;
    private boolean[][] RuleInBoxes = {{false,false,false},{false,false,false},{false,false,false}};
    private boolean[] RuleInHorizontal = {false,false,false,false,false,false,false,false,false};
    private boolean[] RuleInVertical = {false,false,false,false,false,false,false,false,false};

    /**
     * check rules in row, column and box where element be changed
     * @param BOARD - 2d int table
     * @param LAST_X - x of changed element
     * @param LAST_Y - y of changed element
     * @return true when all rules are met
     */
    public boolean check(final int[][] BOARD, final int LAST_X, final int LAST_Y){
        this.board = BOARD;
        checkBox((int) Math.floor(LAST_Y/3), (int) Math.floor(LAST_X/3));
        checkHorizontal(LAST_X);
        checkVertical(LAST_Y);

        for(boolean rule : RuleInVertical){
            if(!rule) return false;
        }
        for(boolean rule : RuleInHorizontal){
            if(!rule) return false;
        }
        for(boolean row[] : RuleInBoxes){
            for(boolean rule : row){
                if(!rule) return false;
            }
        }
        return true;
    }

    /**
     * check rule for column
     * @param column
     */
    private void checkVertical(int column) {
        for (int num = 1; num < 10; num++) {
            int numCount = 0;
            for (int row = 0; row < 9; row++) {
                if (board[row][column]==0){
                    RuleInVertical[column] = false;
                    return;
                }
                if (board[row][column]==num) {
                    ++numCount;
                    if (numCount>1)
                    {
                        RuleInVertical[column] = false;
                        return;
                    }
                }}
            if (numCount==1) RuleInVertical[column] = true;
        }
    }

    /**
     * check rule for row
     * @param row
     */
    private void checkHorizontal(int row) {
        for (int num = 1; num < 10; num++) {
            int numCount = 0;
            for (int column = 0; column < 9; column++) {
                if (board[row][column]==0){
                    RuleInHorizontal[row] = false;
                    return;
                }
                if (board[row][column]==num) {
                    ++numCount;
                    if (numCount>1){
                        RuleInHorizontal[row] = false;
                        return;
                    }
                }}
            if (numCount==1) RuleInHorizontal[row] = true;
        }
    }

    /**
     * check rule for box
     * @param x
     * @param y
     */
    private void checkBox(int x, int y){
        for (int num = 1; num < 10; num++) {
            int numCount = 0;
            for (int row = y * 3; row < y * 3 + 3; row++) {
                for (int column = x * 3; column < x * 3 + 3; column++) {
                    if (board[row][column]==0){
                        RuleInVertical[column] = false;
                        return;
                    }
                    if (board[row][column]==num) {
                        ++numCount;
                        if (numCount>1) {
                            RuleInBoxes[x][y] = false;
                            return;
                        }
            }}}
            if (numCount==1) RuleInBoxes[x][y] = true;
        }
    }

}
