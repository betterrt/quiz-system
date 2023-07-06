package com.jrt.quizsystem.controller;

import com.jrt.quizsystem.domain.User;
import com.jrt.quizsystem.domain.quiz.Choice;
import com.jrt.quizsystem.domain.quiz.Question;
import com.jrt.quizsystem.domain.quiz.Quiz;
import com.jrt.quizsystem.service.quiz.ChoiceService;
import com.jrt.quizsystem.service.quiz.QuestionService;
import com.jrt.quizsystem.service.quiz.QuizService;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
public class QuizController {
    private QuizService quizService;
    private QuestionService questionService;
    private ChoiceService choiceService;
    // questionList: all questions in the quiz
    private List<Question> questionList;
    // chosenList[i] = the user choiceId of question_i
    private int[] answerList;
    private int quizId;


    @Autowired
    public QuizController(QuizService quizService,
                          QuestionService questionService, ChoiceService choiceService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.choiceService = choiceService;
    }

    /**
     * Help render the quiz, passing parameters
     * @param model
     * @param questionIndex
     */
    private void renderQuiz(Model model, int questionIndex) {
        model.addAttribute("curIndex", questionIndex);
        model.addAttribute("question", questionList.get(questionIndex));
        model.addAttribute("answerList", answerList);
        model.addAttribute("choices", choiceService.
              getAllChoices(questionList.get(questionIndex).getQuestionId()));
    }

    @GetMapping("/quiz")
    public String getQuiz(HttpServletRequest request,
                          HttpSession session,
                          Model model) {
        // initialize/reset all cache
        answerList = new int[5];
        Arrays.fill(answerList, -1);
        // get selected categoryId(this variable is ensured not null)
        int categoryId = Integer.valueOf(request.getParameter("categoryId"));
        // initialize/reset five questions
        questionList = questionService.getQuestionsOfCategory(categoryId);
        // get current user_id
        User currentUser = (User) session.getAttribute("user");
        int userId = currentUser.getId();
        // start quiz, get quiz_id
        quizId = quizService.insertWhenQuizStart(userId, categoryId, new Date());
        renderQuiz(model, 0);
        // start quiz
        return "quiz";
    }

    @PostMapping("/quiz")
    public String submitQuiz(HttpServletRequest request,
                             HttpSession session,
                             Model model) {
        // here these two parameters can both be null,
        // so we can't use @RequestParam
        String selectedChoiceIdString = request.getParameter("selectedChoiceId");
        String nowIndexString = request.getParameter("nowIndex");
        // if user selected an answer
        if(selectedChoiceIdString != null) {
            int selectedChoiceId = Integer.valueOf(selectedChoiceIdString);
            int currentIndex= Integer.valueOf(nowIndexString);
            answerList[currentIndex] = selectedChoiceId;
            renderQuiz(model, currentIndex);
        }

        String nextIndexString = request.getParameter("nextIndex");
        if(nextIndexString != null) {
            int nextIndex = Integer.valueOf(nextIndexString);
            // end of quiz
            if(nextIndex == -1) {
                // end quiz
                quizService.updateWhenQuizEnd(quizId,
                      questionList, answerList, new Date());
                session.setAttribute("curQuizId", quizId);
                return "redirect:/quiz-result";
            }
            // else, keep doing quiz
            renderQuiz(model, nextIndex);
        }
        return "quiz";
    }

    @GetMapping("/quiz-result")
    public String getQuizResult(HttpServletRequest request,
                                HttpSession session,
                                Model model) {
        User currentUser = (User) session.getAttribute("user");
        model.addAttribute("user", currentUser);
        int quizId = 0;
        String fromLink = request.getParameter("quizIdFromLink");
        if(fromLink != null) {
            // get method from link in the home page
            quizId = Integer.valueOf(fromLink);
        }
        else {
            // get method from submit at the end of a quiz
            quizId = (Integer) session.getAttribute("curQuizId");
        }
        model.addAttribute("quiz",
              quizService.getQuizById(quizId));

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
        return "quiz-result";
    }
}
