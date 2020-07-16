package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.CustomExceptions.QuestionException;

import java.util.List;

public interface IQuestionService {

    void createQuestion(Question question, IQuestionPersistence questionPersistence) throws QuestionException, QuestionException;

    List<IQuestion> getAllUserQuestions(Long userId, IQuestionPersistence questionPersistence);

    boolean deleteQuestion(Long questionId, IQuestionPersistence questionPersistence);

    List<IQuestion> getAllUserQuestionsSortedByTitle(Long userId, IQuestionPersistence questionPersistence);

    List<IQuestion> getAllUserQuestionsSortedByDate(Long userId, IQuestionPersistence questionPersistence);
}
