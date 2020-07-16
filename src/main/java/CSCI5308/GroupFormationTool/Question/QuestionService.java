package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.CustomExceptions.QuestionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class QuestionService implements IQuestionService {

    Logger log = LoggerFactory.getLogger(QuestionService.class);

    @Override
    public void createQuestion(Question question, IQuestionPersistence questionPersistence) throws QuestionException {
        if (question == null || question.getTitle() == null || question.getTitle().isEmpty()
                || question.getDescription() == null || question.getDescription().isEmpty()){
            log.error("Question details is not valid.");
            throw new QuestionException("Invalid question. Please provide valid details!");
        }
        if (isInvalidQuestionType(question.getQuestionType())){
            log.error("Question type for given question is not valid.");
            throw new QuestionException("Invalid question type.");
        }
        question.setId(System.currentTimeMillis());
        question.setCreatedAt(new Date(System.currentTimeMillis()));
        try {
            questionPersistence.createQuestion(question);
        } catch (SQLException e) {
            log.error("Failed to store question: {} in the database.",question.getTitle(), e);
            throw new QuestionException("Failed to save question:"+ question.getTitle() +". Please contact admin.");
        }
    }

    @Override
    public List<IQuestion> getAllUserQuestions
            (Long userId, IQuestionPersistence questionPersistence) {
        return questionPersistence.getAllUserQuestions(userId);
    }

    @Override
    public boolean deleteQuestion(Long questionId,IQuestionPersistence questionPersistence) {
        return questionPersistence.deleteQuestion(questionId);
    }

    @Override
    public List<IQuestion> getAllUserQuestionsSortedByTitle(Long userId, IQuestionPersistence questionPersistence) {
        List<IQuestion> questions = questionPersistence.getAllUserQuestions(userId);
        Collections.sort(questions,Question.titleComparator);
        return questions;
    }

    @Override
    public List<IQuestion> getAllUserQuestionsSortedByDate(Long userId, IQuestionPersistence questionPersistence) {
        List<IQuestion> questions = questionPersistence.getAllUserQuestions(userId);
        Collections.sort(questions,Question.dateComparator);
        return questions;
    }

    public boolean isInvalidQuestionType(int questionTypeId){
        return !(questionTypeId == Question.getNumeric() || questionTypeId == Question.getFreeText()
                || questionTypeId == Question.getMultipleChoiceChooseOne()
                || questionTypeId == Question.getMultipleChoiceChooseMany());
    }
}
