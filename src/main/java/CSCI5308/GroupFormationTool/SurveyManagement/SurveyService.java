package CSCI5308.GroupFormationTool.SurveyManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Question.Answers;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionOption;
import CSCI5308.GroupFormationTool.SurveyManagement.algorithm.GroupFormationAlgorithmBuilder;
import CSCI5308.GroupFormationTool.SurveyManagement.algorithm.IGroupFormationAlgorithm;
import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.IMatchCriteria;
import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.IMatchCriteriaFactory;

public class SurveyService implements ISurveyService {

	public Map<String, Object> getAllSurveyQuestions(long courseID, ISurveyPersistence surveyPersistence) {

		Map<String, Object> response = new HashMap<>();
		long surveyId = surveyPersistence.getSurveyIdUsingCourseId(courseID);
		if (surveyId == -1) {
			if (surveyPersistence.createSurvey(courseID)) {
				surveyId = surveyPersistence.getSurveyIdUsingCourseId(courseID);
			}
		}
		response.put("surveyId", surveyId);
		if (surveyId != (-1)) {
			boolean status = isSurveyPublished(surveyId, surveyPersistence);
			response.put("status", status);
			List<Question> list = surveyPersistence.getAllSurveyQuestions(surveyId);
			if (list != null) {
				response.put("questions", list);
				return response;
			}
		}
		return null;
	}

	public Map<String, Object> addQuestionPage(long courseId, long surveyId, ISurveyPersistence surveyPersistence) {
		Map<String, Object> response = new HashMap<>();
		List<Question> allQuestions = surveyPersistence.getAllInstructorQuestionsUsingCourseId(courseId, surveyId);
		List<Question> addedQuestions = surveyPersistence.getAllSurveyQuestions(surveyId);
		response.put("addedQuestion", addedQuestions);
		response.put("availableQuestions", allQuestions);
		return response;
	}

	public void addQuestionToSurvey(long surveyId, long questionId, ISurveyPersistence surveyPersistence) {
		if (isSurveyPublished(surveyId, surveyPersistence) == false) {
			surveyPersistence.addQuestionToSurvey(surveyId, questionId);
		}
	}

	public void deleteQuestionFromSurvey(Long surveyId, Long questionId, ISurveyPersistence surveyPersistence) {
		if (isSurveyPublished(surveyId, surveyPersistence) == false) {
			surveyPersistence.deleteQuestionFromSurvey(surveyId, questionId);
		}
	}

	public boolean publishSurvey(long surveyId, ISurveyPersistence surveyPersistence) {
		return surveyPersistence.publishSurvey(surveyId);
	}

	public boolean unpublishSurvey(long surveyId, ISurveyPersistence surveyPersistence) {
		return surveyPersistence.unpublishSurvey(surveyId);
	}

	public boolean isSurveyPublished(Long surveyId, ISurveyPersistence surveyPersistence) {
		int status = surveyPersistence.getSurveyStatus(surveyId);
		if (status == 0) {
			return false;
		}
		return true;
	}

	public Map<String, Object> displaySurveyQuestionsToStudents(Long courseId, ISurveyPersistence surveyPersistence) {
		Map<String, Object> response = new HashMap<>();
        long surveyId = surveyPersistence.getSurveyIdUsingCourseId(courseId);
        if (surveyId == -1L) {
            response.put("isSurveyPublished", false);
        } else {
            List<Question> surveyQuestions = surveyPersistence.getAllSurveyQuestions(surveyId);
            for (Question surveyQuestion : surveyQuestions) {
                if (surveyQuestion.getQuestionType() == Question.MULTIPLE_CHOICE_CHOOSE_ONE
                        || surveyQuestion.getQuestionType() == Question.MULTIPLE_CHOICE_CHOOSE_MANY) {

                    List<QuestionOption> options = surveyPersistence.getSurveyQuestionOption(surveyQuestion.getId());
                    for (QuestionOption option : options) {
                        surveyQuestion.getAnswers().add(new Answers());
                    }

                    if (null != options) {
                        surveyQuestion.setQuestionOptions(options);
                    }
                }
            }
            response.put("isSurveyPublished", true);
            response.put("surveyId", surveyId);
            ISurvey survey = SurveyServiceAbstractFactory.instance().makeSurvey();
            survey.setQuestions(surveyQuestions);
            response.put("survey", survey);

        }
		return response;
	}

	@Override
	public boolean submitAnswers(String bannerId, Long surveyId, Survey survey, ISurveyPersistence surveyPersistence) {
        for (Question q : survey.getQuestions()) {
            q.getAnswers().removeIf(question -> question.getAnswerValue() == null);
        }
		return surveyPersistence.submitAnswers(bannerId, surveyId, survey);
	}

	@Override
	public List<List<User>> createGroups(QuestionCriteriaList questionsList, Long surveyId, int maxUsersPerGroup,
			ISurveyPersistence persistence) throws IOException {
		List<User> users = persistence.getAllParticipants(surveyId);
		List<SurveyQuestion> questions = new ArrayList<>();
		questionsList.getList().forEach(question -> {
			int criteriaType = question.getCriteriaType();
			int criteriaValue = question.getCriteriaValue();
			IMatchCriteriaFactory factory = SystemConfig.instance().getMatchCriteriaFactory();
			IMatchCriteria matchCriteria = factory.getSimilarityCriteria();
			switch (criteriaType) {
			case 0:
				matchCriteria = factory.getSimilarityCriteria();
				break;
			case 1:
				matchCriteria = factory.getDissimilarityCriteria();
				break;
			case 2:
				matchCriteria = factory.getXGreaterThanYCriteria(1, criteriaValue);
				break;
			case 3:
				matchCriteria = factory.getXLesserThanYCriteria(1, criteriaValue);
				break;
			default:
				matchCriteria = null;
			}
			SurveyQuestion surveyQuestion = new SurveyQuestion(surveyId, matchCriteria, question.getId(),
					question.getTitle(), question.getDescription(), question.getUserId(), question.getQuestionType(),
					question.getCreatedAt(), question.getQuestionOptions());
			questions.add(surveyQuestion);
		});
		ISurveyResponse answers = persistence.getSurveyResponses(surveyId);
		IGroupFormationAlgorithm algorithm = GroupFormationAlgorithmBuilder.builder().setUsers(users)
				.setQuestions(questions).setUserAnswers(answers).setMaxUsersPerGroup(5).build();
		return algorithm.createGroups();
	}

	@Override
	public boolean validateQuestionCriteriaList(QuestionCriteriaList questionsList) throws Exception {
		if (questionsList.getMembersPerGroup() < 1) {
			throw new Exception("Number of members per group should be greater than 1");
		}
		for (QuestionWithCriteriaDetails question : questionsList.getList()) {
			switch (question.getQuestionType()) {
			case 1:
				if (question.getCriteriaType() < 0 || question.getCriteriaType() > 3) {
					throw new Exception(
							"Match criteria should be one of 'Similar, Dissimilar, 1 Greater than X, 1 Lesser than X'");
				}
				break;
			case 2:
				if (question.getCriteriaType() < 0 || question.getCriteriaType() > 1) {
					throw new Exception("Match criteria should be one of 'Similar, Dissimilar'");
				}
				break;
			case 3:
				if (question.getCriteriaType() < 0 || question.getCriteriaType() > 1) {
					throw new Exception("Match criteria should be one of 'Similar, Dissimilar'");
				}
				break;
			case 4:
				if (question.getCriteriaType() < 4 || question.getCriteriaType() > 5) {
					throw new Exception("Match criteria should be one of 'Match, Does not match'");
				}
				break;
			}
		}
		return true;
	}
}
