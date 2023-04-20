package com.academy.score.controller;

import com.academy.score.domain.Student;
import com.academy.score.exception.StudentNotExistException;
import com.academy.score.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class RestApiControllerTest {
    private MockMvc mockMvc;
    StudentRepository studentRepository;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(new RestApiController(studentRepository))
                .defaultRequest(get("/students/1").accept(MediaType.APPLICATION_JSON))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("존재하는 학생 정보 조회 성공")
    void getStudentTest() throws Exception {
        Student student = new Student(1, "Kurt", "kurt@cobain.com", 100, "Nirvana");
        when(studentRepository.getStudent(anyLong())).thenReturn(student);
        when(studentRepository.exists(anyLong())).thenReturn(true);

        mockMvc.perform(get("/students/{studentId}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.email").value(student.getEmail()))
                .andExpect(jsonPath("$.score").value(student.getScore()))
                .andExpect(jsonPath("$.comment").value(student.getComment()));
    }

    @Test
    @DisplayName("존재하지 않는 학생 정보 조회 실패")
    void studentNotExistExceptionTest() {
        Throwable throwable = catchThrowable(() ->
                mockMvc.perform(get("/students/{studentId}", "2"))
                        .andExpect(status().isNotFound()));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(StudentNotExistException.class);

    }

    @Test
    @DisplayName("학생 등록 성공")
    void doStudentRegister() throws Exception {
        Student student = new Student(1, "Kurt", "kurt@cobain.com", 100, "Nirvana");
        when(studentRepository.register("Kurt", "kurt@cobain.com", 100, "Nirvana")).thenReturn(student);

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("학생 수정 성공")
    void doPutStudent_success() throws Exception {
        Student student = new Student(1, "Kurt", "kurt@cobain.com", 100, "Nirvana");
        Student studentModified = new Student(1, "Krist", "krist@novoselic.com", 100, "Nirvana");

        when(studentRepository.register("Kurt", "kurt@cobain.com", 100, "Nirvana")).thenReturn(student);
        when(studentRepository.exists(anyLong())).thenReturn(true);
        when(studentRepository.modify(1, "Krist", "krist@novoselic.com", 100, "Nirvana")).thenReturn(studentModified);

        mockMvc.perform(put("/students/{studentId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentModified))).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(studentModified.getId()))
                .andExpect(jsonPath("$.name").value(studentModified.getName()))
                .andExpect(jsonPath("$.email").value(studentModified.getEmail()))
                .andExpect(jsonPath("$.score").value(studentModified.getScore()))
                .andExpect(jsonPath("$.comment").value(studentModified.getComment()));
        ;
    }



}