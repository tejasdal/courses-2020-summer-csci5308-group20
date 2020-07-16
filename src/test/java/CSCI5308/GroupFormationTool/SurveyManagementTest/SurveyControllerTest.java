package CSCI5308.GroupFormationTool.SurveyManagementTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void initializeValues(){
        userId = "1";
        courseId = "2";
        surveyId = "3";
    }

    @Test
    public void surveyManagementPageTest() throws Exception {
        mockMvc.perform(get("/instructor/survey")
                .param("courseId",courseId)
                .param("userId",userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("courseId","userId"));

    }

//    @Test
//    public void addQuestionsToSurveyTest() throws Exception {
//        mockMvc.perform(get("instructor/survey/addquestions")
//                .param("courseId",courseId)
//                .param("surveyId",surveyId)
//                .param("userId",userId))
//                .andDo(print())
//                .andExpect(model().attributeExists("userId","surveyId","courseId"));
//    }

}
