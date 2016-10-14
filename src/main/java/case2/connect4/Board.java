package case2.connect4;

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

    public Board(){
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
        turnPlayer();
        return index[column]++;
    }

    private void turnPlayer() {
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
        for(int i = 0; i < ROW; i++){
            totalDiscs += index[i];
        }
        return totalDiscs;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
}
