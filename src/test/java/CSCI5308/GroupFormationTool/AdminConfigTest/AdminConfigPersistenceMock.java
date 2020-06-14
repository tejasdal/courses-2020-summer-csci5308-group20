package CSCI5308.GroupFormationTool.AdminConfigTest;

import java.util.HashMap;
import java.util.Map;

import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigPersistence;

public class AdminConfigPersistenceMock implements IAdminConfigPersistence {
	Map<String, String> map = new HashMap<>();

	@Override
	public Map<String, String> getAllConfig() {
		return map;
	}

	@Override
	public boolean setConfig(String key, String value) {
		map.put(key, value);
		return true;
	}

	@Override
	public boolean addConfig(String key, String value) {
		map.put(key, value);
		return true;
	}

	@Override
	public boolean deleteConfig(String key) {
		map.remove(key);
		return true;
	}
	
}
