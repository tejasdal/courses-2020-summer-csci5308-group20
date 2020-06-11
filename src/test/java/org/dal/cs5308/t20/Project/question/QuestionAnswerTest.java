package org.dal.cs5308.t20.Project.question;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionAnswerTest {
	
	static final QuestionOption BEGINNER_ANSWER = new QuestionOption(1L, "Beginner", 1);
	static final QuestionOption INTER_ANSWER = new QuestionOption(2L, "Intermediate", 2);
	static final QuestionOption EXPERT_ANSWER = new QuestionOption(3L, "Expert", 3);
	
	@Test
	public void constructorTest() {
		final int value = 1;
		final String answer = "test";
		final Long id = 1L;
		
		final QuestionOption testAnswer = new QuestionOption(1L, answer, value);
		assertEquals(value, testAnswer.getValue());
		assertEquals(answer, testAnswer.getOption());
		assertEquals(id, testAnswer.getId());

	}
	
}
