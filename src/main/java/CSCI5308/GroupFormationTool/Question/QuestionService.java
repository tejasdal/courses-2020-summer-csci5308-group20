package CSCI5308.GroupFormationTool.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class QuestionService implements IQuestionService {

    Logger log = LoggerFactory.getLogger(QuestionService.class);

    @Override
    public void createQuestion(Question question, IQuestionPersistence questionPersistence) throws Exception {
        if (question == null || question.getTitle() == null || question.getTitle().isEmpty()
                || question.getDescription() == null || question.getDescription().isEmpty()){
            log.debug("Question details is not valid.");
            throw new Exception("Invalid question. Please provide valid details!");
        }
        if (isInvalidQuestionType(question.getQuestionType())){
            log.debug("Question type for given question is not valid.");
            throw new Exception("Invalid question type.");
        }
        question.setId(System.currentTimeMillis());
        question.setCreatedAt(new Date(System.currentTimeMillis()));
        try {
            questionPersistence.createQuestion(question);
        } catch (SQLException e) {
            log.error("Failed to store question: {} in the database, error: {}",question.getTitle(), e.getMessage());
            throw new Exception("Failed to save question:"+ question.getTitle() +". Please contact admin.", e);
        }
    }

    @Override
    public List<Question> getAllUserQuestions
            (Long userId, IQuestionPersistence questionPersistence) {
        return questionPersistence.getAllUserQuestions(userId);
    }

    @Override
    public boolean deleteQuestion(Long questionId,IQuestionPersistence questionPersistence) {
        return questionPersistence.deleteQuestion(questionId);
    }

    @Override
    public List<Question> getAllUserQuestionsSortedByTitle(Long userId, IQuestionPersistence questionPersistence) {
        List<Question> questions = questionPersistence.getAllUserQuestions(userId);
        Collections.sort(questions,Question.titleComparator);
        return questions;
    }

    @Override
    public List<Question> getAllUserQuestionsSortedByDate(Long userId, IQuestionPersistence questionPersistence) {
        List<Question> questions = questionPersistence.getAllUserQuestions(userId);
        Collections.sort(questions,Question.dateComparator);
        return questions;
    }

    public boolean isInvalidQuestionType(int questionTypeId){
        return !(questionTypeId == Question.getNumeric() || questionTypeId == Question.getFreeText()
                || questionTypeId == Question.getMultipleChoiceChooseOne()
                || questionTypeId == Question.getMultipleChoiceChooseMany());
    }
}
