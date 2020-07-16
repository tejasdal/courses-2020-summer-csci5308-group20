package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Answers;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionOption;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyService implements ISurveyService {

    private static final Integer SURVEY_PUBLISHED = 1;

    public Map<String, Object> getAllSurveyQuestions(long courseID, ISurveyPersistence surveyPersistence) throws SQLException {

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

    public Map<String, Object> displaySurveyQuestionsToStudents(Long courseId, ISurveyPersistence surveyPersistence) throws SQLException {
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
            response.put("isSurveyPublished", surveyPersistence.getSurveyStatus(surveyId) == SURVEY_PUBLISHED);
            response.put("surveyId", surveyId);
            ISurvey survey = ServiceAbstractFactory.instance().makeSurvey();
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
}
