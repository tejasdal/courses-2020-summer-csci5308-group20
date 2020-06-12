package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserNotifications
{
	public void sendUserLoginCredentials(User user, String rawPassword);
}
