package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.service.EmailService;
import com.prabeshcodes.student.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmailControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmailService emailService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private EmailController emailController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();
    }

    @Test
    public void testSendMail_Success() throws Exception {

        when(emailService.sendNewProductMail(anyString(), anyString(), anyString())).thenReturn(true);


        mockMvc.perform(post("/mail/sendmail")
                        .param("to", "test@example.com")
                        .param("subject", "Test Subject")
                        .param("text", "Test Body")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    public void testSendMail_Failure() throws Exception {
        // Arrange
        when(emailService.sendNewProductMail(anyString(), anyString(), anyString())).thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/mail/sendmail")
                        .param("to", "test@example.com")
                        .param("subject", "Test Subject")
                        .param("text", "Test Body")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }
}
