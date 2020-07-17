package CSCI5308.GroupFormationTool.SurveyManagementTest;

import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.SurveyManagement.*;

import java.util.List;

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

    public IQuestionCriteriaList makeQuestionCriteriaList() {
        return new QuestionCriteriaList();
    }

    public IQuestionCriteriaList makeQuestionCriteriaListUsingList(List<Question> questionList) {
        return new QuestionCriteriaList(questionList);
    }

    public QuestionWithCriteriaDetails makeQuestionWithCriteriaDetails(Question q, int criteriaType, int criteriaValue) {
        return new QuestionWithCriteriaDetails(q, criteriaType, criteriaType);
    }
}
