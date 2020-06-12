package org.dal.cs5308.t20.Project;

import org.dal.cs5308.t20.Project.db.IDbUtil;
import org.dal.cs5308.t20.Project.db.MySqlDbUtil;
import org.dal.cs5308.t20.Project.question.IQuestionPersistence;
import org.dal.cs5308.t20.Project.question.IQuestionService;
import org.dal.cs5308.t20.Project.question.QuestionPersistence;
import org.dal.cs5308.t20.Project.question.QuestionService;
import org.dal.cs5308.t20.Project.user.IUserService;
import org.dal.cs5308.t20.Project.user.UserService;

public class Factory {
	private static final MySqlDbUtil mySqlDbUtil = new MySqlDbUtil();
	private static final IUserService userService = new UserService();
	private static final IQuestionPersistence questionPersistence = new QuestionPersistence();
	private static final IQuestionService questionService = new QuestionService();
	
	public static IDbUtil getDbUtilInstance() {
		return mySqlDbUtil;
	}
	
	public static IUserService getUserService() {
		return userService;
	}

	public static IQuestionPersistence getQuestionPersistence(){ return  questionPersistence;}

	public static IQuestionService getQuestionService(){ return questionService; }
}
