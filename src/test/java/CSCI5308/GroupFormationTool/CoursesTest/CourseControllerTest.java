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
public class CourseControllerTest {
    private static final String ID = "id";

    @Autowired
    MockMvc mockMvc;

    @Test
    public void courseTest() throws Exception {
        mockMvc
                .perform(get("/course/course")
                        .param(ID, String.valueOf(1))
                        .with(user("admin").password("pass")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("course"));
    }
}
