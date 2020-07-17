package CSCI5308.GroupFormationTool.SurveyManagement.algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyResponse;
import CSCI5308.GroupFormationTool.SurveyManagement.SurveyQuestion;
import CSCI5308.GroupFormationTool.SurveyManagement.UserAnswer;
import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.IMatchCriteria;

class BacktrackStandardDeviation implements IGroupFormationAlgorithm {

	private List<User> currentGroup;
	private List<SurveyQuestion> questions;
	private ISurveyResponse userAnswers;
	private List<User> users;
	private int maxUsersPerGroup;
	private Double maxScore;

	BacktrackStandardDeviation(GroupFormationAlgorithmBuilder b) {
		currentGroup = new ArrayList<>();
		this.questions = b.getQuestions();
		this.userAnswers = b.getUserAnswers();
		this.users = new ArrayList<>(b.getUsers());
		this.maxUsersPerGroup = b.getMaxUsersPerGroup();
	}

	@Override
	public List<List<User>> createGroups() throws IOException {

		List<List<User>> groups = new ArrayList<>();

		while (users.size() >= maxUsersPerGroup) {
			maxScore = null;
			backtrack(new ArrayList<User>(), 0, users.size());
			groups.add(currentGroup);
			for (User user : currentGroup) {
				users.remove(user);
			}
		}
		if (users.size() > 0) {
			currentGroup = new ArrayList<>(users);
			groups.add(currentGroup);
		}
		return groups;

	}

	private void backtrack(List<User> group, int start, int end) throws IOException {
		if (group.size() == maxUsersPerGroup) {
			double score = getScore(group);
			if (maxScore == null || maxScore < score) {
				maxScore = score;
				currentGroup = new ArrayList<>(group);
			}
			return;
		}
		for (int i = start; i < end; i++) {
			group.add(users.get(i));
			backtrack(group, i + 1, end);
			group.remove(group.size() - 1);
		}
	}

	private double getScore(List<User> group) throws IOException {
		double score = 0;
		for (SurveyQuestion question : questions) {
			IMatchCriteria matchCriteria = question.getMatchCriteria();
			if (matchCriteria == null) {
				continue;
			}
			final List<List<UserAnswer>> groupUsersAnswers = new ArrayList<>();
			for (User user : group) {
				groupUsersAnswers.add(userAnswers.getUserAnswers(question.getId(), user.getId()));
			}
			score += matchCriteria.calculateMatchScore(groupUsersAnswers);
		}
		return score;
	}

}
