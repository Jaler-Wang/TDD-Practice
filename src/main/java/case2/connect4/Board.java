package case2.connect4;

import java.io.PrintStream;

/**
 * Created by U0148394 on 10/13/2016.
 */
public class Board {
    private static final int COLUMN = 7;
    private static final int ROW = 6;
    private static final String RED = "R";
    private static final String GREEN = "G";

    private String[][] boards = new String[COLUMN][ROW];
    private int[] index = new int[COLUMN];
    private String currentPlayer = "R";
    private PrintStream printStream = null;
    private String winner = "";

    public Board(PrintStream printStream) {
        this.printStream = printStream;
        for (int i = 0; i < COLUMN; i++) {
            index[i] = 0;
        }
    }

    public int getRow() {
        return ROW;
    }

    public int getColumn() {
        return COLUMN;
    }

    public int putDisc(int column) {
        checkColumn(column);
        boards[column][index[column]] = currentPlayer;
        checkWinner(column, index[column]);
        switchPlayer();
        index[column]++;
        printBoard();
        return index[column] - 1;
    }

    private void checkWinner(int column, int row) {
        if (winner.equals("")) {
            checkVerticalLine(column, row);
        }
        if (winner.equals("")) {
            checkHorizontalLine(row);
        }
        if (winner.equals("")) {
            checkDiagonal(column, row);
        }
    }

    private void checkDiagonal(int column, int row) {
        if (winner.equals("")) {
            checkDiagonal(column, row, 1, -1);
        }
        if (winner.equals("")) {
            checkDiagonal(column, row, -1, 1);
        }
        if (winner.equals("")) {
            checkDiagonal(column, row, -1, -1);
        }
        if (winner.equals("")) {
            checkDiagonal(column, row, 1, 1);
        }
    }

    private void checkDiagonal(int column, int row, int cInc, int rInc) {
        int continueNumber = 0;
        while (column >= 0 && row >= 0 && column < COLUMN && row < ROW) {
            if (currentPlayer.equals(boards[column][row])) {
                continueNumber++;
                setWinner(continueNumber);
            } else {
                continueNumber = 0;
            }
            column += cInc;
            row += rInc;
        }

    }

    private void checkHorizontalLine(int row) {
        int continueNumber = 0;
        for (int i = 0; i < COLUMN; i++) {
            if (currentPlayer.equals(boards[i][row])) {
                continueNumber++;
                setWinner(continueNumber);
            } else {
                continueNumber = 0;
            }
        }
    }

    private void checkVerticalLine(int column, int row) {
        if (row > 2) {
            int continueNumber = 0;
            while (row >= 0) {
                if (boards[column][row].equals(currentPlayer)) {
                    continueNumber++;
                    setWinner(continueNumber);
                } else {
                    continueNumber = 0;
                }
                row--;
            }
        }
    }

    private void setWinner(int continueNumber) {
        if (continueNumber > 3) {
            winner = currentPlayer;
        }
    }

    private void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = ROW - 1; i >= 0; i--) {
            sb.append("|");
            for (int j = 0; j < COLUMN; j++) {
                if (index[j] > i) {
                    sb.append(boards[j][i]);
                } else {
                    sb.append(" ");
                }
                sb.append("|");
            }
            sb.append("\n");
        }
        printStream.println(sb.toString());
    }

    private void switchPlayer() {
        if (currentPlayer.equals(RED)) {
            currentPlayer = GREEN;
        } else {
            currentPlayer = RED;
        }
    }

    private void checkColumn(int column) {
        if (column < 0 || column > COLUMN || index[column] == ROW) {
            throw new RuntimeException("out of boarder");
        }
    }

    public String getDisc(int column) {
        return boards[column][0];
    }

    public int getDiscNumber() {
        int totalDiscs = 0;
        for (int i = 0; i < COLUMN; i++) {
            totalDiscs += index[i];
        }
        return totalDiscs;
    }

    public String getCurrentPlayer() {
        printStream.printf("Player %s turn", currentPlayer);
        return currentPlayer;
    }

    public boolean isFinished() {
        return getDiscNumber() == COLUMN * ROW;
    }

    public String getWinner() {
        return winner;
    }
}
