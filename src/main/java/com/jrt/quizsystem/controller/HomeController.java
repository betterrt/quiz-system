package com.jrt.quizsystem.controller;


import com.jrt.quizsystem.domain.User;
import com.jrt.quizsystem.domain.quiz.Quiz;
import com.jrt.quizsystem.service.quiz.CategoryService;
import com.jrt.quizsystem.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class HomeController {
   private CategoryService categoryService;
   private QuizService quizService;

   @Autowired
   public HomeController(CategoryService categoryService, QuizService quizService) {
      this.categoryService = categoryService;
      this.quizService = quizService;
   }

   @GetMapping("/home")
   public String getHome(HttpServletRequest request, Model model) {
      HttpSession session = request.getSession();
      User currentUser = (User) session.getAttribute("user");
      // render categories
      model.addAttribute("categories", categoryService.getAllCategories());
      // render taken quizes
      model.addAttribute("takenQuizes", quizService.getAllQuizByUserId(currentUser.getId()));
      return "home";
   }

}
