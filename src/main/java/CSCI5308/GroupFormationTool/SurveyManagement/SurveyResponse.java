package CSCI5308.GroupFormationTool.SurveyManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyResponse implements ISurveyResponse {
	private Map<Long, Map<Long, List<UserAnswer>>> allUserAnswers;

	public SurveyResponse() {
		allUserAnswers = new HashMap<>();
	}

	@Override
	public List<UserAnswer> getUserAnswers(Long questionId, Long userId) throws IOException {
		if (allUserAnswers.containsKey(questionId) && allUserAnswers.get(questionId).containsKey(userId)) {
			return allUserAnswers.get(questionId).get(userId);
		} else {
			throw new IOException("Answers not found for the question and user combination");
		}
	}

	@Override
	public void setUserAnswers(Long questionId, Long userId, List<UserAnswer> userAnswers) {
		Map<Long, List<UserAnswer>> questionToUserAnswersMap = allUserAnswers.get(questionId);
		if (questionToUserAnswersMap == null) {
			questionToUserAnswersMap = new HashMap<>();
			allUserAnswers.put(questionId, questionToUserAnswersMap);
		}
		questionToUserAnswersMap.put(userId, userAnswers);
	}
	
	@Override
	public void setUserAnswer(Long questionId, Long userId, UserAnswer userAnswer) {
		Map<Long, List<UserAnswer>> questionToUserAnswersMap = allUserAnswers.get(questionId);
		if (questionToUserAnswersMap == null) {
			questionToUserAnswersMap = new HashMap<>();
			allUserAnswers.put(questionId, questionToUserAnswersMap);
		}
		List<UserAnswer> userAnswers = questionToUserAnswersMap.get(userId);
		if (userAnswers == null) {
			userAnswers = new ArrayList<>();
			questionToUserAnswersMap.put(userId, userAnswers);
		}
		userAnswers.add(userAnswer);
	}
}
