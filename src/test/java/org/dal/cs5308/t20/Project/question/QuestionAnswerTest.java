package org.dal.cs5308.t20.Project.question;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionAnswerTest {
	
	static final QuestionAnswer BEGINNER_ANSWER = new QuestionAnswer("Beginner", 1);
	static final QuestionAnswer INTER_ANSWER = new QuestionAnswer("Intermediate", 2);
	static final QuestionAnswer EXPERT_ANSWER = new QuestionAnswer("Expert", 3);
	
	@Test
	public void constructorTest() {
		final int value = 1;
		final String answer = "test";
		
		final QuestionAnswer testAnswer = new QuestionAnswer(answer, value);
		assertEquals(value, testAnswer.getValue());
		assertEquals(answer, testAnswer.getAnswer());
	}
	
}
