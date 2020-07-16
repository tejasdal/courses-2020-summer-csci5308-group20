package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Question;

public class QuestionWithCriteriaDetails extends Question {

    int criteriaType;
    int criteriaValue;

    public QuestionWithCriteriaDetails() {
    }

    public QuestionWithCriteriaDetails(Question q, int criteriaType, int criteriaValue) {
        super(q.getId(), q.getTitle(), q.getDescription(), q.getUserId(), q.getQuestionType(), q.getCreatedAt(), q.getQuestionOptions());
        this.criteriaType = criteriaType;
        this.criteriaValue = criteriaValue;
        this.setQuestionTypeString(this.getQuestionTypeStringMapping(q.getQuestionType()));
    }

    public int getCriteriaType() {
        return criteriaType;
    }

    public void setCriteriaType(int criteriaType) {
        this.criteriaType = criteriaType;
    }

    public int getCriteriaValue() {
        return criteriaValue;
    }

    public void setCriteriaValue(int criteriaValue) {
        this.criteriaValue = criteriaValue;
    }
}
