package CSCI5308.GroupFormationTool.SurveyManagement;

public class SurveyPersistenceConcreteFactory extends SurveyPersistenceAbstractFactory {
    @Override
    public ISurveyPersistence makePersistence() {
        return new SurveyPersistence();
    }
}
