package CSCI5308.GroupFormationTool.SurveyManagement;

import java.util.List;

public interface IQuestionCriteriaList {
    Long getSurveyId();

    void setSurveyId(Long surveyId);

    int getMembersPerGroup();

    void setMembersPerGroup(int membersPerGroup);

    List<QuestionWithCriteriaDetails> getList();

    void setList(List<QuestionWithCriteriaDetails> list);
}
