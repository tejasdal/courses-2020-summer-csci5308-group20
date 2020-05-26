package org.dal.cs5308.t20.Project;

import org.dal.cs5308.t20.Project.db.IDbUtil;
import org.dal.cs5308.t20.Project.db.MySqlDbUtil;

public class Factory {
	private static final MySqlDbUtil mySqlDbUtil = new MySqlDbUtil();

	public static IDbUtil getDbUtilInstance() {
		return mySqlDbUtil;
	}
}
