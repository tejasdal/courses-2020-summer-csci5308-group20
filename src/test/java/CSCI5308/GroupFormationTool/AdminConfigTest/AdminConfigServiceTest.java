package CSCI5308.GroupFormationTool.AdminConfigTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigPersistence;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigService;
import CSCI5308.GroupFormationTool.AdminConfig.KeyNotFoundException;

public class AdminConfigServiceTest {

	@Test
	public void getAllConfigTest() throws KeyNotFoundException {
		final String key = "key", value = "value";
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		adminConfigService.addConfig(key, value, persistence);
		Map<String, String> config = adminConfigService.getAllConfig();
		assertEquals(value, config.get(key));
	}

	@Test
	public void loadAllConfigTest() {
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		final String key = "key", value = "value";
		adminConfigService.addConfig(key, value, persistence);
		Map<String, String> config = adminConfigService.loadAllConfig(persistence);
		assertEquals(value, config.get(key));
	}

	@Test
	public void updateConfigTest() throws KeyNotFoundException {
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		final String key = "key", value1 = "value", value2 = "value2";
		assertTrue(adminConfigService.addConfig(key, value1, persistence));
		assertTrue(adminConfigService.updateConfig(key, value2, persistence));
		assertEquals(value2, adminConfigService.loadAllConfig(persistence).get(key));
	}

	@Test
	public void addConfigTest() throws KeyNotFoundException {
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		final String key = "key", value = "value";
		assertTrue(adminConfigService.addConfig(key, value, persistence));
		assertEquals(value, adminConfigService.loadAllConfig(persistence).get(key));
	}

	@Test
	public void deleteConfigTest() throws KeyNotFoundException {
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		final String key = "key", value = "value";
		adminConfigService.addConfig(key, value, persistence);
		assertTrue(adminConfigService.deleteConfig(key, persistence));
		String receivedValue = adminConfigService.loadAllConfig(persistence).get(key);
		assertNull(receivedValue);
	}
}
