package CSCI5308.GroupFormationTool.Question;

public class PersistenceConcreteFactory extends PersistenceAbstractFactory {

    @Override
    public IQuestionPersistence makePersistence() {
        return new QuestionPersistence();
    }
}
