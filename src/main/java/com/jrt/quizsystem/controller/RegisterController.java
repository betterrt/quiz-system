package com.jrt.quizsystem.controller;

import com.jrt.quizsystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

   private final RegisterService registerService;
   @Autowired
   public RegisterController(RegisterService registerService) {
      this.registerService = registerService;
   }

   @GetMapping("/register")
   public String getRegister() {
      return "register";
   }

   @PostMapping("/register")
   public String postRegister(@RequestParam String firstname,
                              @RequestParam String lastname,
                              @RequestParam String email,
                              @RequestParam String password,
                              @RequestParam String confirmPassword,
                              @RequestParam String phone,
                              @RequestParam String address,
                              Model model) {
      // if two passwords are not same
      if(!registerService.checkPassword(password, confirmPassword)) {
         model.addAttribute("isPasswordInvalid", true);
         return "register";
      }
      if(registerService.submitRegistration(firstname, lastname,
            email, password, phone, address)) {
         // User will be redirected to log_in page if registration is successful
         return "redirect:/login";
      } else {
         // if email has been registered
         model.addAttribute("isEmailInvalid", true);
         return "register";
      }
   }
}
