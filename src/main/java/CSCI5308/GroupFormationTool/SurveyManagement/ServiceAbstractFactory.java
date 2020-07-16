package CSCI5308.GroupFormationTool.SurveyManagement;

public abstract class ServiceAbstractFactory {

    private static ServiceAbstractFactory uniqueInstance = null;

    protected ServiceAbstractFactory(){
    }

    public static ServiceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new ServiceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract ISurveyService makeService();
    public abstract ISurvey makeSurvey();
}
