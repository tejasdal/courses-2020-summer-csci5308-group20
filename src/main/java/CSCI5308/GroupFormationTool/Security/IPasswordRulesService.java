package CSCI5308.GroupFormationTool.Security;

public interface IPasswordRulesService {

	// lazy load from config object inside jvm
	public PasswordRules getPasswordRules();
	
	public boolean isPasswordSatisfyRules(String password);
}
