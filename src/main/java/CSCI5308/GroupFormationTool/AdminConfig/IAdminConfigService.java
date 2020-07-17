package CSCI5308.GroupFormationTool.AdminConfig;


import java.util.Map;

public interface IAdminConfigService {
    // Return map object from JVM
    Map<String, String> getAllConfig();

    // Load map from DB and return
    Map<String, String> loadAllConfig(IAdminConfigPersistence persistence);

    boolean updateConfig(String key, String value, IAdminConfigPersistence persistence);

    boolean addConfig(String key, String value, IAdminConfigPersistence persistence);

    boolean deleteConfig(String key, IAdminConfigPersistence persistence);
}
