package CSCI5308.GroupFormationTool.SurveyManagement.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyResponse;
import CSCI5308.GroupFormationTool.SurveyManagement.SurveyFactory;
import CSCI5308.GroupFormationTool.SurveyManagement.SurveyQuestion;
import CSCI5308.GroupFormationTool.SurveyManagement.UserAnswer;
import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.IMatchCriteria;

public class BacktrackStandardDeviationTest {
	
	@Test
	public void createGroupsTest() throws IOException {
		final int maxUsersPerGroup = 2;

		final List<User> users = new ArrayList<>();
		for (int i = 1; i <= 4; i++) {
			User user = new User();
			user.setId(i);
			users.add(user);
		}

		final List<SurveyQuestion> questions = new ArrayList<>();
		final IMatchCriteria similarityCriteria = SystemConfig.instance().getMatchCriteriaFactory()
				.getSimilarityCriteria();
		final Date currentDate = new Date(System.currentTimeMillis());
		final SurveyQuestion surveyQuestion = new SurveyQuestion(1L, similarityCriteria, 1L, "Test Question", "Testing",
				1L, 1, currentDate, new ArrayList<>());
		questions.add(surveyQuestion);

		final ISurveyResponse userAnswers = SurveyFactory.instance().createSurveyResponse();
		final int[] answers = { 1, 2, 1, 3 };
		for (int i = 1; i <= 4; i++) {
			UserAnswer answer = new UserAnswer(String.valueOf(answers[i-1]), answers[i-1]);
			userAnswers.setUserAnswer(1L, (long) i, answer);
		}

		GroupFormationAlgorithmBuilder builder = GroupFormationAlgorithmBuilder.builder();
		builder.setMaxUsersPerGroup(maxUsersPerGroup);
		builder.setQuestions(questions);
		builder.setUserAnswers(userAnswers);
		builder.setUsers(users);
		
		List<List<User>> groups = builder.build().createGroups();
		List<User> group1 = groups.get(0);
		List<User> group2 = groups.get(1);
		assertEquals(maxUsersPerGroup, group1.size());
		assertEquals(maxUsersPerGroup, group2.size());
		assertTrue(group1.contains(users.get(0)));
		assertTrue(group1.contains(users.get(2)));
		assertTrue(group2.contains(users.get(1)));
		assertTrue(group2.contains(users.get(3)));
	}
}
