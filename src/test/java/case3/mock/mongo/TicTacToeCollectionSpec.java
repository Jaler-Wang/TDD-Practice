package case3.mock.mongo;

import case3.mock.bean.TicTacToeBean;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.testng.Assert;

import java.net.UnknownHostException;

import static org.mockito.Mockito.times;

/**
 * Created by U0148394 on 10/16/2016.
 */
public class TicTacToeCollectionSpec {
    private TicTacToeCollection ticTacToeCollection;

    @Before
    public void init() throws UnknownHostException {
        ticTacToeCollection = Mockito.spy(new TicTacToeCollection());
    }
    @Test
    public void whenInstantiatedThenMongoHasDbNameTicTacToe() throws UnknownHostException {
        Assert.assertEquals("tic-tac-toe", ticTacToeCollection.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame(){
        Assert.assertEquals("game", ticTacToeCollection.getMongoCollection().getName());
    }

    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave(){
        TicTacToeBean ticTacToeBean = new TicTacToeBean(3,2,1,"Y");
        MongoCollection mongoCollection = Mockito.mock(MongoCollection.class);
        Mockito.doReturn(mongoCollection).when(ticTacToeCollection).getMongoCollection();
        ticTacToeCollection.saveMove(ticTacToeBean);
        Mockito.verify(mongoCollection, times(1)).save(ticTacToeBean);
    }
}
