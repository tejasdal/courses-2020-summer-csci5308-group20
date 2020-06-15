package CSCI5308.GroupFormationTool.AdminConfig;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spSetConfig(?,?)");
            proc.setParameter(1, key);
            proc.setParameter(2, value);
            proc.execute();
            return true;
        } catch (SQLException e) {
            return false;
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public boolean addConfig(String key, String value) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spAddConfig(?,?)");
            proc.setParameter(1, key);
            proc.setParameter(2, value);
            proc.execute();
            return true;
        } catch (SQLException e) {
            return false;
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public boolean deleteConfig(String key) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spDeleteConfig(?)");
            proc.setParameter(1, key);
            proc.execute();
            return true;
        } catch (SQLException e) {
            return false;
            // Logging needed.
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

}
