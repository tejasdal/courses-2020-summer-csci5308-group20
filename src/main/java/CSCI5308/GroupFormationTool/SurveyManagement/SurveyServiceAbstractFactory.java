package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Question;

import java.util.List;

public abstract class SurveyServiceAbstractFactory {

    private static SurveyServiceAbstractFactory uniqueInstance = null;

    protected SurveyServiceAbstractFactory() {
    }

    public static SurveyServiceAbstractFactory instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SurveyServiceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract ISurveyService makeService();

    public abstract ISurvey makeSurvey();

    public abstract IQuestionCriteriaList makeQuestionCriteriaList();

    public abstract IQuestionCriteriaList makeQuestionCriteriaListUsingList(List<Question> questionList);

    public abstract QuestionWithCriteriaDetails makeQuestionWithCriteriaDetails(Question q, int criteriaType, int criteriaValue);
}
