package CSCI5308.GroupFormationTool.Database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ICallStoredProcedure {
    void cleanup();

    void setParameter(int paramIndex, String value) throws SQLException;

    void setParameter(int paramIndex, Date value) throws SQLException;

    void registerOutputParameterString(int paramIndex) throws SQLException;

    void setParameter(int paramIndex, long value) throws SQLException;

    void registerOutputParameterLong(int paramIndex) throws SQLException;

    ResultSet executeWithResults() throws SQLException;

    void execute() throws SQLException;

    void addBatch() throws SQLException;

    void executeBatch() throws SQLException;
}
