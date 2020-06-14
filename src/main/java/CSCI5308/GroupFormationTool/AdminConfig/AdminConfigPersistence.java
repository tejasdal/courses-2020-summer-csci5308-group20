package CSCI5308.GroupFormationTool.AdminConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class AdminConfigPersistence implements IAdminConfigPersistence {

	@Override
	public Map<String, String> getAllConfig() {
		Map<String, String> config = new HashMap<>();
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spLoadAllAdminConfig()");
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					config.put(results.getString("CONFIG_KEY"), results.getString("CONFIG_VALUE"));
				}
			}
		} catch (SQLException e) {
			// Logging needed.
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return config;
	}

	@Override
	public boolean setConfig(String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addConfig(String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteConfig(String key) {
		// TODO Auto-generated method stub
		return false;
	}

}
