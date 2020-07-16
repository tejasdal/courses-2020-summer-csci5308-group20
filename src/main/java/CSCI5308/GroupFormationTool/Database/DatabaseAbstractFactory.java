package CSCI5308.GroupFormationTool.Database;

import java.sql.SQLException;

public abstract class DatabaseAbstractFactory {

    private static DatabaseAbstractFactory uniqueInstance = null;

    protected DatabaseAbstractFactory(){}

    public static DatabaseAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new DatabaseConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IDatabaseConfiguration makeDatabaseConfiguration();
    public abstract ICallStoredProcedure makeCallStoredProcedure(String storedProcedureName) throws SQLException;
    public abstract IConnectionManager makeConnectionManager();
}
