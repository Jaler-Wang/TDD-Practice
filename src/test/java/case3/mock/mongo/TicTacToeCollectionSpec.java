package case3.mock.mongo;

import case3.mock.bean.TicTacToeBean;
import com.mongodb.MongoException;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.testng.Assert;

import java.net.UnknownHostException;

import static org.mockito.Mockito.*;

/**
 * Created by U0148394 on 10/16/2016.
 */
public class TicTacToeCollectionSpec {
    private TicTacToeCollection ticTacToeCollection;
    private TicTacToeBean ticTacToeBean;
    private MongoCollection mongoCollection;
    @Before
    public void init() throws UnknownHostException {
        ticTacToeCollection = Mockito.spy(new TicTacToeCollection());
        ticTacToeBean = new TicTacToeBean(3,2,1,"Y");
        mongoCollection = Mockito.mock(MongoCollection.class);
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
        Mockito.doReturn(mongoCollection).when(ticTacToeCollection).getMongoCollection();
        ticTacToeCollection.saveMove(ticTacToeBean);
        Mockito.verify(mongoCollection, times(1)).save(ticTacToeBean);
    }

    @Test
    public void whenSaveMoveThenReturnTrue(){
        Mockito.doReturn(mongoCollection).when(ticTacToeCollection).getMongoCollection();
        Assert.assertTrue(ticTacToeCollection.saveMove(ticTacToeBean));
    }

    @Test
    public void givenExceptionWhenSaveMoveThenReturnFalse(){
        Mockito.doReturn(mongoCollection).when(ticTacToeCollection).getMongoCollection();
        doThrow(new MongoException("exception")).when(mongoCollection).save(Mockito.any(TicTacToeBean.class));
        Assert.assertFalse(ticTacToeCollection.saveMove(ticTacToeBean));
    }

    @Test
    public void whenDropThenInvokeMongoCollectionDrop(){
        doReturn(mongoCollection).when(ticTacToeCollection).getMongoCollection();
        ticTacToeCollection.drop();
        verify(mongoCollection, timeout(1)).drop();
    }

    @Test
    public void whenDropThenReturnTrue(){
        doReturn(mongoCollection).when(ticTacToeCollection).getMongoCollection();
        Assert.assertTrue(ticTacToeCollection.drop());
    }
    @Test
    public void givenExceptionWhenDropThenReturnFalse(){
        Mockito.doReturn(mongoCollection).when(ticTacToeCollection).getMongoCollection();
        doThrow(MongoException.class).when(mongoCollection).drop();
        Assert.assertFalse(ticTacToeCollection.drop());
    }
}
