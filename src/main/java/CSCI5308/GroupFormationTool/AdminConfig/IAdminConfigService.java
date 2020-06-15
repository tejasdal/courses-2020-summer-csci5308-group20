package CSCI5308.GroupFormationTool.AdminConfig;

import CSCI5308.GroupFormationTool.CustomExceptions.DuplicateKeyException;
import CSCI5308.GroupFormationTool.CustomExceptions.KeyNotFoundException;

import java.util.Map;

public interface IAdminConfigService {
    // Return map object from JVM
    Map<String, String> getAllConfig();

    // Load map from DB and return
    Map<String, String> loadAllConfig(IAdminConfigPersistence persistence);

    boolean updateConfig(String key, String value, IAdminConfigPersistence persistence) throws KeyNotFoundException;

    boolean addConfig(String key, String value, IAdminConfigPersistence persistence) throws DuplicateKeyException;

    boolean deleteConfig(String key, IAdminConfigPersistence persistence) throws KeyNotFoundException;
}
