package case2.connect4;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by U0148394 on 10/13/2016.
 */
public class BoardTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private Board board = null;

    @Before
    public void init(){
        board = new Board();
    }

    @Test
    public void whenTheGameIsStartedTheBoardIsEmpty(){
       Assert.assertEquals(0, board.getDiscNumber());
    }

    @Test
    public void whenDiscOutsideBoardThenRuntimeException(){
        int column = -1;
        exception.expect(RuntimeException.class);
        exception.expectMessage("out of boarder");
        board.putDisc(column);
    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero(){
        int column = 1;
        int number = board.putDisc(column);
        Assert.assertEquals(0, number);
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne(){
        int column = 1;
        board.putDisc(1);
        int number = board.putDisc(1);
        Assert.assertEquals(1, number);
    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscIncrease(){
        int column = 1;
        board.putDisc(column);
        Assert.assertEquals(1, board.getDiscNumber());
    }

    @Test
    public void whenPutDiscOnAFullColumnThenRuntimeException(){
        int column = 1;
        int maxDiscNumber = 6;
        for(int i = 0; i < maxDiscNumber; i++){
            board.putDisc(column);
        }
        exception.expect(RuntimeException.class);
        board.putDisc(column);
    }
}