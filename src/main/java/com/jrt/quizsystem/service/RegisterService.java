package com.jrt.quizsystem.service;

import com.jrt.quizsystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

   private final UserService userService;
   @Autowired
   public RegisterService(UserService userService) {
      this.userService = userService;
   }

   public boolean submitRegistration(String firstname, String lastname, String email,
                                     String password, String phone, String address) {
      User user = new User();
      user.setFirstname(firstname);
      user.setLastname(lastname);
      user.setEmail(email);
      user.setPassword(password);
      user.setPhone(phone);
      user.setAddress(address);
      user.setIsActive(true);
      user.setIsAdmin(false);
      return userService.createNewUser(user);
   }

   public boolean checkPassword(String password1, String password2) {
      return password1.equals(password2);
   }
}
