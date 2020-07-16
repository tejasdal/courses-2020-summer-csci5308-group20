package CSCI5308.GroupFormationTool.SurveyManagementTest;

import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SurveyServiceTest {

    ISurveyPersistence surveyPersistence;
    ISurveyService surveyService;

    @BeforeEach
    void initiateObjects() {
        surveyPersistence = SurveyManagementAbstractFactoryTest.instance().getSurveyPersistenceMock();
        surveyService = SurveyManagementAbstractFactoryTest.instance().getSurveyService();
    }


    @Test
    public void isSurveyPublishedTest() {
        long surveyId = 1L;
        Mockito.when(surveyPersistence.getSurveyStatus(surveyId)).thenReturn(1);
        assertTrue(surveyService.isSurveyPublished(surveyId, surveyPersistence));
        assertFalse(surveyService.isSurveyPublished(surveyId + 1, surveyPersistence));
    }

    @Test
    public void getAllSurveyQuestionsTest() {
        long courseId = 1L;
        long surveyId = 1L;
        Mockito.when(surveyPersistence.getSurveyIdUsingCourseId(courseId)).thenReturn(surveyId);
        Mockito.when(surveyPersistence.getSurveyStatus(surveyId)).thenReturn(0);
        Mockito.when(surveyPersistence.getAllSurveyQuestions(1L)).thenReturn(new ArrayList<Question>() {
        });
        Map<String, Object> response = new HashMap<>();
        response.put("surveyId", surveyId);
        response.put("status", false);
        response.put("questions", new ArrayList<Question>() {
        });
        assertEquals(surveyService.getAllSurveyQuestions(courseId, surveyPersistence), response);
    }

    @Test
    public void addQuestionPageTest() {
        long courseId = 1;
        long surveyId = 1;
        Mockito.when(surveyPersistence.getAllInstructorQuestionsUsingCourseId(courseId, surveyId)).thenReturn(new ArrayList<>());
        Mockito.when(surveyPersistence.getAllSurveyQuestions(surveyId)).thenReturn(new ArrayList<Question>() {
        });
        Map<String, Object> response = new HashMap<>();
        response.put("addedQuestion", new ArrayList<Question>());
        response.put("availableQuestions", new ArrayList<Question>());
        assertEquals(surveyService.addQuestionPage(courseId, surveyId, surveyPersistence), response);
    }

    @Test
    public void addQuestionToSurveyTest() {
        long questionId = 1;
        long surveyId = 1;
        Mockito.when(surveyPersistence.addQuestionToSurvey(surveyId, questionId)).thenReturn(true);
        Mockito.when(surveyPersistence.getSurveyStatus(surveyId)).thenReturn(1);
        surveyService.addQuestionToSurvey(surveyId, questionId, surveyPersistence);
    }

    @Test
    public void deleteQuestionFromSurveyTest() {
        long questionId = 1;
        long surveyId = 1;
        Mockito.when(surveyPersistence.deleteQuestionFromSurvey(surveyId, questionId)).thenReturn(true);
        Mockito.when(surveyPersistence.getSurveyStatus(surveyId)).thenReturn(1);
        surveyService.deleteQuestionFromSurvey(surveyId, questionId, surveyPersistence);
    }

    @Test
    public void publishSurveyTest() {
        long surveyId = 1;
        Mockito.when(surveyPersistence.publishSurvey(surveyId)).thenReturn(true);
        assertTrue(surveyService.publishSurvey(surveyId, surveyPersistence));
    }

    @Test
    public void unpublishSurveyTest() {
        long surveyId = 1;
        Mockito.when(surveyPersistence.unpublishSurvey(surveyId)).thenReturn(true);
        assertTrue(surveyService.unpublishSurvey(surveyId, surveyPersistence));
    }

    @Test
    public void displaySurveyQuestionsToStudentsTest() {
        //TODO jaspreet will write
    }
}
