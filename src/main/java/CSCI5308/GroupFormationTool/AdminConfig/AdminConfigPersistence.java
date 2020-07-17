package CSCI5308.GroupFormationTool.AdminConfig;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.ICallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AdminConfigPersistence implements IAdminConfigPersistence {

    @Override
    public Map<String, String> getAllConfig() {
        Map<String, String> config = new HashMap<>();
        ICallStoredProcedure proc = null;
        try {
            proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spLoadAllAdminConfig()");
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
        ICallStoredProcedure proc = null;
        try {
            proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spSetConfig(?,?)");
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
        ICallStoredProcedure proc = null;
        try {
            proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spAddConfig(?,?)");
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
        ICallStoredProcedure proc = null;
        try {
            proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spDeleteConfig(?)");
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
