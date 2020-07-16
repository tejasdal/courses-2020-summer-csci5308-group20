package CSCI5308.GroupFormationTool.SurveyManagement;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public class QuestionCriteriaList {

    List<QuestionWithCriteriaDetails> list = new ArrayList<>();
    int membersPerGroup = 0;
    Long surveyId;

    public QuestionCriteriaList() {
    }

    public QuestionCriteriaList(List<IQuestion> questionList) {
        list = new ArrayList<>();
        questionList.forEach(question -> {
            list.add(new QuestionWithCriteriaDetails(question, 0, 0));
        });
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public int getMembersPerGroup() {
        return membersPerGroup;
    }

    public void setMembersPerGroup(int membersPerGroup) {
        this.membersPerGroup = membersPerGroup;
    }

    public List<QuestionWithCriteriaDetails> getList() {
        return list;
    }

    public void setList(List<QuestionWithCriteriaDetails> list) {
        this.list = list;
    }
}
