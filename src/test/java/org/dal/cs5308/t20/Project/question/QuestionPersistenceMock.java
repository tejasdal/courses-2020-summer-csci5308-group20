package org.dal.cs5308.t20.Project.question;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionPersistenceMock implements IQuestionPersistence {

	@Override
	public boolean addQuestion(Question question) throws SQLException {
		return true;
	}

	@Override
	public boolean deleteQuestion(Long questionId) throws SQLException {
		return true;
	}

	@Override
	public List<Question> getAllQuestionsForUser(Long userId) throws SQLException {
		if (userId == 1) {
			List<Question> questions = new ArrayList<>();
			questions.add(QuestionTest.DEFAULT_QUESTION);
			return questions;
		}
		return null;
	}
	
}
