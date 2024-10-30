package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Board {

    public File fileToRead;

    public char[][] board = new char[3][3];

    public ArrayList<MoveInfo> movesMade = new ArrayList<>();
    public ArrayList<Integer> moveValues = new ArrayList<>();

    public Board(File file) throws MyException, IOException {

        this.fileToRead = file;

        try {

            FileReader fr = new FileReader(file);

            BufferedReader br = new BufferedReader(fr);

            ArrayList<char[]> temp = new ArrayList<>();

            String line = "xxx";

            while ((line = br.readLine()) != null) {

                char[] tempA = line.toCharArray();
                temp.add(tempA);

            }

            br.close();
            fr.close();

            for (int row = 0; row < board.length; row++) {

                board[row] = temp.get(row);

            }

        } catch (FileNotFoundException ex) {
            throw new MyException("No valid file input ", ex);

        }

    } // end of constructor

    public char[][] getBoard() {
        return board;
    }

    public ArrayList<MoveInfo> getMovesMade() {
        return movesMade;
    }

    public ArrayList<Integer> getMoveValues() {
        return moveValues;
    }

    public static int evalBoard(char[][] board) {

    //check got matching row
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == 'X') {

                    return 1;

                } else if (board[row][0] == 'O') {

                    return -1;

                }

            }
        }

    // Check column got win
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == 'X') {

                    return 1;

                } else if (board[0][col] == 'O') {

                    return -1;

                }
            }
        }

     // Check diagonal
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X') {

                return 1;

            } else if (board[0][0] == 'O') {

                return -1;

            }

        }
    // check other diagonal
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'X'){

                return 1;


            } else if (board[0][2] == 'O'){

                return -1;


            }
                
        }

    // if nothing means draw


        return 0;
    }




    public static boolean gotMoveLeft(char[][] board) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (board[row][col] == '.') {

                    return true;

                }

        return false;
    }

    public String toPrintBoard() {

        String result = "Processing: " + this.fileToRead.toString() + "\n \n";

        result += "Board: \n";

        for (char[] c : this.board) {
            result += new String(c) + "\n";

        }

        return result;

    }

    public int minimax(char[][] board, boolean Max) {
        int score = evalBoard(board);

        if (score == 1 || score == -1) {

            return score;
        }

        if (gotMoveLeft(board) == false) {

            return 0;

        }

        if (Max) {

            // System.out.println("X making move...");
    
            int best = -1000000;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col ++) {
                    if (board[row][col] == '.') {

                        board[row][col] = 'X';

                        // System.out.println("current possible move is " + i + "," + j);

                        // //score for this move
                        // int moveValue = minimax(board, false);
                        // System.out.println("current value of this move is " + moveValue);

                        best = Math.max(best, minimax(board, false));

                        //put back after trying
                        board[row][col] = '.';
                    }
                }
            }
            return best;
        } else {
            int best = 1000000;

            // System.out.println("O making move...");

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (board[row][col] == '.') {
                        board[row][col] = 'O';

    
                        best = Math.min(best, minimax(board, true));

                        //put back after trying
                        board[row][col] = '.';

                    }
                }
            }
            return best;
        }
    }

    public void possibleMoves(char[][] board) {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                if (board[row][col] == '.') {
                    board[row][col] = 'X';

                    // System.out.println("now at " + row + " , " + col);
                    // add possible move to movesMade;
                    movesMade.add(new MoveInfo(row, col));
                    // Compute this move score
                    int moveValue = minimax(board, false);
                    moveValues.add(moveValue);

                    // put back
                    board[row][col] = '.';

                }
            }
        }

    }

    // public String printResult(){

    // this.movesMade;
    // this.moveValues;

    // }

}
