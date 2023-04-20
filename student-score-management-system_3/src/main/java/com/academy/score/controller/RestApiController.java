package com.academy.score.controller;

import com.academy.score.domain.Student;
import com.academy.score.domain.StudentModifyRequest;
import com.academy.score.domain.StudentRegisterRequest;
import com.academy.score.exception.StudentNotExistException;
import com.academy.score.exception.ValidationFailedException;
import com.academy.score.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/students")
public class RestApiController {
    StudentRepository studentRepository;

    public RestApiController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable("studentId") long studentId) {
        if (!studentRepository.exists(studentId)) {
            throw new StudentNotExistException(studentId);
        }
        return studentRepository.getStudent(studentId);
    }

    @PostMapping
    public ResponseEntity<Student> doStudentRegister(@Valid @RequestBody StudentRegisterRequest studentRegisterRequest,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Student student = studentRepository.register(studentRegisterRequest.getName(),
                studentRegisterRequest.getEmail(),
                studentRegisterRequest.getScore(),
                studentRegisterRequest.getComment());

        return ResponseEntity.created(URI.create("/students/" + student.getId())).build();
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> doPutStudent(@PathVariable("studentId") long studentId,
                                                @Valid @RequestBody StudentModifyRequest studentModifyRequest,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        if (!studentRepository.exists(studentId)) {
            throw new StudentNotExistException(studentId);
        }

        Student student = studentRepository.modify(studentId,
                studentModifyRequest.getName(),
                studentModifyRequest.getEmail(),
                studentModifyRequest.getScore(),
                studentModifyRequest.getComment());

        return ResponseEntity.status(200).body(student);

    }

}