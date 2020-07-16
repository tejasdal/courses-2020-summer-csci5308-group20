package CSCI5308.GroupFormationTool.QuestionTest;

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
public class QuestionControllerTest {

    private static String userId;
    private static String questionId;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void initializeValues(){
        userId = "1";
        questionId = "2";
    }

    @Test
    public void getAllUserQuestionsTest() throws Exception {
        mockMvc.perform(get("/instructor/questions")
                .param("userId",userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("questions","userId"));

    }

    @Test
    public void getAllUserQuestionsSortedDateTest() throws Exception {
        mockMvc.perform(get("/instructor/questions/dateSort")
                .param("userId",userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("questions","userId"));

    }

    @Test
    public void getAllUserQuestionsSortedTitleTest() throws Exception {
        mockMvc.perform(get("/instructor/questions/titleSort")
                .param("userId",userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("questions","userId"));

    }

    @Test
    public void deleteQuestionTest() throws Exception {
        mockMvc.perform(post("/instructor/deletequestion")
                .param("questionId",questionId)
                .param("userId",userId))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("userId"));
    }

    @Test
    public void createQuestionPostTest() throws Exception {
        mockMvc.perform(post("/question/create"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void createQuestionGetTest() throws Exception {
        mockMvc.perform(get("/instructor/1/question/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("question"));
    }

    @Test
    public void addOptionToQuestionTest() throws Exception {
        mockMvc.perform(post("/question/more-option"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("question"));
    }

    @Test
    public void displayQuestionPrototypeTest() throws Exception {
        mockMvc.perform(post("/question/prototype/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("question"));
    }

}
