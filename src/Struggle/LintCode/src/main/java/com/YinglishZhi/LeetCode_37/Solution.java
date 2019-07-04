package com.YinglishZhi.LeetCode_37;

import java.util.ArrayList;

/**
 * Sudoku Solver
 * 求解数独
 *
 * @author LDZ
 * @date 2019-06-28 22:49
 */
public class Solution {

    private static boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' == board[i][j]) {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isSudokuValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }


    private static boolean isSudokuValid(char[][] board, int row, int column, char c) {
        for (int i = 0; i < 9; i++) {
            if ('.' != board[row][i] && c == board[row][i]) {
                return false;
            }

            if ('.' != board[i][column] && c == board[i][column]) {
                return false;
            }
            char temp = board[3 * (row / 3) + i / 3][3 * (column / 3) + i % 3];
            if ('.' != temp && c == temp) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean sl = solve(board);
        System.out.println(sl);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + "  -  ");
            }
            System.out.println("");
        }

        System.out.println();

//        System.out.println(isSudokuValid(board, 1, 1, '2'));
//        System.out.println(isSudokuValid(board, 1, 1, '1'));
//        System.out.println(isSudokuValid(board, 1, 1, '3'));
//        System.out.println(isSudokuValid(board, 1, 1, '4'));
//        System.out.println(isSudokuValid(board, 1, 1, '5'));
    }

}
