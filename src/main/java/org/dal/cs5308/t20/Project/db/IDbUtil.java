package org.dal.cs5308.t20.Project.db;

import java.sql.Connection;

public interface IDbUtil {

	public void initializeDb() throws Exception;

	public Connection getConnection();
}
