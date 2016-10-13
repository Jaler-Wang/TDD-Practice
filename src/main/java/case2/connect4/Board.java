package case2.connect4;

/**
 * Created by U0148394 on 10/13/2016.
 */
public class Board {
    private String[][] boards = new String[6][7];

    public int getRow() {
        return 6;
    }

    public int getColumn() {
        return 7;
    }

    public void putDisc(int column) {
        boards[column][0] = "O";
    }

    public String getDisc(int column) {
        return boards[column][0];
    }

    public int getDiscNumber() {
        return 0;
    }
}
