package CSCI5308.GroupFormationTool.survey.algorithm;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.survey.SurveyQuestion;
import CSCI5308.GroupFormationTool.survey.UserAnswer;

public class GroupFormationAlgorithmBuilder {
	private List<SurveyQuestion> questions;
	private Map<Long, Map<Long, List<UserAnswer>>> userAnswers;
	private List<User> users;
	private int maxUsersPerGroup;
	private String algorithmName;

	private GroupFormationAlgorithmBuilder() {

	}

	public IGroupFormationAlgorithm build() {
		if (questions == null) {
			throw new IllegalStateException("Questions not initialized for the algorithm");
		}
		if (userAnswers == null) {
			throw new IllegalStateException("User answers not initialized for the algorithm");
		}
		if (users == null) {
			throw new IllegalStateException("User details not initialized for the algorithm");
		}
		if (maxUsersPerGroup < 1) {
			throw new IllegalStateException("Max users per group not initialized to a valid positive integer");
		}
		return new BacktrackStandardDeviation(this);
	}

	public static GroupFormationAlgorithmBuilder builder() {
		return new GroupFormationAlgorithmBuilder();
	}

	public GroupFormationAlgorithmBuilder setQuestions(List<SurveyQuestion> questions) {
		this.questions = questions;
		return this;
	}

	public GroupFormationAlgorithmBuilder setUserAnswers(Map<Long, Map<Long, List<UserAnswer>>> userAnswers) {
		this.userAnswers = userAnswers;
		return this;
	}

	public GroupFormationAlgorithmBuilder setUsers(List<User> users) {
		this.users = users;
		return this;
	}

	public GroupFormationAlgorithmBuilder setMaxUsersPerGroup(int maxUsersPerGroup) {
		this.maxUsersPerGroup = maxUsersPerGroup;
		return this;
	}

	List<SurveyQuestion> getQuestions() {
		return questions;
	}

	Map<Long, Map<Long, List<UserAnswer>>> getUserAnswers() {
		return userAnswers;
	}

	List<User> getUsers() {
		return users;
	}

	int getMaxUsersPerGroup() {
		return maxUsersPerGroup;
	}

	String getAlgorithmName() {
		return algorithmName;
	}

}
