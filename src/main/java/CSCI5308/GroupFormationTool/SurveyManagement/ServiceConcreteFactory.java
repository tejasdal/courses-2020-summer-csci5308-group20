package CSCI5308.GroupFormationTool.SurveyManagement;

public class ServiceConcreteFactory extends ServiceAbstractFactory {
    @Override
    public ISurveyService makeService() {
        return new SurveyService();
    }

    @Override
    public ISurvey makeSurvey() {
        return new Survey();
    }
}
