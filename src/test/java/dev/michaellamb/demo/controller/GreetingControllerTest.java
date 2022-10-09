package dev.michaellamb.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GreetingControllerTest {

    private MockMvc mockMvc;


    @InjectMocks
    private GreetingController sut;

    private static final String ANY = "ANY";

    @BeforeEach
    public void setUpBefore() {
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    @Test
    void testGreeting() throws Exception {
        mockMvc.perform(get("/greeting")
                        .param("name", ANY))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void testHello() throws Exception {
        mockMvc.perform(get("/hello")
                        .param("name", ANY))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
