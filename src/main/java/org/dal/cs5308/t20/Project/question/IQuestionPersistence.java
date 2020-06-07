package org.dal.cs5308.t20.Project.question;

import java.sql.SQLException;
import java.util.List;

public interface IQuestionPersistence {

	public boolean addQuestion(Question question) throws SQLException;
	
	public boolean deleteQuestion(Long questionId) throws SQLException;
	
	public List<Question> getAllQuestionsForUser(Long userId) throws SQLException;
}
