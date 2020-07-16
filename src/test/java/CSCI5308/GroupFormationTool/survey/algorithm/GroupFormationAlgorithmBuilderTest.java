package CSCI5308.GroupFormationTool.survey.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.survey.SurveyQuestion;
import CSCI5308.GroupFormationTool.survey.UserAnswer;
import CSCI5308.GroupFormationTool.survey.matchcriteria.IMatchCriteria;

public class GroupFormationAlgorithmBuilderTest {

	@Test
	public void builderTest() {
		final int maxUsersPerGroup = 5;

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

		final Map<Long, Map<Long, List<UserAnswer>>> userAnswers = new HashMap<>();
		final Map<Long, List<UserAnswer>> userAnswer = new HashMap<Long, List<UserAnswer>>();
		userAnswers.put(1L, userAnswer);
		final int[] answers = { 1, 1, 2, 3 };
		for (int i = 1; i <= 4; i++) {
			userAnswer.put((long) i, new ArrayList<>());
			int answer = answers[i - 1];
			userAnswer.get((long) i).add(new UserAnswer(String.valueOf(answer), answer));
		}

		GroupFormationAlgorithmBuilder builder = GroupFormationAlgorithmBuilder.builder();
		builder.setMaxUsersPerGroup(maxUsersPerGroup);
		builder.setQuestions(questions);
		builder.setUserAnswers(userAnswers);
		builder.setUsers(users);

		assertEquals(maxUsersPerGroup, builder.getMaxUsersPerGroup());
		assertEquals(questions.size(), builder.getQuestions().size());
		assertEquals(userAnswers.size(), builder.getUserAnswers().size());
		assertEquals(users.size(), builder.getUsers().size());
		assertNotNull(builder.build());
		assertTrue(builder.build() instanceof IGroupFormationAlgorithm);
	}

}
