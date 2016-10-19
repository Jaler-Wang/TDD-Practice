package case3.mock;

import case3.mock.bean.TicTacToeBean;
import case3.mock.mongo.TicTacToeCollection;

import java.net.UnknownHostException;

public class TicTacToe {

    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
    private char lastPlayer = '\0';
    private static final int SIZE = 3;
    public static final String NO_WINNER = "No winner";
    public static final String RESULT_DRAW = "The result is draw";
    private TicTacToeCollection ticTacToeeCollection = null;
    private int turn = 1;
    public TicTacToe() throws UnknownHostException {
        new TicTacToe(new TicTacToeCollection());
    }

    public TicTacToe(TicTacToeCollection ticTacToeCollection){
        this.ticTacToeeCollection = ticTacToeCollection;
    }
    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(x, y, lastPlayer);
        if (isWin(x, y)) {
            return lastPlayer + " is the winner";
        } else if (isDraw()) {
            return RESULT_DRAW;
        } else {
            return NO_WINNER;
        }
    }

    public char nextPlayer() {
        if (lastPlayer == 'X') {
            return 'O';
        }
        return 'X';
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException("X is outside board");
        }
    }

    private void setBox(int x, int y, char lastPlayer) {
        if (board[x - 1][y - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[x - 1][y - 1] = lastPlayer;
            if(!ticTacToeeCollection.saveMove(new TicTacToeBean(turn++, x, y, lastPlayer))){
                throw new RuntimeException("save fail");
            }
        }
    }

    private boolean isWin(int x, int y) {
        int playerTotal = lastPlayer * SIZE;
        char horizontal, vertical, diagonal1, diagonal2;
        horizontal = vertical = diagonal1 = diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            horizontal += board[i][y - 1];
            vertical += board[x - 1][i];
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
        }
        if (horizontal == playerTotal
                || vertical == playerTotal
                || diagonal1 == playerTotal
                || diagonal2 == playerTotal) {
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    public Object getTicTacToeeCollection() {
        return ticTacToeeCollection;
    }
}
