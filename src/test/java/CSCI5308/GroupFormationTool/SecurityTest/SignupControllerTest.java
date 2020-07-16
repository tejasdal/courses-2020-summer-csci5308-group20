package CSCI5308.GroupFormationTool.SecurityTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
    private final String FIRST_NAME = "firstName";
    private final String LAST_NAME = "lastName";
    private final String EMAIL = "email";

    @Test
    public void displaySignUpTest() throws Exception {
        mockMvc.perform(get("/signup"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void processSignUpTest() throws Exception {
        mockMvc.perform(post("/signup")
                .param(USERNAME, "B00841234")
                .param(PASSWORD, "dal123@1818")
                .param(PASSWORD_CONFIRMATION,"dal123@1818")
                .param(FIRST_NAME, "Dal Test")
                .param(LAST_NAME, "Test Last")
                .param(EMAIL, "dal@dal.ca"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
