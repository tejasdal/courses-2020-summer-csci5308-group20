package CSCI5308.GroupFormationTool.Question;

public interface IQuestionOption {
    String getOption();

    Long getQuestionId();

    int getValue();

    Long getId();

    void setId(Long id);

    void setQuestionId(Long questionId);

    void setOption(String option);

    void setValue(int value);
}
