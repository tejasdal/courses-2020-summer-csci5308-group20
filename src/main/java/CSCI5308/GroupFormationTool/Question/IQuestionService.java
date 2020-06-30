package CSCI5308.GroupFormationTool.Question;

import java.util.List;

public interface IQuestionService {

    void createQuestion(Question question, IQuestionPersistence questionPersistence) throws QuestionException;
    public List<Question> getAllUserQuestions(Long userId, IQuestionPersistence questionPersistence);
    public boolean deleteQuestion(Long questionId,IQuestionPersistence questionPersistence);
    public List<Question> getAllUserQuestionsSortedByTitle(Long userId, IQuestionPersistence questionPersistence);
    public List<Question> getAllUserQuestionsSortedByDate(Long userId, IQuestionPersistence questionPersistence);
}
