package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Answers;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionOption;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyService implements ISurveyService {

    public Map<String, Object> getAllSurveyQuestions(long courseID) {

        ISurveyPersistence surveyPersistence = SystemConfig.instance().getSurveyPersistence();
        Map<String, Object> response = new HashMap<>();
        long surveyId = surveyPersistence.getSurveyIdUsingCourseId(courseID);
        if (surveyId == -1) {
            if (surveyPersistence.createSurvey(courseID)) {
                surveyId = surveyPersistence.getSurveyIdUsingCourseId(courseID);
            }
        }
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

    public void addQuestionToSurvey(long surveyId, long questionId) {
        ISurveyPersistence surveyPersistence = SystemConfig.instance().getSurveyPersistence();
        if (isSurveyPublished(surveyId) == false) {
            surveyPersistence.addQuestionToSurvey(surveyId, questionId);
        }
    }

    public void deleteQuestionFromSurvey(Long surveyId, Long questionId) {
        ISurveyPersistence surveyPersistence = SystemConfig.instance().getSurveyPersistence();
        if (isSurveyPublished(surveyId) == false) {
            surveyPersistence.deleteQuestionFromSurvey(surveyId, questionId);
        }
    }

    public boolean publishSurvey(long surveyId) {
        ISurveyPersistence surveyPersistence = SystemConfig.instance().getSurveyPersistence();
        return surveyPersistence.publishSurvey(surveyId);
    }


    public boolean unpublishSurvey(long surveyId) {
        ISurveyPersistence surveyPersistence = SystemConfig.instance().getSurveyPersistence();
        return surveyPersistence.unpublishSurvey(surveyId);
    }

    public boolean isSurveyPublished(Long surveyId) {
        ISurveyPersistence surveyPersistence = SystemConfig.instance().getSurveyPersistence();
        int status = surveyPersistence.getSurveyStatus(surveyId);
        if (status == 0) {
            return false;
        }
        return true;
    }

    public Map<String, Object> displaySurveyQuestionsToStudents(Long courseId, ISurveyPersistence surveyPersistence) {
        Map<String, Object> response = new HashMap<>();
        long surveyId = surveyPersistence.getSurveyIdUsingCourseId(courseId);
        if(surveyId == -1L){
            response.put("isSurveyPublished", false);
        }else{
            List<Question> surveyQuestions = surveyPersistence.getAllSurveyQuestions(surveyId);
            for (Question surveyQuestion: surveyQuestions) {
                if(surveyQuestion.getQuestionType() == Question.MULTIPLE_CHOICE_CHOOSE_ONE
                        || surveyQuestion.getQuestionType() == Question.MULTIPLE_CHOICE_CHOOSE_MANY){

                    List<QuestionOption> options = surveyPersistence.getSurveyQuestionOption(surveyQuestion.getId());
                    for(QuestionOption option: options){
                        surveyQuestion.getAnswers().add(new Answers());
                    }

                    if(null != options) {
                        surveyQuestion.setQuestionOptions(options);
                    }
                }
            }
            response.put("isSurveyPublished", true);
            response.put("surveyId", surveyId);
            Survey survey = new Survey();
            survey.setQuestions(surveyQuestions);
            response.put("survey", survey);

        }
        return response;
    }

    @Override
    public boolean submitAnswers(String bannerId, Long surveyId, Survey survey, ISurveyPersistence surveyPersistence) {
        for(Question q: survey.getQuestions()){
            q.getAnswers().removeIf(question->question.getAnswerValue() == null);
        }
        return surveyPersistence.submitAnswers(bannerId,surveyId,survey);
    }


}
