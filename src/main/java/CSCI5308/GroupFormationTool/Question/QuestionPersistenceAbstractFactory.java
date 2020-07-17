package CSCI5308.GroupFormationTool.Question;

public abstract class QuestionPersistenceAbstractFactory {

    private static QuestionPersistenceAbstractFactory uniqueInstance = null;

    protected QuestionPersistenceAbstractFactory(){}

    public static QuestionPersistenceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new QuestionPersistenceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IQuestionPersistence makePersistence();
}
