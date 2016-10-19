package case3.mock;

import case3.mock.bean.TicTacToeBean;
import case3.mock.mongo.TicTacToeCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TicTacToeSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private TicTacToe ticTacToe;
    private TicTacToeCollection ticTacToeCollection = null;

    @Before
    public final void before() throws UnknownHostException {
        ticTacToeCollection = mock(TicTacToeCollection.class);
        doReturn(true).when(ticTacToeCollection).saveMove(any(TicTacToeBean.class));
        ticTacToe = new TicTacToe(ticTacToeCollection);
    }

    @Test
    public void whenXOutsideBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(5, 2);
    }

    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 5);
    }

    @Test
    public void whenOccupiedThenRuntimeException() {
        ticTacToe.play(1, 1);
        ticTacToe.play(2, 1);
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 1);
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {
        assertEquals('X', ticTacToe.nextPlayer());
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThenO() {
        ticTacToe.play(1, 1);
        assertEquals('O', ticTacToe.nextPlayer());
    }

    @Test
    public void whenPlayThenNoWinner() {
        String actual = ticTacToe.play(1, 1);
        assertEquals("No winner", actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 1); // X
        ticTacToe.play(2, 2); // O
        String actual = ticTacToe.play(3, 1); // X
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() {
        ticTacToe.play(2, 1); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(3, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        String actual = ticTacToe.play(1, 3); // O
        assertEquals("O is the winner", actual);
    }

    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(1, 3); // O
        String actual = ticTacToe.play(3, 3); // O
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() {
        ticTacToe.play(1, 3); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(1, 2); // O
        String actual = ticTacToe.play(3, 1); // O
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenAllBoxesAreFilledThenDraw() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 2);
        assertEquals("The result is draw", actual);
    }

    @Test
    public void whenInstantiatedThenSetCollection() {
        Assert.assertNotNull(ticTacToe.getTicTacToeeCollection());
    }

    @Test
    public void whenPlayThenSaveMoveIsInvoked() {
        TicTacToeBean bean = new TicTacToeBean(1, 1, 3, 'X');
        ticTacToe.play(bean.getX(), bean.getY());
        verify(ticTacToeCollection, times(1)).saveMove(bean);
    }

    @Test
    public void whenPlayAndSaveReturnFalseThenThrowException() {
        TicTacToeBean bean = new TicTacToeBean(2, 3, 3, 'X');
        exception.expect(RuntimeException.class);
        doReturn(false).when(ticTacToeCollection).saveMove(any(TicTacToeBean.class));
        ticTacToe.play(bean.getX(), bean.getY());
    }

    @Test
    public void whenPlayInvokeMultipleTimesThenReturnIncreases(){
        TicTacToeBean move1 = new TicTacToeBean(1, 1,1,'X');
        ticTacToe.play(move1.getX(), move1.getY());
        verify(ticTacToeCollection, times(1)).saveMove(move1);
        TicTacToeBean move2 = new TicTacToeBean(2, 2,2,'O');
        ticTacToe.play(move2.getX(), move2.getY());
        verify(ticTacToeCollection, times(1)).saveMove(move2);
    }
}
