package org.dal.cs5308.t20.Project.forgotpasstest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ForgotPassControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void forgotPassGetTest() throws Exception {
        this.mockMvc.perform(get("/forgotpass")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("forgotpass")));
    }

    @Test
    public void forgotPassPostTest() throws Exception {
        this.mockMvc.perform(get("/forgotpass"))
                .andDo(print()).andExpect(status().isOk()).andExpect(model().attributeExists("emailModel"));
    }

}