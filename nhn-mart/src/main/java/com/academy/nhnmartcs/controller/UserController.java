package com.academy.nhnmartcs.controller;

import com.academy.nhnmartcs.domain.Authorization;
import com.academy.nhnmartcs.domain.User;
import com.academy.nhnmartcs.exception.LogInFailedException;
import com.academy.nhnmartcs.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cs")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("password") String password,
                          HttpServletRequest request) {
        checkIdPw(id, password);

        User user = userRepository.getUser(id);

        HttpSession session = request.getSession(true);
        session.setAttribute("LoginUser", user);

        if (checkAuthorization(user) == Authorization.ADMIN) {
            return "redirect:/cs/csInquiryList";
        }

        return "redirect:/cs";
    }


    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request) {
        request.getSession(false).removeAttribute("LoginUser");
        request.getSession(false).invalidate();
        return "redirect:/cs/login";
    }

    @ExceptionHandler(LogInFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAuthenticationFailedException(LogInFailedException ex, Model model) {
        String[] splitExMessage = ex.toString().split(":");

        model.addAttribute("exception", splitExMessage[1]);
        model.addAttribute("exception", ex);
        return "error";
    }

    private void checkIdPw(String id, String password) {
        checkUserExistWithId(id);
        User user = userRepository.getUser(id);
        if (!user.getPassword().equals(password)) {
            throw new LogInFailedException();
        }
    }

    private void checkUserExistWithId(String id) {
        if (!userRepository.exist(id)) {
            throw new LogInFailedException();
        }
    }

    private Authorization checkAuthorization(User user) {
        Authorization authorization = user.getAuthorization();
        if (authorization == Authorization.ADMIN) {
            return Authorization.ADMIN;
        }
        return Authorization.CUSTOMER;
    }

}
