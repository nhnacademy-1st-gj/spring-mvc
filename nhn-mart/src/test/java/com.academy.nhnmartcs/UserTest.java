package com.academy.nhnmartcs;

import com.academy.nhnmartcs.controller.UserController;
import com.academy.nhnmartcs.domain.Authorization;
import com.academy.nhnmartcs.domain.User;
import com.academy.nhnmartcs.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTest {
    private MockMvc mockMvc;
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userRepository))
                .alwaysDo(print())
                .build();
    }

    @Test
    void login_success() throws Exception {
        User user = new User("testUser", "12345", "Dave", Authorization.CUSTOMER);
        when(userRepository.getUser(user.getId())).thenReturn(user);

        mockMvc.perform(post("/cs/login"))
                .andExpect(status().isOk());
    }
}
