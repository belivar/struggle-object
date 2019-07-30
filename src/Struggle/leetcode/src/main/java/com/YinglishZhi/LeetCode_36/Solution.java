package com.YinglishZhi.LeetCode_36;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * Valid Sudoku
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * @author LDZ
 * @date 2019-06-27 23:40
 */
public class Solution {

    public static boolean isValidSudoku(char[][] board) {
        int boardSize = board.length;

        HashSet row = new HashSet();
        HashSet column = new HashSet();
        HashSet miniBoard = new HashSet();
        boolean rowR;
        boolean columnR;
        boolean miniBoardR;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                char number = board[i][j];
                if ('.' != number) {
                    rowR = row.add(number + "_" + i);
                    columnR = column.add(number + "_" + j);
                    miniBoardR = miniBoard.add(number + "_" + i / 3 + "_" + j / 3);
                    if (!(rowR && columnR && miniBoardR)) {
                        System.out.println(i + "_" + j + "_" + number);
                        System.out.println(rowR + "_" + columnR + "_" + miniBoardR);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

//        char[][] board = {
//                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
//        };
//
//
//        System.out.println(isValidSudoku(board));

        ArrayList list = new ArrayList();
        for (int i = 0; i < 19; i++) {
            list.add(i+ "");
            if (i % 2 == 0) {
                list.remove(i +"");
            }
        }
        System.out.println(list);
    }
}
