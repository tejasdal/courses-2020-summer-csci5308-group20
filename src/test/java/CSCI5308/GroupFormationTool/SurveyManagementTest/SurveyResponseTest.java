package CSCI5308.GroupFormationTool.SurveyManagementTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyResponse;
import CSCI5308.GroupFormationTool.SurveyManagement.SurveyFactory;
import CSCI5308.GroupFormationTool.SurveyManagement.UserAnswer;

public class SurveyResponseTest {
	
	@Test
	public void setUserAnswerTest() throws IOException {
		ISurveyResponse response = SurveyFactory.instance().createSurveyResponse();
		final Long questionId = 1L;
		final Long userId = 1L;
		final UserAnswer answer = new UserAnswer("Hello", 1);
		response.setUserAnswer(questionId, userId, answer);
		assertEquals(response.getUserAnswers(questionId, userId).get(0), answer);
	}
	
	@Test
	public void setUserAnswersTest() throws IOException {
		ISurveyResponse response = SurveyFactory.instance().createSurveyResponse();
		final Long questionId = 1L;
		final Long userId = 1L;
		final UserAnswer answer = new UserAnswer("Hello", 1);
		final List<UserAnswer> userAnswers = new ArrayList<>();
		userAnswers.add(answer);
		response.setUserAnswers(questionId, userId, userAnswers);
		assertEquals(response.getUserAnswers(questionId, userId).get(0), answer);
	}
}
