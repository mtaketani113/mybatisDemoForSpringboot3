package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.demo.controller.api.CustomerApiController;
import com.example.demo.exceptions.DemoRuntimeException;
import com.example.demo.response.error.ErrorResponse;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DemoControllerAdviceTest {
    @InjectMocks
    private CustomerApiController customerApiController;

    @Mock
    private CustomerService customerService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init() {

        mockMvc = MockMvcBuilders.standaloneSetup(customerApiController)
                .setControllerAdvice(new DemoControllerAdvice())
                .build();
    }

    @Test
    public void handleEmployeeNotFoundExceptionTest() throws Exception {

        when(customerService.searchById(anyString()))
            .thenThrow(new DemoRuntimeException("demoError", "Demo error occurred."));

        var responseString = mockMvc.perform(get("/api/customer/1"))
                .andExpect(status().isInternalServerError())
                .andReturn().getResponse().getContentAsString();

        var errorResponse = objectMapper.readValue(responseString, ErrorResponse.class);

        assertThat(errorResponse.getErrorCode()).isEqualTo("demoError");
        assertThat(errorResponse.getMessage()).isEqualTo("Demo error occurred.");

    }
}
