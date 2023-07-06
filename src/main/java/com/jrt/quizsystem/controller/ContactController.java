package com.jrt.quizsystem.controller;

import com.jrt.quizsystem.domain.Contact;
import com.jrt.quizsystem.domain.User;
import com.jrt.quizsystem.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ContactController {
   private final ContactService contactService;

   @Autowired
   public ContactController(ContactService contactService) {
      this.contactService = contactService;
   }

   @GetMapping("contact")
   public String getContact() {
      return "contact";
   }

   @PostMapping("contact")
   public String postContact(@RequestParam String subject,
                             @RequestParam String message,
                             HttpServletRequest request) {
      Contact contact = new Contact();
      HttpSession session = request.getSession();
      User currentUser = (User) session.getAttribute("user");
      contact.setFirstname(currentUser.getFirstname());
      contact.setLastname(currentUser.getLastname());
      contact.setSubject(subject);
      contact.setMessage(message);
      contactService.createNewContact(contact);
      return "contact";
   }

}
