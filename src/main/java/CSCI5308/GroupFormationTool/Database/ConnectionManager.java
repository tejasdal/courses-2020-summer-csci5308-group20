package CSCI5308.GroupFormationTool.Database;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton for retrieving connections.
public class ConnectionManager implements IConnectionManager {
    private static ConnectionManager uniqueInstance = null;

    private Logger log = LoggerFactory.getLogger(ConnectionManager.class);
    private String dbURL;
    private String dbUserName;
    private String dbPassword;

    public ConnectionManager() {
        IDatabaseConfiguration config = DatabaseAbstractFactory.instance().makeDatabaseConfiguration();
        dbURL = config.getDatabaseURL();
        dbUserName = config.getDatabaseUserName();
        dbPassword = config.getDatabasePassword();
    }

    public static ConnectionManager instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new ConnectionManager();
        }
        return uniqueInstance;
    }

    @Override
    public Connection getDBConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        return connection;
    }
}
