package com.academy.score.controller;

import com.academy.score.domain.Student;
import com.academy.score.domain.StudentModifyRequest;
import com.academy.score.exception.StudentNotExistException;
import com.academy.score.exception.UserNotExistException;
import com.academy.score.exception.ValidationFailedException;
import com.academy.score.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @ModelAttribute("student")
    public Student getStudent(@PathVariable("studentId") long studentId) {
        if (!studentRepository.exists(studentId)) {
            throw new StudentNotExistException(studentId);
        }
        return studentRepository.getStudent(studentId);
    }

    @GetMapping("/{studentId}")
    public String studentView(Student student, Model model) {
        model.addAttribute("student", student);
        return "studentView";
    }

    @GetMapping(value = "/{studentId}", params = "hideScore=yes")
    public String studentViewHideScore(Student student,
                                       Model model) {

        model.addAttribute("hideScore", "hideScore");
        model.addAttribute("student", student);
        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public ModelAndView studentModifyForm(Student student, Model model) {
        ModelAndView mav = new ModelAndView("studentModify");
        mav.addObject("student", student);

        return mav;
    }

    @PostMapping("/{studentId}/modify")
    public String studentModify(@Valid @ModelAttribute StudentModifyRequest studentModifyRequest,
                                BindingResult bindingResult,
                                Student student,
                                Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        if (!studentRepository.exists(student.getId())) {
            throw new StudentNotExistException(student.getId());
        }

        Student modifiedStudent = studentRepository.modify(student.getId(),
                studentModifyRequest.getName(),
                studentModifyRequest.getEmail(),
                studentModifyRequest.getScore(),
                studentModifyRequest.getComment());
        model.addAttribute("student", modifiedStudent);
        return "redirect:/student/{studentId}";
    }

    @ExceptionHandler(StudentNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleStudentNotExistException(StudentNotExistException ex, Model model) {
        model.addAttribute("exception", ex);
        return "error";
    }


}
