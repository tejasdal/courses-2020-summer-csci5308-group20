package CSCI5308.GroupFormationTool.Question;

public class QuestionOption {
    private Long id;
    private Long questionId;
    private String option;
    private int value;

    public QuestionOption(Long id, String answer, int value) {
        this.id = id;
        this.option = answer;
        this.value = value;
    }

    public String getOption() {
        return option;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public int getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }
}
