package org.dal.cs5308.t20.Project.admin;

import org.dal.cs5308.t20.Project.course.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AdminService adminService;

    @Test
    @WithMockUser
    public void adminGetCoursesTest() throws Exception {
        List<Course> list=new ArrayList<Course>();
        list.add(new Course());
        list.add(new Course());

        given(adminService.getAllCourse()).willReturn(list);
        this.mockMvc.perform(get("/admin")).andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("courses",list));
    }

    @Test
    @WithMockUser
    public void courseFormTest() throws Exception {
        Course course=new Course();
        this.mockMvc.perform(get("/courseform")).andDo(print()).andExpect(status().isOk())
                .andExpect(model().attributeExists("course"));

    }

    @Test
    @WithMockUser
    public void addCourseTest() throws Exception {
        Course course=new Course();
        given(adminService.addCourse(course)).willReturn("Test");
        this.mockMvc.perform(post("/addcourse")).andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void delCourseTest() throws Exception {
        given(adminService.delCourse((long) 1)).willReturn("Test");
        this.mockMvc.perform(post("/delcourse").param("reg_no", "1")).andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void addInstructPageTest() throws Exception {

        this.mockMvc.perform(post("/instructF").param("id","2")).andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("id",2L));
    }

    @Test
    @WithMockUser
    public void addInstructorTest() throws Exception{

        this.mockMvc.perform(post("/addinstructor").param("id","2").param("emailId","abc")).andDo(print())
                .andExpect(status().is3xxRedirection());

    }

}
