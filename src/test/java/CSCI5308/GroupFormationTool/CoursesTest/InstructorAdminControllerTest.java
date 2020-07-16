package CSCI5308.GroupFormationTool.CoursesTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InstructorAdminControllerTest {
    private static final String ID = "id";
    private static final String DISPLAY_RESULTS = "displayresults";

    @Autowired
    MockMvc mockMvc;

    @Test
    public void instructorAdminTest() throws Exception {
        mockMvc
                .perform(get("/course/instructoradmin")
                        .param(ID, String.valueOf(1L))
                        .with(user("admin").password("pass")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("course"))
                .andExpect(status().isOk());
    }

    @Test
    public void instructorAdminResultsTest() throws Exception {
        mockMvc
                .perform(get("/course/instructoradminresults")
                        .param(ID, String.valueOf(1L))
                        .param(DISPLAY_RESULTS, String.valueOf(true))
                        .with(user("admin").password("pass")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("course"));
    }

    @Test
    public void enrollTATest() throws Exception {
        mockMvc
                .perform(get("/course/enrollta")
                        .param(ID, String.valueOf(1L))
                        .with(user("admin").password("pass")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("course"));
    }
}
