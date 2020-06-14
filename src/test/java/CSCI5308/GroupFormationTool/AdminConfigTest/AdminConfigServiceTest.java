package CSCI5308.GroupFormationTool.AdminConfigTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AdminConfig.DuplicateKeyException;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigPersistence;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigService;
import CSCI5308.GroupFormationTool.AdminConfig.KeyNotFoundException;

public class AdminConfigServiceTest {

	@Test
	public void addConfigTest() throws DuplicateKeyException {
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		final String key = "key1", value = "value1";
		assertTrue(adminConfigService.addConfig(key, value, persistence));
		assertEquals(value, adminConfigService.loadAllConfig(persistence).get(key));
	}

	@Test
	public void getAllConfigTest() throws DuplicateKeyException {
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		final String key = "key2", value = "value2";
		assertTrue(adminConfigService.addConfig(key, value, persistence));
		assertEquals(value, adminConfigService.getAllConfig().get(key));
	}

	@Test
	public void loadAllConfigTest() throws DuplicateKeyException {
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		final String key = "key3", value = "value3";
		assertTrue(adminConfigService.addConfig(key, value, persistence));
		assertEquals(value, adminConfigService.loadAllConfig(persistence).get(key));
	}

	@Test
	public void updateConfigTest() throws KeyNotFoundException, DuplicateKeyException {
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		final String key = "key4", value = "value4", value2 = "value5";
		assertTrue(adminConfigService.addConfig(key, value, persistence));
		assertTrue(adminConfigService.updateConfig(key, value2, persistence));
		assertEquals(value2, adminConfigService.getAllConfig().get(key));
	}

	@Test
	public void deleteConfigTest() throws KeyNotFoundException, DuplicateKeyException {
		final IAdminConfigPersistence persistence = new AdminConfigPersistenceMock();
		final IAdminConfigService adminConfigService = SystemConfig.instance().getAdminConfigService();
		final String key = "key5", value = "value5";
		assertTrue(adminConfigService.addConfig(key, value, persistence));
		assertTrue(adminConfigService.deleteConfig(key, persistence));
		String receivedValue = adminConfigService.loadAllConfig(persistence).get(key);
		assertNull(receivedValue);
	}
}
