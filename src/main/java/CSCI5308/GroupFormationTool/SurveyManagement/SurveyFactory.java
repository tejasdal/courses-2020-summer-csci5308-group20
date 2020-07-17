package CSCI5308.GroupFormationTool.SurveyManagement;

public abstract class SurveyFactory {
	private static SurveyFactory uniqueInstance = null;

    protected SurveyFactory(){
    }

    public static SurveyFactory instance(){
        if(uniqueInstance == null){
            uniqueInstance = new DefaultFactory();
        }
        return uniqueInstance;
    }

    public abstract ISurveyPersistence createPersistence();
    
    public abstract ISurveyService createService();
    
    public abstract ISurveyResponse createSurveyResponse();
}
