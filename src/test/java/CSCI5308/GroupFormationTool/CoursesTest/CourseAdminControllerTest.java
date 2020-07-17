package CSCI5308.GroupFormationTool.CoursesTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseAdminControllerTest {


    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String INSTRUCTOR = "instructor";

    private final GrantedAuthority adminAuthority = (GrantedAuthority) () -> "ADMIN";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void courseTest() throws Exception {
        mockMvc
                .perform(get("/admin/course")
                        .with(user("admin").password("pass").authorities(adminAuthority)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("courses"));
    }

    @Test
    public void assignInstructorTest() throws Exception {
        mockMvc
                .perform(get("/admin/assigninstructor")
                        .param(ID, String.valueOf(1))
                        .with(user("admin").password("pass").authorities(adminAuthority)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"));
    }

    @Test
    public void deleteCourseTest() throws Exception {
        mockMvc
                .perform(get("/admin/deletecourse")
                        .param(ID, String.valueOf(1))
                        .with(user("admin").password("pass").authorities(adminAuthority)))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void createCourseTest() throws Exception {
        mockMvc
                .perform(post("/admin/createcourse")
                        .param(TITLE, "courseName")
                        .with(user("admin").password("pass").authorities(adminAuthority)))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }


    @Test
    public void assignInstructorToCourseTest() throws Exception {
        this.mockMvc
                .perform(post("/admin/assigninstructor")
                        .param(ID, String.valueOf(1L))
                        .param(INSTRUCTOR, new String[]{String.valueOf(1), String.valueOf(1)})
                        .with(user("admin").password("pass").authorities(adminAuthority)))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}