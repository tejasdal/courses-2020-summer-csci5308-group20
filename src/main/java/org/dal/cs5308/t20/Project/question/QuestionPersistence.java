package org.dal.cs5308.t20.Project.question;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dal.cs5308.t20.Project.Factory;

public class QuestionPersistence implements IQuestionPersistence {

	private static final String ADD_QUESTION = "call spCreateQuestion(?,?,?,?,?,?)";
	private static final String ADD_QUESTION_OPTIONS = "call spCreateQuestionOption(?, ?, ?, ?)";
	private static final String DELETE_QUESTION = "delete from Question where ID = ?";
	private static final String GET_ALL_QUESTIONS_FOR_USER = "select *from Question where USER_ID = ?";
	private static final String GET_ALL_QUESTION_ANSWERS_FOR_USER = "select QuestionAnswer.* from QuestionAnswer inner join Question on Question.ID = QuestionAnswer.QUESTION_ID and Question.USER_ID = ?";

	@Override
	public boolean addQuestion(Question question) throws SQLException {
		try(PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection().prepareCall(ADD_QUESTION)){
			pstatement.setLong(1, question.getId());
			pstatement.setString(2, question.getTitle());
			pstatement.setString(3, question.getDescription());
			pstatement.setLong(4, question.getUserId());
			pstatement.setInt(5, question.getQuestionType());
			pstatement.setDate(6, question.getCreatedAt());
			pstatement.execute();
		}
		if (question.getQuestionOptions() != null && !question.getQuestionOptions().isEmpty()) {
			addQuestionOptions(question.getQuestionOptions(), question.getId());
		}
		return true;
	}

	private static void addQuestionOptions(List<QuestionOption> questionOptions, Long questionId) throws SQLException {
		try(PreparedStatement pstatement = Factory.getDbUtilInstance().getConnection().prepareCall(ADD_QUESTION_OPTIONS)){
			for (QuestionOption questionOption : questionOptions) {
				pstatement.setLong(1, questionOption.getId());
				pstatement.setLong(2, questionId);
				pstatement.setString(3, questionOption.getOption());
				pstatement.setInt(4, questionOption.getValue());
				pstatement.addBatch();
			}
			pstatement.executeBatch();
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

	private static Map<Long, List<QuestionOption>> getAllQuestionAnswersForUser(Long userId) throws SQLException {
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		final Map<Long, List<QuestionOption>> questionAnswers = new HashMap<>();
		try {
			pstatement = Factory.getDbUtilInstance().getConnection()
					.prepareStatement(GET_ALL_QUESTION_ANSWERS_FOR_USER);
			pstatement.setLong(1, userId);
			rs = pstatement.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong(org.dal.cs5308.t20.Project.dd.QuestionOption.ID);
				Long questionId = rs.getLong(org.dal.cs5308.t20.Project.dd.QuestionOption.QUESTION_ID);
				String answer = rs.getString(org.dal.cs5308.t20.Project.dd.QuestionOption.OPTIONS);
				int value = rs.getInt(org.dal.cs5308.t20.Project.dd.QuestionOption.VALUE);
				QuestionOption questionAnswer = new QuestionOption(id, answer, value);
				List<QuestionOption> answers = questionAnswers.get(questionId);
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
		Map<Long, List<QuestionOption>> questionAnswers = getAllQuestionAnswersForUser(userId);
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
				Date createdAt = rs.getDate(org.dal.cs5308.t20.Project.dd.Question.CREATED_AT);
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
