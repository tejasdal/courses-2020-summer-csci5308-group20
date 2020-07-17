package CSCI5308.GroupFormationTool.SurveyManagement;

public class UserAnswer {
	private String answerRaw;
	private Integer answerIndex;
	
	public UserAnswer(String answerRaw, Integer answerIndex) {
		this.answerIndex = answerIndex;
		this.answerRaw = answerRaw;
	}
	
	public String getAnswerRaw() {
		return answerRaw;
	}
	
	public Integer getAnswerIndex() {
		return answerIndex;
	}
	
	public boolean compareIndex(UserAnswer answer) {
		return answerIndex == answer.answerIndex;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof UserAnswer) {
			UserAnswer other = (UserAnswer) object;
			return answerRaw.equals(other.answerRaw);
		}
		return false;
	}
}
