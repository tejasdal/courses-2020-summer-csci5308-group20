package CSCI5308.GroupFormationTool.SurveyManagement;

public class SurveyConcreteFactory extends SurveyAbstractFactory {

    @Override
    public ISurveyService getService() {
        //TODO check with jaspreet about static SurveyPersistence
        return new SurveyService(new SurveyPersistence());
    }

    @Override
    public ISurveyPersistence getPersistence() {
        return new SurveyPersistence();
    }
}
