package com.example.myapplication;

public class FullTestBoard {
    private String[][] board = new String[9][9];
    private int[][] intBoard = {
            {1,2,3,  7,8,9,  4,5,6,},
            {4,5,6,  1,2,3,  7,8,9,},
            {7,8,9,  4,5,6,  1,2,3,},

            {9,1,2,  6,7,8,  3,4,5,},
            {3,4,5,  9,1,2,  6,7,8,},
            {6,7,8,  3,4,5,  9,1,2,},

            {8,9,1,  5,6,7,  2,3,4,},
            {2,3,4,  8,9,1,  5,6,7,},
            {5,6,7,  2,3,4,  8,9,1,},
        };

    /**
     * use already generated table and change randomly
     * @return String table for implementation in textView
     */
    public String[][] generate(){
        board = new String[9][9];

        int horizontal = Integer.parseInt((Math.floor(Math.random()*2)+"").charAt(0)+"");
        int vertical = Integer.parseInt((Math.floor(Math.random()*2)+"").charAt(0)+"");
        int i, j, x, y;

        switch (horizontal){
            case 0:
                switch (vertical){
                    case 0:
                        i = 0; x = 0;
                        while (x<9){
                            j = 0; y = 0;
                            while (y<9){
                                if(Math.floor(Math.random() * 4) == 0) board[x][y] = Integer.toString(intBoard[i][j]);
                                j++; y++;
                            }i++; x++;
                        }break;
                    default:
                        i = 8; x = 0;
                        while (x<9){
                            j = 0; y = 0;
                            while (y<9){
                                if(Math.floor(Math.random() * 4) == 0) board[x][y] = Integer.toString(intBoard[i][j]);
                                j++; y++;
                            }i--; x++;
                }}break;
            default:
                switch (vertical){
                    case 0:
                        i = 0; x = 0;
                        while (x<9){
                            j = 8; y = 0;
                            while (y<9){
                                if(Math.floor(Math.random() * 4) == 0) board[x][y] = Integer.toString(intBoard[i][j]);
                                j--; y++;
                            }i++; x++;
                        }break;
                    default:
                        i = 8; x = 0;
                        while (x<9){
                            j = 8; y = 0;
                            while (y<9){
                                if(Math.floor(Math.random() * 4) == 0) board[x][y] = Integer.toString(intBoard[i][j]);
                                j--; y++;
                            }i--; x++;
        }}}


        return board;
    }


}
