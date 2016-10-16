package case3.mock.mongo;

import case3.mock.bean.TicTacToeBean;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;

/**
 * Created by U0148394 on 10/16/2016.
 */
public class TicTacToeCollection {
    private MongoCollection mongoCollection = null;

    public TicTacToeCollection() throws UnknownHostException {
        DB db = new MongoClient().getDB("tic-tac-toe");
        mongoCollection = new Jongo(db).getCollection("game");
    }

    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }


    public void saveMove(TicTacToeBean ticTacToeBean) {
        getMongoCollection().save(ticTacToeBean);
    }
}
