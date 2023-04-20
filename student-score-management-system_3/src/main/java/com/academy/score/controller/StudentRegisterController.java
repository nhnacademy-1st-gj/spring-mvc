package com.academy.score.controller;

import com.academy.score.domain.Student;
import com.academy.score.domain.StudentRegisterRequest;
import com.academy.score.exception.ValidationFailedException;
import com.academy.score.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/student/register")
public class StudentRegisterController {
    private final StudentRepository studentRepository;

    public StudentRegisterController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String studentRegisterForm() {
        return "studentRegister";
    }

    @PostMapping
    public String doStudentRegister(@Valid @ModelAttribute StudentRegisterRequest studentRegisterRequest,
                                    BindingResult bindingResult,
                                    Model model
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Student student = studentRepository.register(studentRegisterRequest.getName(),
                studentRegisterRequest.getEmail(), studentRegisterRequest.getScore(), studentRegisterRequest.getComment());

        model.addAttribute("student", student);
        model.addAttribute("studentId", student.getId());

        return "redirect:/student/{studentId}";
    }

    @ExceptionHandler(ValidationFailedException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleValidationFailedException(ValidationFailedException ex, Model model) {
        model.addAttribute("exception", ex);
        return "error";
    }

}
