package CSCI5308.GroupFormationTool.AdminConfig;

import java.util.Map;

public interface IAdminConfigPersistence {
	
	public Map<String, String> getAllConfig();
	
	public boolean setConfig(String key, String value);
}
