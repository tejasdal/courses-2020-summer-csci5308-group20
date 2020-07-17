package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.AccessControl.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ISurveyService {
    Map<String, Object> getAllSurveyQuestions(long courseID, ISurveyPersistence surveyPersistence) throws SQLException;

    Map<String, Object> addQuestionPage(long courseId, long surveyId, ISurveyPersistence surveyPersistence);

    void addQuestionToSurvey(long surveyId, long questionId, ISurveyPersistence surveyPersistence);

    void deleteQuestionFromSurvey(Long surveyId, Long questionId, ISurveyPersistence surveyPersistence);

    boolean unpublishSurvey(long surveyId, ISurveyPersistence surveyPersistence);

    boolean publishSurvey(long surveyId, ISurveyPersistence surveyPersistence);

    boolean isSurveyPublished(Long surveyId, ISurveyPersistence surveyPersistence);

    Map<String, Object> displaySurveyQuestionsToStudents(Long courseId, ISurveyPersistence surveyPersistence);

    boolean submitAnswers(String bannerId, Long surveyId, Survey survey, ISurveyPersistence surveyPersistence);

    Map<Integer, Map<User, List<String>>> createGroups(QuestionCriteriaList questionsList, Long surveyId, int maxUsersPerGroup,
                                                       ISurveyResponse responses, ISurveyPersistence persistence) throws IOException;

    boolean validateQuestionCriteriaList(QuestionCriteriaList questionsList) throws Exception;

    public List<SurveyQuestion> getQuestionsFromCriteriaList(QuestionCriteriaList questionsList, Long surveyId);
}
