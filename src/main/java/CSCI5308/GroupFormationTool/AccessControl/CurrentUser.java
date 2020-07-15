package CSCI5308.GroupFormationTool.AccessControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import CSCI5308.GroupFormationTool.SystemConfig;

public class CurrentUser
{
	private Logger log = LoggerFactory.getLogger(CurrentUser.class);
	private static CurrentUser uniqueInstance = null;
	
	private CurrentUser()
	{
		
	}
	
	public static CurrentUser instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new CurrentUser();
		}
		return uniqueInstance;
	}
	
	public User getCurrentAuthenticatedUser()
	{
		log.trace("Fetching current authenticated user.");
		IUserPersistence userDB = SystemConfig.instance().getUserDB();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated())
		{
			String bannerID = authentication.getPrincipal().toString();
			User u = new User();
			userDB.loadUserByBannerID(bannerID, u);
			if (u.isValidUser())
			{
				return u;
			}
		}
		log.debug("Instance of current authenticated user is null.");
		return null;
	}
}
