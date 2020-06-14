package CSCI5308.GroupFormationTool.AdminConfig;

import java.util.Map;

public interface IAdminConfigService {
	// Return map object from JVM
	public Map<String, String> getAllConfig();
	
	// Load map from DB and return
	public Map<String, String> loadAllConfig(IAdminConfigPersistence persistence);
	
	public boolean updateConfig(String key, String value, IAdminConfigPersistence persistence) throws KeyNotFoundException;
	
	public boolean addConfig(String key, String value, IAdminConfigPersistence persistence);
	
	public boolean deleteConfig(String key, IAdminConfigPersistence persistence) throws KeyNotFoundException;
}
