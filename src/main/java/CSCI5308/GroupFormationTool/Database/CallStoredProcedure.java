package CSCI5308.GroupFormationTool.Database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CallStoredProcedure implements ICallStoredProcedure {
	Logger log = LoggerFactory.getLogger(CallStoredProcedure.class);
	private String storedProcedureName;
	private Connection connection;
	private CallableStatement statement;
	
	public CallStoredProcedure(String storedProcedureName) throws SQLException
	{
		this.storedProcedureName = storedProcedureName;
		connection = null;
		statement = null;
		openConnection();
		createStatement();
	}
	
	private void createStatement() throws SQLException
	{
		statement = connection.prepareCall("{call " + storedProcedureName + "}");
	}
	
	private void openConnection() throws SQLException
	{
		connection = DatabaseAbstractFactory.instance().makeConnectionManager().getDBConnection();
	}
	
	@Override
	public void cleanup()
	{
		try
		{
			if (null != statement)
			{
				statement.close();
			}
			if (null != connection)
			{
				if (!connection.isClosed())
				{
					connection.close();
				}
			}
		}
		catch (Exception e)
		{
			log.error("Error while cleaning up statement and connection resources, error: {}", e.getMessage());
		}
	}
	
	@Override
	public void setParameter(int paramIndex, String value) throws SQLException
	{
		statement.setString(paramIndex, value);
	}

	@Override
	public void setParameter(int paramIndex, Date value) throws SQLException
	{
		statement.setDate(paramIndex, value);
	}
	
	@Override
	public void registerOutputParameterString(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, java.sql.Types.VARCHAR);
	}
	
	@Override
	public void setParameter(int paramIndex, long value) throws SQLException
	{
		statement.setLong(paramIndex, value);
	}
	
	@Override
	public void registerOutputParameterLong(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, java.sql.Types.BIGINT);
	}
	
	@Override
	public ResultSet executeWithResults() throws SQLException
	{
		if (statement.execute())
		{
			return statement.getResultSet();
		}
		return null;
	}
	
	@Override
	public void execute() throws SQLException
	{
		statement.execute();
	}

	@Override
	public void addBatch() throws SQLException {
		statement.addBatch();
	}

	@Override
	public void executeBatch() throws SQLException {
		statement.executeBatch();
	}
}
