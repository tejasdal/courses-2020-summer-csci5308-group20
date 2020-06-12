package org.dal.cs5308.t20.Project.question;

import org.dal.cs5308.t20.Project.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class QuestionService implements IQuestionService {

    Logger log = LoggerFactory.getLogger(QuestionService.class);
    private IQuestionPersistence questionPersistence= Factory.getQuestionPersistence();

    @Override
    public void createQuestion(Question question) throws QuestionException {
        if (question == null || question.getTitle() == null || question.getTitle().isEmpty()
                || question.getDescription() == null || question.getDescription().isEmpty()){
            log.error("Question details is not valid.");
            throw new QuestionException("Invalid question. Please provide valid details!");
        }
        if (!isValidQuestionType(question.getQuestionType())){
            log.error("Question type for given question is not valid.");
            throw new QuestionException("Invalid question type.");
        }
        question.setId(System.currentTimeMillis());
        try {
            this.questionPersistence.addQuestion(question);
        } catch (SQLException e) {
            log.error("Failed to store question: {} in the database.",question.getTitle(), e);
            throw new QuestionException("Failed to save question:"+ question.getTitle() +". Please contact admin.");
        }
    }

    public boolean isValidQuestionType(int questionTypeId){
        return (questionTypeId == Question.getNumeric() || questionTypeId == Question.getFreeText()
                || questionTypeId == Question.getMultipleChoiceChooseOne()
                || questionTypeId == Question.getMultipleChoiceChooseMany());
    }
}
