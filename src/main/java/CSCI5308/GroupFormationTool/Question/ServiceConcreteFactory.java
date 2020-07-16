package CSCI5308.GroupFormationTool.Question;

public class ServiceConcreteFactory extends ServiceAbstractFactory {
    @Override
    public IQuestionService makeService() {
        return new QuestionService();
    }

    @Override
    public IQuestion makeQuestion() {
        return new Question();
    }

    @Override
    public IQuestionOption makeQuestionOption() {
        return new QuestionOption();
    }
}
