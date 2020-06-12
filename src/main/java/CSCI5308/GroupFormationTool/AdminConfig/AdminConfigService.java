package CSCI5308.GroupFormationTool.AdminConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AdminConfigService implements IAdminConfigService {

	public static Map<String, String> config = new ConcurrentHashMap<>();

	@Override
	public Map<String, String> getAllConfig() {
		return config;
	}

	@Override
	public Map<String, String> loadAllConfig(IAdminConfigPersistence persistence) {
		config = persistence.getAllConfig();
		return config;
	}

	@Override
	public boolean updateConfig(String key, String value, IAdminConfigPersistence persistence) {
		// update using persistence class, update value in 'config' map
		boolean result = persistence.setConfig(key, value);
		if (result) {			
			config.put(key, value);
		}
		return result;
	}

}
