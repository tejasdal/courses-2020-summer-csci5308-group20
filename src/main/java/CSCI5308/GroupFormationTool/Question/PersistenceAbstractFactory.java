package CSCI5308.GroupFormationTool.Question;

public abstract class PersistenceAbstractFactory {

    private static PersistenceAbstractFactory uniqueInstance = null;

    protected PersistenceAbstractFactory(){}

    public static PersistenceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new PersistenceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract IQuestionPersistence makePersistence();
}
