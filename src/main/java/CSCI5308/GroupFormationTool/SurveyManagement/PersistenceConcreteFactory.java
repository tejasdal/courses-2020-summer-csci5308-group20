package CSCI5308.GroupFormationTool.SurveyManagement;

public class PersistenceConcreteFactory extends PersistenceAbstractFactory {
    @Override
    public ISurveyPersistence makePersistence() {
        return new SurveyPersistence();
    }
}
