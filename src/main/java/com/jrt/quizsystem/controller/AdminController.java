package com.jrt.quizsystem.controller;

import com.jrt.quizsystem.domain.User;
import com.jrt.quizsystem.domain.quiz.*;
import com.jrt.quizsystem.service.ContactService;
import com.jrt.quizsystem.service.FeedbackService;
import com.jrt.quizsystem.service.UserService;
import com.jrt.quizsystem.service.quiz.CategoryService;
import com.jrt.quizsystem.service.quiz.ChoiceService;
import com.jrt.quizsystem.service.quiz.QuestionService;
import com.jrt.quizsystem.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AdminController {
   private final UserService userService;
   private final FeedbackService feedbackService;
   private final ContactService contactService;
   private final QuizService quizService;
   private final CategoryService categoryService;
   private final QuestionService questionService;
   private final ChoiceService choiceService;

   @Autowired
   public AdminController(UserService userService, FeedbackService feedbackService,
                          QuizService quizService, CategoryService categoryService,
                          QuestionService questionService, ChoiceService choiceService,
                          ContactService contactService) {
      this.userService = userService;
      this.feedbackService = feedbackService;
      this.quizService = quizService;
      this.categoryService = categoryService;
      this.questionService = questionService;
      this.choiceService = choiceService;
      this.contactService = contactService;
   }

   private void renderProfile(Model model) {
      List<User> listOfUsers = userService.getAllUsers();
      model.addAttribute("users", listOfUsers);
      // key: user_id,  value: user fullname
      Map<Integer, String> mapOfFullName = new HashMap<>();
      for(User user : listOfUsers) {
         mapOfFullName.put(user.getId(), userService.getUserFullName(user.getId()));
      }
      model.addAttribute("mapOfFullName", mapOfFullName);
   }

   @GetMapping("/admin/profile")
   public String getAdminProfile(Model model) {
      renderProfile(model);
      return "admin-profile";
   }

   @PostMapping("/admin/profile")
   public String postAdminProfile(@RequestParam String userStatus,
                                  @RequestParam String userId,
                                  Model model) {
      int flag = Integer.valueOf(userStatus);
      int userID = Integer.valueOf(userId);
      if(flag == 1) {
         userService.activateUser(userID);
      } else {
         userService.suspendUser(userID);
      }
      renderProfile(model);
      return "admin-profile";
   }

   @GetMapping("/admin/feedback")
   public String getFeedback(Model model) {
      model.addAttribute("feedbacks", feedbackService.getAllFeedback());
      return "admin-feedback";
   }

   private void renderByQuizes(List<Quiz> quizList, Model model) {
      model.addAttribute("quizes", quizList);
      // key: quiz_id, value: category name
      Map<Integer, String> mapOfCategory = new HashMap<>();
      // key: quiz_id, value: user full name
      Map<Integer, String> mapOfFullName = new HashMap<>();
      for(Quiz quiz : quizList) {
         Category category = categoryService.getCategoryByQuizId(quiz.getQuizId());
         mapOfCategory.put(quiz.getQuizId(), category.getName());
         int userId = quiz.getUserId();
         mapOfFullName.put(quiz.getQuizId(), userService.getUserFullName(userId));
      }
      model.addAttribute("mapOfCategory", mapOfCategory);
      model.addAttribute("mapOfFullName", mapOfFullName);
      // reset
      model.addAttribute("showItems", true);
   }

   @GetMapping("/admin/quizes")
   public String getQuizes(Model model) {
      List<Quiz> quizList = quizService.getAllQuizesInDateOrder(userService.getAllUsers());
      renderByQuizes(quizList, model);
      return "admin-quizes";
   }

   @PostMapping("/admin/quizes")
   public String postQuizes(HttpServletRequest request, Model model) {
      String categoryName = request.getParameter("categoryName");
      String userIdString = request.getParameter("userId");
      if(categoryName != null) {
         int categoryId = categoryService.getCategoryIdByName(categoryName);
         if(categoryId == -1) {
            model.addAttribute("showItems", false);
         } else {
            List<Quiz> quizList = quizService.getAllQuizByCategoryId(categoryId);
            renderByQuizes(quizService.makeQuizesInDateOrder(quizList), model);
         }
      }
      if(userIdString != null) {
         int userId = Integer.valueOf(userIdString);
         List<User> userList = new ArrayList<>();
         User user = userService.getUserById(userId);
         if(user == null) {
            model.addAttribute("showItems", false);
         } else {
            userList.add(userService.getUserById(userId));
            List<Quiz> quizList = quizService.getAllQuizesInDateOrder(userList);
            renderByQuizes(quizList, model);
         }
      }
      return "admin-quizes";
   }

   @GetMapping("/admin/quizes-result")
   public String getQuizResult(HttpServletRequest request,
                               HttpSession session,
                               Model model) {

      int quizId = Integer.valueOf(request.getParameter("quizIdFromLink"));
      int userId = quizService.getUserIdByQuizId(quizId);
      model.addAttribute("quiz", quizService.getQuizById(quizId));
      model.addAttribute("user", userService.getUserById(userId));

      List<Question> listOfQuestions= questionService.getQuestionsOfQuiz(quizId);
      model.addAttribute("questions", listOfQuestions);
      // key: question index  value: list of all choices of that question
      Map<Integer, List<Choice>> mapOfAllChoices = new HashMap<>();
      // key: question index  value: user choices of that question
      Map<Integer, Choice> mapOfUserChoice = new HashMap<>();
      for(int i = 0; i < listOfQuestions.size(); ++i) {
         Question question = listOfQuestions.get(i);
         int questionId = question.getQuestionId();
         mapOfAllChoices.put(i, choiceService.getAllChoices(questionId));
         mapOfUserChoice.put(i,
               choiceService.getUserChoiceByQuizQuestion(quizId,questionId));
      }
      model.addAttribute("mapOfAllChoices", mapOfAllChoices);
      model.addAttribute("mapOfUserChoice", mapOfUserChoice);
      return "admin-quizes-result";
   }

   @GetMapping("/admin/edit")
   public String getEdit() {
      return "admin-edit";
   }

   @PostMapping("/admin/edit")
   public String postEdit(HttpServletRequest request) {
      String category = request.getParameter("category");
      String desciption = request.getParameter("description");
      String[] choices = new String[4];
      choices[0] = request.getParameter("choice1");
      choices[1] = request.getParameter("choice2");
      choices[2] = request.getParameter("choice3");
      choices[3] = request.getParameter("choice4");
      String rightAnswer = request.getParameter("rightAnswer");
      if(category != null && desciption != null && choices[0] != null && choices[1] != null
      && choices[2] != null && choices[3] != null && rightAnswer != null) {
         int categoryId = categoryService.getCategoryIdByName(category);
         // if this is a valid category
         if(categoryId != -1) {
            int rightAnswerNum = Integer.valueOf(rightAnswer);
            questionService.createNewQuestion(categoryId, desciption);
            int questionId = questionService.getQuestionIdByDescription(desciption);
            for(int i = 0; i < 4; ++i) {
               boolean isCorrect = (i == (rightAnswerNum - 1));
               choiceService.createNewChoice(questionId, choices[i], isCorrect);
            }
         }
      }
      return "admin-edit";
   }

   private void renderEnable(Model model) {
      List<Question> questionList = questionService.getAllQuestions();
      model.addAttribute("questions", questionList);
   }

   @GetMapping("/admin/enable-question")
   public String getEnable(Model model) {
      renderEnable(model);
      return "admin-enable-question";
   }

   @PostMapping("/admin/enable-question")
   public String postEnable(@RequestParam String questionStatus,
                            @RequestParam String questionId,
                            Model model) {
      int flag = Integer.valueOf(questionStatus);
      int questionID = Integer.valueOf(questionId);
      if(flag == 1) {
         questionService.enableQuestion(questionID);
      } else {
         questionService.disableQuestion(questionID);
      }
      renderEnable(model);
      return "admin-enable-question";
   }

   @GetMapping("admin/contact")
   public String showAdminAllContacts(Model model) {
      model.addAttribute("contacts", contactService.getAllContact());
      return "admin-contact";
   }

}
