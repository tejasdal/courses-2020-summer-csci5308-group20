package CSCI5308.GroupFormationTool.survey.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.survey.SurveyQuestion;
import CSCI5308.GroupFormationTool.survey.UserAnswer;
import CSCI5308.GroupFormationTool.survey.matchcriteria.IMatchCriteria;

class BacktrackStandardDeviation implements IGroupFormationAlgorithm {

	private List<User> currentGroup;
	private List<SurveyQuestion> questions;
	private Map<Long, Map<Long, List<UserAnswer>>> userAnswers;
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
	public List<List<User>> createGroups() {

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

	private void backtrack(List<User> group, int start, int end) {
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

	private double getScore(List<User> group) {
		double score = 0;
		for (SurveyQuestion question : questions) {
			IMatchCriteria matchCriteria = question.getMatchCriteria();
			if (matchCriteria == null) {
				continue;
			}
			final List<List<UserAnswer>> groupUsersAnswers = new ArrayList<>();
			group.forEach(user -> groupUsersAnswers.add(userAnswers.get(question.getId()).get(user.getId())));
			score += matchCriteria.calculateMatchScore(groupUsersAnswers);
		}
		return score;
	}

}
