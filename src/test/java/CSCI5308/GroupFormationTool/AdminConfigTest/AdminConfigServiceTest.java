package CSCI5308.GroupFormationTool.AdminConfigTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigPersistence;

public class AdminConfigServiceTest {

	@Test
	public void getAllConfigTest() {
		Map<String, String> config = SystemConfig.instance().getAdminConfigService().getAllConfig();
		assertTrue(config.isEmpty());
	}

	@Test
	public void loadAllConfigTest() {
		IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		Map<String, String> config = SystemConfig.instance().getAdminConfigService().loadAllConfig(persistence);
		assertEquals("value", config.get("key"));
	}

	@Test
	public void updateConfigTest() {
		IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		SystemConfig.instance().getAdminConfigService().loadAllConfig(persistence);
		final String key = "key", value = "value";
		assertTrue(SystemConfig.instance().getAdminConfigService().updateConfig(key, value, persistence));
		assertEquals(value, SystemConfig.instance().getAdminConfigService().loadAllConfig(persistence).get(key));
	}

}
