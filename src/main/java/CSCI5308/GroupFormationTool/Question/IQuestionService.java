package CSCI5308.GroupFormationTool.Question;

import java.util.List;

public interface IQuestionService {

    void createQuestion(Question question);
    public List<Question> getAllQuestionOfInstructor(Long instructorId);
    public boolean deleteQuestion(Long questionId);

}
