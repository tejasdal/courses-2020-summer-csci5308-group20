package CSCI5308.GroupFormationTool.Question;

import java.util.List;

public interface IQuestionService {

    void createQuestion(Question question, IQuestionPersistence questionPersistence) throws Exception;

    List<Question> getAllUserQuestions(Long userId, IQuestionPersistence questionPersistence);

    boolean deleteQuestion(Long questionId, IQuestionPersistence questionPersistence);

    List<Question> getAllUserQuestionsSortedByTitle(Long userId, IQuestionPersistence questionPersistence);

    List<Question> getAllUserQuestionsSortedByDate(Long userId, IQuestionPersistence questionPersistence);
}
