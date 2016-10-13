package case2.connect4;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by U0148394 on 10/13/2016.
 */
@Test
public class BoardTest {
    private Board board = null;
    @BeforeMethod
    public void init(){
        board = new Board();
    }
    public void whenTheGameIsStartedTheBoardIsEmpty(){
       Assert.assertEquals(0, board.getDiscNumber());
    }

}
