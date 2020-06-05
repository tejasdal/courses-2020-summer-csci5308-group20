package org.dal.cs5308.t20.Project;

import org.dal.cs5308.t20.Project.db.IDbUtil;
import org.dal.cs5308.t20.Project.db.MySqlDbUtil;
import org.dal.cs5308.t20.Project.user.IUserService;
import org.dal.cs5308.t20.Project.user.UserService;

public class Factory {
	private static final MySqlDbUtil mySqlDbUtil = new MySqlDbUtil();
	private static final IUserService userService = new UserService();
	
	public static IDbUtil getDbUtilInstance() {
		return mySqlDbUtil;
	}
	
	public static IUserService getUserService() {
		return userService;
	}
}
