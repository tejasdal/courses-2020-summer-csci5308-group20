package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyService implements ISurveyService {

    public Map<String, Object> getAllSurveyQuestions(long courseID) {

        ISurveyPersistence surveyPersistence = SystemConfig.instance().getSurveyPersistence();
        Map<String, Object> response = new HashMap<>();
        long surveyId = surveyPersistence.getSurveyIdUsingCourseId(courseID);
        response.put("surveyId", surveyId);
        if (surveyId != (-1)) {
            List<Question> list = surveyPersistence.getAllSurveyQuestions(surveyId);
            if (list != null) {
                response.put("questions", list);
                return response;
            }
        }
        return null;
    }

    public Map<String, Object> addQuestionPage(long courseId, long surveyId) {
        Map<String, Object> response = new HashMap<>();
        ISurveyPersistence surveyPersistence = SystemConfig.instance().getSurveyPersistence();
        List<Question> allQuestions = surveyPersistence.getAllInstructorQuestionsUsingCourseId(courseId, surveyId);
        List<Question> addedQuestions = surveyPersistence.getAllSurveyQuestions(surveyId);
        response.put("addedQuestion", addedQuestions);
        response.put("availableQuestions", allQuestions);
        return response;
    }

    public boolean addQuestionToSurvey(long surveyId, long questionId) {
        ISurveyPersistence surveyPersistence = SystemConfig.instance().getSurveyPersistence();
        return surveyPersistence.addQuestionToSurvey(surveyId, questionId);
    }

    public boolean deleteQuestionFromSurvey(Long surveyId, Long questionId) {
        ISurveyPersistence surveyPersistence = SystemConfig.instance().getSurveyPersistence();
        return surveyPersistence.deleteQuestionFromSurvey(surveyId, questionId);
    }
}
