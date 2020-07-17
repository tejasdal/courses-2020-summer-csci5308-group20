package CSCI5308.GroupFormationTool.Database;

import java.sql.SQLException;

public class DatabaseConcreteFactory extends DatabaseAbstractFactory {
    @Override
    public IDatabaseConfiguration makeDatabaseConfiguration() {
        return new DefaultDatabaseConfiguration();
    }

    @Override
    public ICallStoredProcedure makeCallStoredProcedure(String storedProcedureName) throws SQLException {
        return new CallStoredProcedure(storedProcedureName);
    }

    @Override
    public IConnectionManager makeConnectionManager() {
        return ConnectionManager.instance();
    }
}
