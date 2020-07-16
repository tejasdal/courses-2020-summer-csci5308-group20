package CSCI5308.GroupFormationTool.SurveyManagementTest;

import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyService;
import CSCI5308.GroupFormationTool.SurveyManagement.SurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManagement.SurveyService;

import static org.mockito.Mockito.mock;

public class SurveyManagementAbstractFactoryTest {

    private static SurveyManagementAbstractFactoryTest uniqueInstance = null;

    private final SurveyPersistence surveyPersistenceMock;
    private ISurveyService surveyService;

    private SurveyManagementAbstractFactoryTest() {
        surveyPersistenceMock = mock(SurveyPersistence.class);
        surveyService = new SurveyService();
    }

    public static SurveyManagementAbstractFactoryTest instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new SurveyManagementAbstractFactoryTest();
        }
        return uniqueInstance;
    }

    public ISurveyPersistence getSurveyPersistenceMock() {
        return surveyPersistenceMock;
    }

    public ISurveyService getSurveyService() {
        return surveyService;
    }
}
