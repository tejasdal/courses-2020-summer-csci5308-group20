package CSCI5308.GroupFormationTool.Question;

public class QuestionOption implements IQuestionOption {
    private Long id;
    private Long questionId;
    private String option;
    private int value;

    public QuestionOption() {
    }

    public QuestionOption(Long id, String answer, int value) {
        this.id = id;
        this.option = answer;
        this.value = value;
    }

    @Override
    public String getOption() {
        return option;
    }

    @Override
    public Long getQuestionId() {
        return questionId;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }
}
