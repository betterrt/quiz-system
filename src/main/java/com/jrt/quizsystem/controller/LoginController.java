package com.jrt.quizsystem.controller;

import com.jrt.quizsystem.domain.User;
import com.jrt.quizsystem.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        // redirect to /home if user is already logged in
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    // validate that we are always getting a new session after login
    @PostMapping("/login")
    public String postLogin(@RequestParam String email,
                            @RequestParam String password,
                            HttpServletRequest request) {

        Optional<User> possibleUser = loginService.validateLogin(email, password);
        if(possibleUser.isPresent()) {
            // false: if no session exists, return null
            HttpSession oldSession = request.getSession(false);
            // invalidate old session if it exists
            if (oldSession != null) oldSession.invalidate();
            // generate new session
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("user", possibleUser.get());
            // if the user is admin
            if(possibleUser.get().getIsAdmin()) {
                return "redirect:/admin/profile";
            }
            return "redirect:/home";
        } else { // if user details are invalid
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();
        return "login";
    }
}
