package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionOption;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.IQuestionService;

public interface QuestionTestAbstractFactory {

    public IQuestionPersistence makeQuestionPersistence();
    public IQuestion makeQuestion();
    public IQuestion makeEmptyQuestion();
    public IQuestionOption makeEmptyQuestionOption();
    public IQuestionOption makeQuestionOption();
}
