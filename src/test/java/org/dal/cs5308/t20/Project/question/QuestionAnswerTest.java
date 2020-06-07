package org.dal.cs5308.t20.Project.question;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionAnswerTest {
	
	static final QuestionAnswer BEGINNER_ANSWER = new QuestionAnswer(1L, "Beginner", 1);
	static final QuestionAnswer INTER_ANSWER = new QuestionAnswer(2L, "Intermediate", 2);
	static final QuestionAnswer EXPERT_ANSWER = new QuestionAnswer(3L, "Expert", 3);
	
	@Test
	public void constructorTest() {
		final int value = 1;
		final String answer = "test";
		final Long id = 1L;
		
		final QuestionAnswer testAnswer = new QuestionAnswer(1L, answer, value);
		assertEquals(value, testAnswer.getValue());
		assertEquals(answer, testAnswer.getAnswer());
		assertEquals(id, testAnswer.getId());

	}
	
}
