package CSCI5308.GroupFormationTool.SurveyManagement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ISurveyResponse {

	public Map<Long, Map<Long, List<UserAnswer>>> getAllUserAnswers();
	
	public List<UserAnswer> getUserAnswers(Long questionId, Long userId) throws IOException;

	public void setUserAnswers(Long questionId, Long userId, List<UserAnswer> userAnswers);
	
	public void setUserAnswer(Long questionId, Long userId, UserAnswer userAnswer);
}
