package CSCI5308.GroupFormationTool.SecurityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.Security.PasswordRules;

public class PasswordRulesTest {

	@Test
	public void constructorTest() {
		final int minLength = 5;
		final int maxLength = 6;
		final int minUpperChars = 7;
		final int minLowerChars = 8;
		final int minSpecialChars = 9;
		final int passwordsRemembered = 10;
		final Character restrictedChar = '#';
		final Set<Character> restrictedChars = new HashSet<>();
		restrictedChars.add(restrictedChar);
		PasswordRules rules = new PasswordRules(minLength, maxLength, minUpperChars, minLowerChars, minSpecialChars,
				passwordsRemembered, restrictedChars);
		assertEquals(minLength, rules.getMinLength());
		assertEquals(maxLength, rules.getMaxLength());
		assertEquals(minUpperChars, rules.getMinUpperChars());
		assertEquals(minLowerChars, rules.getMinLowerChars());
		assertEquals(minSpecialChars, rules.getMinSpecialChars());
		assertEquals(passwordsRemembered, rules.getPasswordsRemembered());
		assertTrue(rules.getRestrictedChars().contains(restrictedChar));
	}
}
