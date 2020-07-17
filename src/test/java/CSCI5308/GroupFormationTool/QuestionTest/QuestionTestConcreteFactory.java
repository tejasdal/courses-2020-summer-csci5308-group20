package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Question.*;

import java.sql.Date;
import java.util.ArrayList;

public class QuestionTestConcreteFactory implements QuestionTestAbstractFactory {
    @Override
    public IQuestionPersistence makeQuestionPersistence() {
        return new QuestionPersistenceMock();
    }

    @Override
    public IQuestion makeQuestion() {
        return  new Question(1L, "Test Question", "Testing",
                1L, 1, new Date(System.currentTimeMillis()), new ArrayList<>());
    }

    @Override
    public IQuestion makeEmptyQuestion() {
        return new Question();
    }

    @Override
    public IQuestionOption makeEmptyQuestionOption() {
        return new QuestionOption();
    }

    @Override
    public IQuestionOption makeQuestionOption() {
        return new QuestionOption(1L, "Test Option", 1);
    }

}
