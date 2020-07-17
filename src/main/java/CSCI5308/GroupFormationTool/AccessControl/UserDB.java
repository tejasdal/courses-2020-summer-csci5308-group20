package CSCI5308.GroupFormationTool.AccessControl;

import java.sql.ResultSet;
import java.sql.SQLException;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.ICallStoredProcedure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDB implements IUserPersistence
{
	private Logger log = LoggerFactory.getLogger(UserDB.class);

	public void loadUserByID(long id, User user)
	{
		log.trace("Loading a user by ID: {} from database.", id);
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spLoadUser(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String password = results.getString(3);
					String firstName = results.getString(4);
					String lastName = results.getString(5);
					String email = results.getString(6);
					user.setID(userID);
					user.setBannerID(bannerID);
					user.setPassword(password);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setEmail(email);
				}
			}
		}
		catch (SQLException e)
		{
			log.error("Error while loading user by ID: {} from database, error: {}", id, e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
	}

	public void loadUserByBannerID(String bannerID, User user)
	{
		log.trace("Loading user by banner ID: {} from database.", bannerID);
		ICallStoredProcedure proc = null;
		long userID = -1;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFindUserByBannerID(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					userID = results.getLong(1);
				}
			}
		}
		catch (SQLException e)
		{
			log.error("Error while loading user by banner ID: {} from database, error: {}", bannerID, e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		// If we found the ID load the full details.
		if (userID > -1)
		{
			loadUserByID(userID, user);
		}
	}
	
	public boolean createUser(User user)
	{
		log.trace("Creating a new user with BannerID: {} in database.", user.getBannerID());
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spCreateUser(?, ?, ?, ?, ?, ?)");
			proc.setParameter(1, user.getBannerID());
			proc.setParameter(2, user.getPassword());
			proc.setParameter(3, user.getFirstName());
			proc.setParameter(4, user.getLastName());
			proc.setParameter(5, user.getEmail());
			proc.registerOutputParameterLong(6);
			proc.execute();
		}
		catch (SQLException e)
		{
			log.error("Error while creating a new user with ID: {} in database, error: {}", user.getID(), e.getMessage());
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
	}
	
	public boolean updateUser(User user)
	{
		return false;
	}
}
