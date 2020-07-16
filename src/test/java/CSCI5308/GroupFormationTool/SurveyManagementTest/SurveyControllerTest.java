package CSCI5308.GroupFormationTool.SurveyManagementTest;

import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManagement.ISurveyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SurveyControllerTest {

    private static String userId;
    private static String courseId;
    private static String surveyId;
    private static String questionId;
    private static String bannerId;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ISurveyService surveyService;

    @MockBean
    ISurveyPersistence surveyPersistence;

    @BeforeAll
    public static void initializeValues(){
        userId = "1";
        courseId = "2";
        surveyId = "3";
        questionId = "4";
        bannerId = "B001";
    }

    @Test
    public void surveyManagementPageTest() throws Exception {
        mockMvc.perform(post("/instructor/survey")
                .param("courseId",courseId)
                .param("userId",userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("courseId","userId"));

    }

    @Test
    public void deleteQuestionsTest() throws Exception {
        mockMvc.perform(post("/instructor/survey/deletequestion")
                .param("courseId",courseId)
                .param("surveyId",surveyId)
                .param("userId",userId)
                .param("questionId",questionId))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("userId","surveyId","courseId"));
    }

    @Test
    public void publishSurveyTest() throws Exception {
        mockMvc.perform(get("/instructor/survey/publish")
                .param("courseId",courseId)
                .param("surveyId",surveyId)
                .param("userId",userId))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("userId","surveyId","courseId"));
    }

    @Test
    public void unpublishSurveyTest() throws Exception {
        mockMvc.perform(get("/instructor/survey/unpublish")
                .param("courseId",courseId)
                .param("surveyId",surveyId)
                .param("userId",userId))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("userId","surveyId","courseId"));
    }

    @Test
    public void displaySurveyQuestionToStudentTest() throws Exception {
        mockMvc.perform(get("/student/survey/questions")
                .param("courseId",courseId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("courseId"));
    }

    @Test
    public void submitSurveyTest() throws Exception {
        mockMvc.perform(get("/student/survey/submit")
                .param("surveyId",surveyId)
                .param("bannerId",bannerId))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }


}
