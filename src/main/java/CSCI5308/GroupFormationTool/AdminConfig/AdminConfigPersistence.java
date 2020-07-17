package CSCI5308.GroupFormationTool.AdminConfig;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.ICallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AdminConfigPersistence implements IAdminConfigPersistence {

    private Logger log = LoggerFactory.getLogger(AdminConfigPersistence.class);

    @Override
    public Map<String, String> getAllConfig() {
        log.trace("Fetching all configurations from database.");
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
            log.error("Error while fetching all configurations from database, error: {}", e.getMessage());
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return config;
    }

    @Override
    public boolean setConfig(String key, String value) {
        log.trace("Setting a configuration with key: {} to database.", key);
        ICallStoredProcedure proc = null;
        try {
            proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spSetConfig(?,?)");
            proc.setParameter(1, key);
            proc.setParameter(2, value);
            proc.execute();
            return true;
        } catch (SQLException e) {
            log.error("Error while setting a configuration with key: {} to database, error: {}", key, e.getMessage());
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public boolean addConfig(String key, String value) {
        log.trace("Adding a configuration with key: {} to database.", key);
        ICallStoredProcedure proc = null;
        try {
            proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spAddConfig(?,?)");
            proc.setParameter(1, key);
            proc.setParameter(2, value);
            proc.execute();
            return true;
        } catch (SQLException e) {
            log.error("Error while adding a configuration with key: {} to database, error: {}", key, e.getMessage());
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public boolean deleteConfig(String key) {
        log.trace("Deleting a configuration with key: {} to database.", key);
        ICallStoredProcedure proc = null;
        try {
            proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spDeleteConfig(?)");
            proc.setParameter(1, key);
            proc.execute();
            return true;
        } catch (SQLException e) {
            log.error("Error while deleting a configuration with key: {} from database, error: {}", key, e.getMessage());
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

}
