package CSCI5308.GroupFormationTool.Question;

public class QuestionPersistenceConcreteFactory extends QuestionPersistenceAbstractFactory {

    @Override
    public IQuestionPersistence makePersistence() {
        return new QuestionPersistence();
    }
}
