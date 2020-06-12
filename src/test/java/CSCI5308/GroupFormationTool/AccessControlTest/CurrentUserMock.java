package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;

public class CurrentUserMock 
{
	public User getCurrentAuthenticatedUser() 
	{
		IUserPersistence userDB = new UserDBMock();
		String bannerID = "B00000000";
		User u = new User();
		userDB.loadUserByBannerID(bannerID, u);
		return u;
	}

}
