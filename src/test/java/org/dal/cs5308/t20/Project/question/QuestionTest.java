package org.dal.cs5308.t20.Project.question;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.dal.cs5308.t20.Project.user.UserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {
	static final Question DEFAULT_QUESTION = new Question(1L, "Credit Hour",
			"How many credit hours are you attempting this term ?", UserTest.id, Question.NUMERIC,
			System.currentTimeMillis(), new ArrayList<QuestionOption>());

	@Test
	public void constructorTest() {
		final Long questionId = 1L;
		final String title = "Test question";
		final String description = "This is a test question";
		final Long userId = 1L;
		final int questionType = Question.NUMERIC;
		final List<QuestionOption> presetAnswers = new ArrayList<>();
		final Long createdAt = System.currentTimeMillis();

		presetAnswers.add(QuestionAnswerTest.BEGINNER_ANSWER);
		presetAnswers.add(QuestionAnswerTest.INTER_ANSWER);
		presetAnswers.add(QuestionAnswerTest.EXPERT_ANSWER);

		Question testQuestion = new Question(questionId, title, description, userId, questionType, createdAt,
				presetAnswers);
		assertEquals(questionId, testQuestion.getId());
		assertEquals(title, testQuestion.getTitle());
		assertEquals(description, testQuestion.getDescription());
		assertEquals(userId, testQuestion.getUserId());
		assertEquals(questionType, testQuestion.getQuestionType());
		assertEquals(createdAt, testQuestion.getCreatedAt());
		assertEquals(presetAnswers.size(), testQuestion.getPresetAnswers().size());
		assertEquals(presetAnswers.get(0).getOption(), testQuestion.getPresetAnswers().get(0).getOption());
		assertEquals(presetAnswers.get(0).getValue(), testQuestion.getPresetAnswers().get(0).getValue());
		assertEquals(presetAnswers.get(1).getOption(), testQuestion.getPresetAnswers().get(1).getOption());
		assertEquals(presetAnswers.get(1).getValue(), testQuestion.getPresetAnswers().get(1).getValue());
		assertEquals(presetAnswers.get(2).getOption(), testQuestion.getPresetAnswers().get(2).getOption());
		assertEquals(presetAnswers.get(2).getValue(), testQuestion.getPresetAnswers().get(2).getValue());
	}
}
