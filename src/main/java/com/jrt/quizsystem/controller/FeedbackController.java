package com.jrt.quizsystem.controller;


import com.jrt.quizsystem.domain.User;
import com.jrt.quizsystem.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class FeedbackController {
   private final FeedbackService feedbackService;
   @Autowired
   public FeedbackController(FeedbackService feedbackService) {
      this.feedbackService = feedbackService;
   }
   @GetMapping("/feedback")
   public String getFeedback() {
      return "feedback";
   }

   @PostMapping("/feedback")
   public String postFeedback(@RequestParam String starRating,
                              @RequestParam String feedbackText,
                              HttpServletRequest request) {
      HttpSession session = request.getSession();
      User currentUser = (User) session.getAttribute("user");
      feedbackService.createNewFeedback(Integer.valueOf(starRating),
            feedbackText, currentUser.getId());
      return "feedback";
   }

}
