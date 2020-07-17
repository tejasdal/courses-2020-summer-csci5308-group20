package CSCI5308.GroupFormationTool.SurveyManagement;

public abstract class SurveyServiceAbstractFactory {

    private static SurveyServiceAbstractFactory uniqueInstance = null;

    protected SurveyServiceAbstractFactory(){
    }

    public static SurveyServiceAbstractFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new SurveyServiceConcreteFactory();
        }
        return uniqueInstance;
    }

    public abstract ISurveyService makeService();
    public abstract ISurvey makeSurvey();
}
