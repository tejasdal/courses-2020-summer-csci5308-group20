package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.List;

public class QuestionService implements IQuestionService {

    @Override
    public void createQuestion(Question question) {

    }

    @Override
    public List<Question> getAllQuestionOfInstructor
            (Long instructorId,String sortBy,IQuestionPersistence questionPersistence) {
        return questionPersistence.getAllQuestionsForUser(instructorId,sortBy);
    }

    @Override
    public boolean deleteQuestion(Long questionId,IQuestionPersistence questionPersistence) {
        return questionPersistence.deleteQuestion(questionId);
    }
}
