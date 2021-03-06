package case2.connect4;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.containsString;

/**
 * Created by U0148394 on 10/13/2016.
 */
public class BoardTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private Board board = null;
    private OutputStream outputStream = null;

    @Before
    public void init() {
        outputStream = new ByteArrayOutputStream();
        board = new Board(new PrintStream(outputStream));
    }

    @Test
    public void whenTheGameIsStartedTheBoardIsEmpty() {
        Assert.assertEquals(0, board.getDiscNumber());
    }

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        int column = -1;
        exception.expect(RuntimeException.class);
        exception.expectMessage("out of boarder");
        board.putDisc(column);
    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        int column = 1;
        int number = board.putDisc(column);
        Assert.assertEquals(0, number);
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        int column = 1;
        board.putDisc(1);
        int number = board.putDisc(1);
        Assert.assertEquals(1, number);
    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscIncrease() {
        int column = 1;
        board.putDisc(column);
        Assert.assertEquals(1, board.getDiscNumber());
    }

    @Test
    public void whenPutDiscOnAFullColumnThenRuntimeException() {
        int column = 1;
        int maxDiscNumber = 6;
        for (int i = 0; i < maxDiscNumber; i++) {
            board.putDisc(column);
        }
        exception.expect(RuntimeException.class);
        board.putDisc(column);
    }

    @Test
    public void whenFirstPlayerPlaysThenColorIsRed() {
        Assert.assertEquals("R", board.getCurrentPlayer());
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {
        int column = 1;
        board.putDisc(column);
        Assert.assertEquals("G", board.getCurrentPlayer());
    }

    @Test
    public void whenAskedForCurrentPlayerThenOutputNotice() {
        board.getCurrentPlayer();
        Assert.assertEquals("Player R turn", outputStream.toString());
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        int column = 1;
        board.putDisc(column);

        Assert.assertThat(outputStream.toString(), containsString("| |R| | | | | |"));
    }

    @Test
    public void whenTheGameStartsItIsNotFinished() {
        Assert.assertFalse(board.isFinished());
    }

    @Test
    public void whenTheBoardIsFullThenFinished() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                board.putDisc(i);
            }
        }
        Assert.assertTrue(board.isFinished());
    }

    @Test
    public void when4VerticalDiscAreConnectedThenPlayerWins() {
        int column1 = 1;
        int column2 = 2;
        for (int i = 0; i < 3; i++) {
            board.putDisc(column1);
            board.putDisc(column2);
        }
        board.putDisc(column1);
        Assert.assertEquals("R", board.getWinner());
    }

    @Test
    public void when4HorizontalDiscAreConnectedThenPlayerWins() {
        for (int i = 0; i < 3; i++) {
            board.putDisc(i);
            board.putDisc(i);
        }
        board.putDisc(3);
        Assert.assertEquals("R", board.getWinner());
    }

    @Test
    public void whenTopLeftToBottomRightHave4DiscThenPlayerWins() {
        int[] location = new int[]{6, 5, 5, 4, 4, 3, 4, 3, 3, 1, 3};
        for (int i = 0; i < location.length; i++) {
            board.putDisc(location[i]);
        }
        System.out.println(outputStream.toString());
        Assert.assertEquals("R", board.getWinner());
    }

    @Test
    public void whenBottomLeftToTopRightHave4DiscthenPlayerWins() {
        int[] location = new int[]{0,1,1,2,3,2,2,3,4,3,3};
        for (int i = 0; i < location.length; i++) {
            board.putDisc(location[i]);
        }
        Assert.assertEquals("R", board.getWinner());
    }
}
