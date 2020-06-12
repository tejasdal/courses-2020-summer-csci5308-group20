package CSCI5308.GroupFormationTool.Security;

import java.util.Set;

public class PasswordRules {

	public static final String CONFIG_MIN_LENGTH = "user.password.minLength";
	public static final String CONFIG_MAX_LENGTH = "user.password.minLength";
	public static final String CONFIG_MIN_UPPER_CHARS = "user.password.minLength";
	public static final String CONFIG_MIN_LOWER_CHARS = "user.password.minLength";
	public static final String CONFIG_MIN_SPECIAL_CHARS = "user.password.minLength";
	public static final String CONFIG_RESTRICTED_CHARS = "user.password.minLength";
	public static final String CONFIG_REMEMBERED = "user.password.remembered";

	private int minLength;
	private int maxLength;
	private int minUpperChars;
	private int minLowerChars;
	private int minSpecialChars;
	private int passwordsRemembered;
	private Set<Character> restrictedChars;

	public PasswordRules(int minLength, int maxLength, int minUpperChars, int minLowerChars, int minSpecialChars,
			int passwordsRemembered, Set<Character> restrictedChars) {
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.minUpperChars = minUpperChars;
		this.minLowerChars = minLowerChars;
		this.minSpecialChars = minSpecialChars;
		this.passwordsRemembered = passwordsRemembered;
		this.restrictedChars = restrictedChars;
	}

	public int getMinLength() {
		return minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public int getMinUpperChars() {
		return minUpperChars;
	}

	public int getMinLowerChars() {
		return minLowerChars;
	}

	public int getMinSpecialChars() {
		return minSpecialChars;
	}

	public int getPasswordsRemembered() {
		return passwordsRemembered;
	}

	public Set<Character> getRestrictedChars() {
		return restrictedChars;
	}

}
