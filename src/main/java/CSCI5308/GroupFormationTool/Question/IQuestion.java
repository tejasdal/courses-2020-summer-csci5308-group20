package CSCI5308.GroupFormationTool.Question;

import java.sql.Date;
import java.util.List;

public interface IQuestion {
    String getQuestionTypeString();

    void setQuestionTypeString(String questionTypeString);

    Long getId();

    void setId(Long id);

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    Long getUserId();

    void setUserId(Long userId);

    int getQuestionType();

    void setQuestionType(int questionType);

    List<QuestionOption> getQuestionOptions();

    void setQuestionOptions(List<QuestionOption> questionOptions);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    String getQuestionTypeStringMapping(int questionType);
    
    void setAnswers(List<Answers> answers);
    
    List<Answers> getAnswers();

}
