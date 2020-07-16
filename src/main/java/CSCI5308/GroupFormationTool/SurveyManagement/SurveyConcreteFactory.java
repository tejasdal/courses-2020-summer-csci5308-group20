package CSCI5308.GroupFormationTool.SurveyManagement;

public class SurveyConcreteFactory extends SurveyAbstractFactory {

    @Override
    public ISurveyService getService() {
        return new SurveyService();
    }

    @Override
    public ISurveyPersistence getPersistence() {
        return new SurveyPersistence();
    }
}
