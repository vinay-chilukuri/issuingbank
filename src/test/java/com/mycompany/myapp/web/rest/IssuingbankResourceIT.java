package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.IssuingbankApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the IssuingbankResource REST controller.
 *
 * @see IssuingbankResource
 */
@SpringBootTest(classes = IssuingbankApp.class)
public class IssuingbankResourceIT {

    private MockMvc restMockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        IssuingbankResource issuingbankResource = new IssuingbankResource();
        restMockMvc = MockMvcBuilders
            .standaloneSetup(issuingbankResource)
            .build();
    }

    /**
     * Test issuingbank
     */
    @Test
    public void testIssuingbank() throws Exception {
        restMockMvc.perform(post("/api/issuingbank/issuingbank"))
            .andExpect(status().isOk());
    }
}
