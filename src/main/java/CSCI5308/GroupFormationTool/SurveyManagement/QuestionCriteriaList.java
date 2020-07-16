package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionCriteriaList {

    List<QuestionWithCriteriaDetails> list = new ArrayList<>();

    public QuestionCriteriaList() {
    }

    QuestionCriteriaList(List<Question> questionList) {
        list = new ArrayList<>();
        questionList.forEach(question -> {
            list.add(new QuestionWithCriteriaDetails(question, -1, 0));
        });
    }

    public List<QuestionWithCriteriaDetails> getList() {
        return list;
    }

    public void setList(List<QuestionWithCriteriaDetails> list) {
        this.list = list;
    }
}
