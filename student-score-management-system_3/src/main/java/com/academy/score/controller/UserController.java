package com.academy.score.controller;

import com.academy.score.domain.User;
import com.academy.score.exception.IncorrectPasswordException;
import com.academy.score.exception.UserNotExistException;
import com.academy.score.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("email") String email,
                          @RequestParam("password") String password,
                          HttpServletRequest request) {
        checkExistUser(email);
        User user = userRepository.getUser(email);

        checkPassword(password, user);

        HttpSession session = request.getSession(true);
        session.setAttribute("LoginUser", user);

        return "studentRegister";
    }

    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request) {
        request.getSession(false).removeAttribute("LoginUser");
        return "/loginForm";
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotExistException(UserNotExistException ex, Model model) {
        model.addAttribute("exception", ex);
        return "error";
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleIncorrectPasswordException(IncorrectPasswordException ex, Model model) {
        model.addAttribute("exception", ex);
        return "error";
    }

    private void checkExistUser(String email) {
        if (!userRepository.exists(email)) {
            throw new UserNotExistException();
        }
    }

    private static void checkPassword(String password, User user) {
        if (!user.getPassword().equals(password)) {
            throw new IncorrectPasswordException();
        }
    }

}
