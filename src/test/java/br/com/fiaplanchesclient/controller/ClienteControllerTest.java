package br.com.fiaplanchesclient.controller;

import br.com.fiaplanchesclient.application.dtos.ClientRequestDto;
import br.com.fiaplanchesclient.application.usecases.CreateClientUseCase;
import br.com.fiaplanchesclient.application.usecases.DeleteClientUseCase;
import br.com.fiaplanchesclient.application.usecases.FindClientUseCase;
import br.com.fiaplanchesclient.application.usecases.UpdateClientUseCase;
import br.com.fiaplanchesclient.infra.dto.ClientDto;
import br.com.fiaplanchesclient.infra.rest.ClientController;
import com.callibrity.logging.test.LogTracker;
import com.callibrity.logging.test.LogTrackerStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClienteControllerTest {

    private MockMvc mockMvc;

    @RegisterExtension
    LogTrackerStub logTracker = LogTrackerStub.create().recordForLevel(LogTracker.LogLevel.INFO)
            .recordForType(ClienteControllerTest.class);

    @Mock
    private CreateClientUseCase createClientUseCase;

    @Mock
    private FindClientUseCase findClientUseCase;

    @Mock
    private UpdateClientUseCase updateClientUseCase;

    @Mock
    private DeleteClientUseCase deleteClientUseCase;

    private Response response;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        ClientController clientController = new ClientController(createClientUseCase,
                findClientUseCase,
                updateClientUseCase,
                deleteClientUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCriarCliente() throws Exception {
        var clientDtoRequest = new ClientRequestDto("38037984850", "Daniel Tavares");

        when(createClientUseCase.cadastra(any(ClientDto.class)))
                .thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(post("/v1/client/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDtoRequest)))
//                    .andDo(print())
                .andExpect(status().isCreated());
        verify(createClientUseCase, times(1))
                .cadastra(any(ClientDto.class));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
