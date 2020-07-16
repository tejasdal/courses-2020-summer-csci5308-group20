package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.IQuestionOption;
import CSCI5308.GroupFormationTool.Question.Question;

import java.util.List;

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

    List<IQuestionOption> getSurveyQuestionOption(Long questionId);

    boolean submitAnswers(String bannerId, Long surveyId, Survey survey);
}
