package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Question;

import java.util.List;

public interface ISurveyPersistence {

    long getSurveyIdUsingCourseId(long courseId);

    boolean addQuestionToSurvey(long surveyId, long questionId);

    boolean deleteQuestionFromSurvey(Long surveyId, Long questionId);

    List<Question> getAllSurveyQuestions(long surveyId);

    List<Question> getAllInstructorQuestionsUsingCourseId(long courseId, long surveyId);
}
