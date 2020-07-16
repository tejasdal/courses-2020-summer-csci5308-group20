package CSCI5308.GroupFormationTool.SurveyManagementTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyResponse;
import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyService;
import CSCI5308.GroupFormationTool.SurveyManagement.SurveyFactory;

public class DefaultFactoryTest {

	@Test
    public void createPersistenceTest() {
		ISurveyPersistence persistence = SurveyFactory.instance().createPersistence();
    	assertNotNull(persistence);
    }

	@Test
    public void createServiceTest() {
		ISurveyService service = SurveyFactory.instance().createService();
    	assertNotNull(service);
    }
    
    @Test
    public void createSurveyResponseTest() {
    	ISurveyResponse response = SurveyFactory.instance().createSurveyResponse();
    	assertNotNull(response);
    }
}
