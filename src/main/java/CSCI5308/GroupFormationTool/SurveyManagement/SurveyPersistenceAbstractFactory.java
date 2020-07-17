package CSCI5308.GroupFormationTool.SurveyManagement;

public abstract class SurveyPersistenceAbstractFactory {

    private static SurveyPersistenceAbstractFactory uniqueInstance = null;

    protected SurveyPersistenceAbstractFactory(){
    }

    public static SurveyPersistenceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new SurveyPersistenceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract ISurveyPersistence makePersistence();
}
