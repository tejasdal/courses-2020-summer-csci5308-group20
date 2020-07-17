package CSCI5308.GroupFormationTool.SurveyManagement;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionOption;

public interface ISurveyPersistence {
    boolean createSurvey(long courseId);

    long getSurveyIdUsingCourseId(long courseId);

    boolean addQuestionToSurvey(long surveyId, long questionId);

    boolean deleteQuestionFromSurvey(Long surveyId, Long questionId);

    boolean publishSurvey(Long surveyId);

    boolean unpublishSurvey(Long surveyId);

    int getSurveyStatus(Long surveyId);

    List<Question> getAllSurveyQuestions(long surveyId);

    List<Question> getAllInstructorQuestionsUsingCourseId(long courseId, long surveyId);

    List<QuestionOption> getSurveyQuestionOption(Long questionId);

    boolean submitAnswers(String bannerId, Long surveyId, Survey survey);
    
    List<User> getAllParticipants(Long surveyId);
    
    ISurveyResponse getSurveyResponses(Long surveyId);
}
