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

    public Board(PrintStream printStream){
        this.printStream = printStream;
        for(int i = 0; i < COLUMN; i++){
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
        switchPlayer();
        index[column]++;
        printBoard();
        return index[column] - 1;
    }

    private void printBoard() {
        StringBuilder sb = new StringBuilder("|");
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COLUMN; j++){
                if(index[j] > i){
                    sb.append(boards[j][i]);
                }else{
                    sb.append(" ");
                }
                sb.append("|");
            }
            sb.append("\n");
        }
        printStream.println(sb.toString());
    }

    private void switchPlayer() {
        if(currentPlayer.equals(RED)){
            currentPlayer = GREEN;
        }
        else{
            currentPlayer = RED;
        }
    }

    private void checkColumn(int column){
        if(column < 0 || column > COLUMN || index[column] == ROW){
            throw new RuntimeException("out of boarder");
        }
    }
    public String getDisc(int column) {
        return boards[column][0];
    }

    public int getDiscNumber() {
        int totalDiscs = 0;
        for(int i = 0; i < COLUMN; i++){
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
}
