package CSCI5308.GroupFormationTool.Question;

import java.util.List;

public interface IQuestionService {

    void createQuestion(Question question, IQuestionPersistence questionPersistence) throws QuestionException;
    public List<Question> getAllUserQuestions(Long userId, String sortBy, IQuestionPersistence questionPersistence);
    public boolean deleteQuestion(Long questionId,IQuestionPersistence questionPersistence);

}
