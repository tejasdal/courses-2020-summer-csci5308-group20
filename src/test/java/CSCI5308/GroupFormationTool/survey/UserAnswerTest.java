package CSCI5308.GroupFormationTool.survey;

import CSCI5308.GroupFormationTool.SurveyManagement.UserAnswer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAnswerTest {

	@Test
	public void constructorTest() {
		final String answerRaw = "hello";
		final Integer answerIndex = 0;
		UserAnswer answer = new UserAnswer(answerRaw, answerIndex);
		assertEquals(answerRaw, answer.getAnswerRaw());
		assertEquals(answerIndex, answer.getAnswerIndex());
	}
	
	@Test
	public void compareIndexTest() {
		final UserAnswer a1 = new UserAnswer("hello", 1);
		final UserAnswer a2 = new UserAnswer("world", 1);
		final UserAnswer a3 = new UserAnswer("hello", 3);
		assertTrue(a1.compareIndex(a2));
		assertFalse(a1.compareIndex(a3));
	}
	
	@Test
	public void equalsTest() {
		final UserAnswer a1 = new UserAnswer("hello", 1);
		final UserAnswer a2 = new UserAnswer("world", 1);
		final UserAnswer a3 = new UserAnswer("hello", 3);
		assertTrue(a1.equals(a3));
		assertFalse(a1.equals(a2));
	}
}
