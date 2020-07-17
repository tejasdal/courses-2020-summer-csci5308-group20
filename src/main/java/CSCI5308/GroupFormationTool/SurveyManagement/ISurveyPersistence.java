package CSCI5308.GroupFormationTool.SurveyManagement;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionOption;

import java.sql.SQLException;
import java.util.List;

public interface ISurveyPersistence {
    boolean createSurvey(long courseId);

    long getSurveyIdUsingCourseId(long courseId) throws SQLException;

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
