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
	public boolean updateConfig(String key, String value, IAdminConfigPersistence persistence) throws KeyNotFoundException {
		// update using persistence class, update value in 'config' map
		if (!config.containsKey(key)) {
			throw new KeyNotFoundException("Config with key '"+key+"' not found");
		}
		boolean result = persistence.setConfig(key, value);
		if (result) {			
			config.put(key, value);
		}
		// Logging required
		return result;
	}

	@Override
	public boolean addConfig(String key, String value, IAdminConfigPersistence persistence) {
		// add using persistence class, update value in 'config' map
		boolean result = persistence.addConfig(key, value);
		if (result) {
			config.put(key, value);
		}
		// Logging required
		return result;
	}

	@Override
	public boolean deleteConfig(String key, IAdminConfigPersistence persistence) throws KeyNotFoundException {
		if (!config.containsKey(key)) {
			throw new KeyNotFoundException("Config with key '"+key+"' not found");
		}
		boolean result = persistence.deleteConfig(key);
		if (result) {
			config.remove(key);
		}
		// Logging required
		return result;
	}

}
