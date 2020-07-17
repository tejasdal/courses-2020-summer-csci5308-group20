package CSCI5308.GroupFormationTool.Database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionManager {
    Connection getDBConnection() throws SQLException;
}
