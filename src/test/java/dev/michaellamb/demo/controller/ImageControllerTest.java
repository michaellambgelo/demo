package dev.michaellamb.demo.controller;

import dev.michaellamb.demo.agent.ImageAgent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ImageControllerTest {

    private MockMvc mockMvc;

    private static final String svgUri = "https://sampleweb/smile.svg";

    @Mock
    private ImageAgent imageAgent;

    @InjectMocks
    private ImageController imageController;

    @BeforeEach
    public void setUpBefore() {
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    void testGetSvgToJpg() throws Exception {
        mockMvc.perform(get("/svg-to-jpg")
                        .param("svgUri", svgUri))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetSvgToPng() throws Exception {
        mockMvc.perform(get("/svg-to-png")
                        .param("svgUri", svgUri))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
