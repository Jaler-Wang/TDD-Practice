package case3.mock.bean;

/**
 * Created by U0148394 on 10/16/2016.
 */
public class TicTacToeBean {
    private int turn;
    private int x;
    private int y;

    private char player;

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TicTacToeBean(int turn, int x, int y, char player) {
        this.turn = turn;
        this.x = x;
        this.y = y;
        this.player = player;
    }

    @Override
    public boolean equals(Object obj) {
        TicTacToeBean bean = (TicTacToeBean)obj;
        return this.getX() == bean.getX() && this.getY() == bean.getY() &&
                this.getTurn() == bean.getTurn() && this.getPlayer() == bean.getPlayer();
    }
}
