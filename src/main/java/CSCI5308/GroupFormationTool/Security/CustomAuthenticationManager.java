package CSCI5308.GroupFormationTool.Security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.*;

public class CustomAuthenticationManager implements AuthenticationManager
{
	private Logger log = LoggerFactory.getLogger(CustomAuthenticationManager.class);
	private static final String ADMIN_BANNER_ID = "B-000000";
	
	private Authentication checkAdmin(String password, User u, Authentication authentication) throws AuthenticationException
	{
		// The admin password is not encrypted because it is hardcoded in the DB.
		if (password.equals(u.getPassword()))
		{
			// Grant ADMIN rights system-wide, this is used to protect controller mappings.
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(new SimpleGrantedAuthority("ADMIN"));
			// Return valid authentication token.
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
																			authentication.getCredentials(),
																			rights);
			return token;
		}
		else
		{
			log.info("Bad credentials of user with banner ID: {} provided for authentication", u.getBannerID());
			throw new BadCredentialsException("1000");
		}
	}
	
	private Authentication checkNormal(String password, User u, Authentication authentication) throws AuthenticationException
	{
		IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();
		if (passwordEncryption.matches(password, u.getPassword()))
		{
			// Grant USER rights system-wide, this is used to protect controller mappings.
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(new SimpleGrantedAuthority("USER"));
			// Return valid authentication token.
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
																			authentication.getCredentials(),
																			rights);
			return token;
		}
		else
		{
			log.info("Bad credentials of user with banner ID: {} provided for authentication", u.getBannerID());
			throw new BadCredentialsException("1000");
		}
	}
	
	// Authenticate against our database using the input banner ID and password.
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String bannerID = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		IUserPersistence userDB = SystemConfig.instance().getUserDB();
		User u;
		try
		{
			u = new User(bannerID, userDB);
		}
		catch (Exception e)
		{
			log.error("Error while authenticating user with banner ID: {}, error: {}", bannerID, e.getMessage());
			throw new AuthenticationServiceException("1000");
		}
		if (u.isValidUser())
		{
			if (bannerID.toUpperCase().equals(ADMIN_BANNER_ID))
			{
				return checkAdmin(password, u, authentication);
			}
			else
			{
				return checkNormal(password, u, authentication);
			}
		}
		else
		{
			// No user with this banner id found.
			log.info("User with banned ID: {} not found in database.", bannerID);
			throw new BadCredentialsException("1001");
		}			
	}
}
