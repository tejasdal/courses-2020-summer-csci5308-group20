package CSCI5308.GroupFormationTool.SurveyManagement;

public class SurveyServiceConcreteFactory extends SurveyServiceAbstractFactory {
    @Override
    public ISurveyService makeService() {
        return new SurveyService();
    }

    @Override
    public ISurvey makeSurvey() {
        return new Survey();
    }
}
