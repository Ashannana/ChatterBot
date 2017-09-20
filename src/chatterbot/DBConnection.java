package chatterbot;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;

/**
 *
 * @author ASHAN
 */
public class DBConnection {
    DB mongoDB;

    private DBConnection(){}

    public DB getMongoDBConnection() throws UnknownHostException{

        if(mongoDB == null){
            initDB();
        }
        return mongoDB;
    }

    private synchronized void initDB() throws UnknownHostException{

        if(mongoDB == null){
            MongoClient mongoClient=new MongoClient(Constants.host,Constants.port);
            mongoDB = mongoClient.getDB(Constants.dbName);
        }
    }

    private static class SingletoHolder{
        private static final DBConnection INSTANCE =new DBConnection();
    }

    public static DB getConnection() throws UnknownHostException{
        return SingletoHolder.INSTANCE.getMongoDBConnection();
    }
}
