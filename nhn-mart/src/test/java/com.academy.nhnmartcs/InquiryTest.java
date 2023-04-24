package com.academy.nhnmartcs;

import com.academy.nhnmartcs.controller.InquiryController;
import com.academy.nhnmartcs.domain.Inquiry;
import com.academy.nhnmartcs.domain.InquiryCategory;
import com.academy.nhnmartcs.repository.InquiryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InquiryTest {
    private MockMvc mockMvc;
    InquiryRepository inquiryRepository;

    @BeforeEach
    void setUp() {
        inquiryRepository = mock(InquiryRepository.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new InquiryController(inquiryRepository))
                .alwaysDo(print())
                .build();
    }

    @Test
    void write_inquiry_success() throws Exception {
        Inquiry inquiry = new Inquiry(1, "test", InquiryCategory.COMPLIMENT, "test comment",
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                "testUser", null, false);
        when(inquiryRepository.addInquiryToMap("test", InquiryCategory.COMPLIMENT, "test comment",
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                "testUser", null, false)).thenReturn(inquiry);

        mockMvc.perform(post("/cs/inquiry"))
                .andExpect(status().isOk());


    }

}
