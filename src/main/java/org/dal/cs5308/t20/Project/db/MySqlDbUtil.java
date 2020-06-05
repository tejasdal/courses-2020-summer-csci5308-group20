package org.dal.cs5308.t20.Project.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dal.cs5308.t20.Project.AppProperties;
import org.dal.cs5308.t20.Project.StreamUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;

public class MySqlDbUtil implements IDbUtil {

	private Connection connection;

	public void initializeDb() throws SQLException, IOException {
		initMySqlConnection();
		loadSqlQueries();
	}

	private void initMySqlConnection() throws SQLException, IOException {
		String environment = AppProperties.properties.getProperty("environment");
		String dbHost = AppProperties.properties.getProperty("mysql." + environment + ".host");
		String dbPort = AppProperties.properties.getProperty("mysql." + environment + ".port");
		String dbUser = AppProperties.properties.getProperty("mysql." + environment + ".user");
		String dbPassword = AppProperties.properties.getProperty("mysql." + environment + ".password");
		String dbName = AppProperties.properties.getProperty("mysql." + environment + ".dbName");
		connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName
				+ "?createDatabaseIfNotExist=true&serverTimezone=UTC", dbUser, dbPassword);
		System.out.println("MySQL connection established.");
	}

	private void loadSqlQueries() throws SQLException, IOException {
		String query = "show tables";
		ResultSet rs = executeQuery(query);
		if (!rs.next()) {
			ClassPathResource file = new ClassPathResource("sql-queries.json");
			JSONObject json = new JSONObject(StreamUtil.readFromInputStream(file.getInputStream()));
			JSONArray ddQueries = json.getJSONObject("queries").getJSONArray("dd");
			JSONArray dmlQueries = json.getJSONObject("queries").getJSONArray("dml");
			for (int i = 0; i < ddQueries.length(); i++) {
				query = ddQueries.getString(i);
				execute(query);
			}
			System.out.println("Created " + ddQueries.length() + " tables");
			System.out.println("DD Queries executed");
			for (int i = 0; i < dmlQueries.length(); i++) {
				query = dmlQueries.getString(i);
				executeUpdate(query);
			}
			System.out.println("Executed " + dmlQueries.length() + " DML queries");
		}
	}

	private boolean execute(String query) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.execute(query);
	}

	private ResultSet executeQuery(String query) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeQuery(query);
	}

	private int executeUpdate(String query) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeUpdate(query);
	}

	public Connection getConnection() {
		return connection;
	}
}

