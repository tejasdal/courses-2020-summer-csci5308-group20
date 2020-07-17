package CSCI5308.GroupFormationTool.SurveyManagement;

public class DefaultFactory extends SurveyFactory {
	
	@Override
    public ISurveyPersistence createPersistence() {
        return new SurveyPersistence();
    }

	@Override
	public ISurveyService createService() {
		return new SurveyService();
	}

	@Override
	public ISurveyResponse createSurveyResponse() {
		return new SurveyResponse();
	}
}
