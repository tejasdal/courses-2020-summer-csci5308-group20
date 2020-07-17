package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Question;

import java.util.List;

public class SurveyServiceConcreteFactory extends SurveyServiceAbstractFactory {
    @Override
    public ISurveyService makeService() {
        return new SurveyService();
    }

    @Override
    public ISurvey makeSurvey() {
        return new Survey();
    }

    @Override
    public IQuestionCriteriaList makeQuestionCriteriaList() {
        return new QuestionCriteriaList();
    }

    @Override
    public IQuestionCriteriaList makeQuestionCriteriaListUsingList(List<Question> questionList) {
        return new QuestionCriteriaList(questionList);
    }

    @Override
    public QuestionWithCriteriaDetails makeQuestionWithCriteriaDetails(Question q, int criteriaType, int criteriaValue) {
        return new QuestionWithCriteriaDetails(q, criteriaType, criteriaValue);
    }
}
