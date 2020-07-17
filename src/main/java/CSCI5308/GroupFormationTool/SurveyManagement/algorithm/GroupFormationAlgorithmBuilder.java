package CSCI5308.GroupFormationTool.SurveyManagement.algorithm;

import java.io.IOException;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyResponse;
import CSCI5308.GroupFormationTool.SurveyManagement.SurveyQuestion;
import CSCI5308.GroupFormationTool.SurveyManagement.SurveyResponse;

public class GroupFormationAlgorithmBuilder {
	private List<SurveyQuestion> questions;
	private ISurveyResponse userAnswers;
	private List<User> users;
	private int maxUsersPerGroup;
	private String algorithmName;

	private GroupFormationAlgorithmBuilder() {

	}

	public IGroupFormationAlgorithm build() throws IOException {
		if (questions == null) {
			throw new IOException("Questions not initialized for the algorithm");
		}
		if (userAnswers == null) {
			throw new IOException("User answers not initialized for the algorithm");
		}
		if (users == null) {
			throw new IOException("User details not initialized for the algorithm");
		}
		if (maxUsersPerGroup < 1) {
			throw new IOException("Max users per group not initialized to a valid positive integer");
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

	public GroupFormationAlgorithmBuilder setUserAnswers(ISurveyResponse userAnswers) {
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

	ISurveyResponse getUserAnswers() {
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
