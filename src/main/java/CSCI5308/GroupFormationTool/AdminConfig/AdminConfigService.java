package CSCI5308.GroupFormationTool.AdminConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AdminConfigService implements IAdminConfigService {

	private Logger log = LoggerFactory.getLogger(AdminConfigService.class);
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
		if (config.containsKey(key) == false) {
			throw new IllegalArgumentException("Config with key '" + key + "' not found");
		}
		boolean result = persistence.setConfig(key, value);
		if (result) {
			config.put(key, value);
		}
		return result;
	}

	@Override
	public boolean addConfig(String key, String value, IAdminConfigPersistence persistence) {
		// add using persistence class, update value in 'config' map
		if (config.containsKey(key)) {
			throw new IllegalArgumentException("Key '" + key + "' already exists");
		}
		boolean result = persistence.addConfig(key, value);
		if (result) {
			config.put(key, value);
		}
		return result;
	}

	@Override
	public boolean deleteConfig(String key, IAdminConfigPersistence persistence) {
		if (config.containsKey(key) == false) {
			throw new IllegalArgumentException("Config with key '" + key + "' not found");
		}
		boolean result = persistence.deleteConfig(key);
		if (result) {
			config.remove(key);
		}
		return result;
	}

}
