package CSCI5308.GroupFormationTool.SurveyManagement;

public abstract class SurveyAbstractFactory {

    private static SurveyAbstractFactory uniqueInstance = null;

    protected SurveyAbstractFactory(){
    }

    public static SurveyAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new SurveyConcreteFactory();
        }
        return uniqueInstance;
    }
    public abstract ISurveyService getService();
    public abstract ISurveyPersistence getPersistence();
}
