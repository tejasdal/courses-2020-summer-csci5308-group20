package org.dal.cs5308.t20.Project.question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dal.cs5308.t20.Project.Factory;

public class QuestionPersistence implements IQuestionPersistence {

	private static final String ADD_QUESTION = "insert into Question(ID, TITLE, DESCRIPTION, TYPE_ID, USER_ID, CREATED_AT) values(?,?,?,?,?,?)";
	private static final String ADD_QUESTION_ANSWERS = "insert into QuestionAnswer(ID, QUESTION_ID, ANSWER, VALUE) values(?, ?, ?, ?)";
	private static final String DELETE_QUESTION = "delete from Question where ID = ?";
	private static final String GET_ALL_QUESTIONS_FOR_USER = "select *from Question where USER_ID = ?";
	private static final String GET_ALL_QUESTION_ANSWERS_FOR_USER = "select QuestionAnswer.* from QuestionAnswer inner join Question on Question.ID = QuestionAnswer.QUESTION_ID and Question.USER_ID = ?";

	@Override
	public boolean addQuestion(Question question) throws SQLException {
		PreparedStatement pstatement = null;
		try {
			pstatement = Factory.getDbUtilInstance().getConnection().prepareCall(ADD_QUESTION);
			pstatement.setLong(1, question.getId());
			pstatement.setString(2, question.getTitle());
			pstatement.setString(3, question.getDescription());
			pstatement.setInt(4, question.getQuestionType());
			pstatement.setLong(5, question.getUserId());
			pstatement.setLong(6, question.getCreatedAt());
			pstatement.execute();
		} finally {
			if (pstatement != null) {
				pstatement.close();
			}
		}
		if (question.getPresetAnswers() != null && question.getPresetAnswers().isEmpty()) {
			addQuestionAnswers(question.getPresetAnswers(), question.getId());
		}
		return true;
	}

	private static void addQuestionAnswers(List<QuestionAnswer> presetAnswers, Long questionId) throws SQLException {
		PreparedStatement pstatement = null;
		try {
			pstatement = Factory.getDbUtilInstance().getConnection().prepareCall(ADD_QUESTION_ANSWERS);
			for (QuestionAnswer questionAnswer : presetAnswers) {
				pstatement.setLong(1, questionAnswer.getId());
				pstatement.setLong(2, questionId);
				pstatement.setString(3, questionAnswer.getAnswer());
				pstatement.setInt(4, questionAnswer.getValue());
				pstatement.addBatch();
			}
			pstatement.executeBatch();
		} finally {
			if (pstatement != null) {
				pstatement.close();
			}
		}
	}

	@Override
	public boolean deleteQuestion(Long questionId) throws SQLException {
		PreparedStatement pstatement = null;
		try {
			pstatement = Factory.getDbUtilInstance().getConnection().prepareStatement(DELETE_QUESTION);
			pstatement.setLong(1, questionId);
			pstatement.executeUpdate();
		} finally {
			if (pstatement != null) {
				pstatement.close();
			}
		}
		return true;
	}

	private static Map<Long, List<QuestionAnswer>> getAllQuestionAnswersForUser(Long userId) throws SQLException {
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		final Map<Long, List<QuestionAnswer>> questionAnswers = new HashMap<>();
		try {
			pstatement = Factory.getDbUtilInstance().getConnection()
					.prepareStatement(GET_ALL_QUESTION_ANSWERS_FOR_USER);
			pstatement.setLong(1, userId);
			rs = pstatement.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong(org.dal.cs5308.t20.Project.dd.QuestionAnswer.ID);
				Long questionId = rs.getLong(org.dal.cs5308.t20.Project.dd.QuestionAnswer.QUESTION_ID);
				String answer = rs.getString(org.dal.cs5308.t20.Project.dd.QuestionAnswer.ANSWER);
				int value = rs.getInt(org.dal.cs5308.t20.Project.dd.QuestionAnswer.VALUE);
				QuestionAnswer questionAnswer = new QuestionAnswer(id, answer, value);
				List<QuestionAnswer> answers = questionAnswers.get(questionId);
				if (answers == null) {
					answers = new ArrayList<>();
					questionAnswers.put(questionId, answers);
				}
				answers.add(questionAnswer);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstatement != null) {
				pstatement.close();
			}
		}
		return questionAnswers;
	}

	@Override
	public List<Question> getAllQuestionsForUser(Long userId) throws SQLException {
		Map<Long, List<QuestionAnswer>> questionAnswers = getAllQuestionAnswersForUser(userId);
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		final List<Question> questions = new ArrayList<>();
		try {
			pstatement = Factory.getDbUtilInstance().getConnection().prepareStatement(GET_ALL_QUESTIONS_FOR_USER);
			pstatement.setLong(1, userId);
			rs = pstatement.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong(org.dal.cs5308.t20.Project.dd.Question.ID);
				String title = rs.getString(org.dal.cs5308.t20.Project.dd.Question.TITLE);
				String description = rs.getString(org.dal.cs5308.t20.Project.dd.Question.DESCRIPTION);
				int questionType = rs.getInt(org.dal.cs5308.t20.Project.dd.Question.TYPE_ID);
				Long createdAt = rs.getLong(org.dal.cs5308.t20.Project.dd.Question.CREATED_AT);
				Question question = new Question(id, title, description, userId, questionType, createdAt,
						questionAnswers.get(id));
				questions.add(question);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstatement != null) {
				pstatement.close();
			}
		}
		return questions;
	}

}
