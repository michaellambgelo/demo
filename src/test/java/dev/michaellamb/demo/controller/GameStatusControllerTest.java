package dev.michaellamb.demo.controller;

import dev.michaellamb.demo.agent.GameStatusAgent;
import dev.michaellamb.demo.model.GameStatusResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GameStatusControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GameStatusAgent gameStatusAgent;

    @InjectMocks
    private GameStatusController sut;

    @BeforeEach
    public void setUpBefore() {
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    @DisplayName("Get valheim status and return 200 response")
    @Test
    void testGetValheimStatus() throws Exception {

        final String ANY = "ANY";
        final GameStatusResponse gameStatusResponse = new GameStatusResponse();
        gameStatusResponse.setUp(true);
        when(gameStatusAgent.getValheimInstanceStatus(anyString(), anyString())).thenReturn(gameStatusResponse);

        mockMvc.perform(get("/steam/valheim")
                        .param("address", ANY)
                        .param("key", ANY))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
