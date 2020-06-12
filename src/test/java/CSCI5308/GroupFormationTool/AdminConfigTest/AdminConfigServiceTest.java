package CSCI5308.GroupFormationTool.AdminConfigTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigPersistence;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigService;

public class AdminConfigServiceTest {

	@Test
	public void getAllConfigTest() {
		final String key = "key", value = "value";
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		adminConfigService.updateConfig(key, value, persistence);
		Map<String, String> config = adminConfigService.getAllConfig();
		assertEquals(value, config.get(key));
	}

	@Test
	public void loadAllConfigTest() {
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		Map<String, String> config = adminConfigService.loadAllConfig(persistence);
		assertEquals("value", config.get("key"));
	}

	@Test
	public void updateConfigTest() {
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		final String key = "key", value = "value";
		assertTrue(adminConfigService.updateConfig(key, value, persistence));
		assertEquals(value, adminConfigService.loadAllConfig(persistence).get(key));
	}
}
