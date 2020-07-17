package CSCI5308.GroupFormationTool.AdminConfigTest;

import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigPersistence;
import CSCI5308.GroupFormationTool.AdminConfig.IAdminConfigService;
import CSCI5308.GroupFormationTool.CustomExceptions.DuplicateKeyException;
import CSCI5308.GroupFormationTool.CustomExceptions.KeyNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdminConfigServiceTest {

	private static IAdminConfigPersistence persistence;
	private static IAdminConfigService adminConfigService;

	@BeforeAll
	public static void setUp()
	{
		AdminConfigTestAbstractFactory adminConfigTestAbstractFactory = new AdminConfigTestConcreteFactory();
		persistence = adminConfigTestAbstractFactory.makeAdminConfigPersistence();
		adminConfigService = adminConfigTestAbstractFactory.makeAdminConfigService();
	}

	@Test
	public void addConfigTest() throws DuplicateKeyException {
		final String key = "key1", value = "value1";
		assertTrue(adminConfigService.addConfig(key, value, persistence));
		assertEquals(value, adminConfigService.loadAllConfig(persistence).get(key));
	}

	@Test
	public void getAllConfigTest() throws DuplicateKeyException {
		final String key = "key2", value = "value2";
		assertTrue(adminConfigService.addConfig(key, value, persistence));
		assertEquals(value, adminConfigService.getAllConfig().get(key));
	}

	@Test
	public void loadAllConfigTest() throws DuplicateKeyException {
		final String key = "key3", value = "value3";
		assertTrue(adminConfigService.addConfig(key, value, persistence));
		assertEquals(value, adminConfigService.loadAllConfig(persistence).get(key));
	}

	@Test
	public void updateConfigTest() throws KeyNotFoundException, DuplicateKeyException {
		final String key = "key4", value = "value4", value2 = "value5";
		assertTrue(adminConfigService.addConfig(key, value, persistence));
		assertTrue(adminConfigService.updateConfig(key, value2, persistence));
		assertEquals(value2, adminConfigService.getAllConfig().get(key));
	}

	@Test
	public void deleteConfigTest() throws KeyNotFoundException, DuplicateKeyException {
		final String key = "key5", value = "value5";
		assertTrue(adminConfigService.addConfig(key, value, persistence));
		assertTrue(adminConfigService.deleteConfig(key, persistence));
		String receivedValue = adminConfigService.loadAllConfig(persistence).get(key);
		assertNull(receivedValue);
	}
}
