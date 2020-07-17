package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionCriteriaList implements IQuestionCriteriaList {

    List<QuestionWithCriteriaDetails> list = new ArrayList<>();
    int membersPerGroup = 0;
    Long surveyId;

    public QuestionCriteriaList() {
    }

    public QuestionCriteriaList(List<Question> questionList) {
        list = new ArrayList<>();
        questionList.forEach(question -> {
            list.add(SurveyServiceAbstractFactory.instance().makeQuestionWithCriteriaDetails(question, -1, 0));
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
