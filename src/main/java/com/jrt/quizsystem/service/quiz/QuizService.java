package com.jrt.quizsystem.service.quiz;

import com.jrt.quizsystem.dao.quiz.QuizDao;
import com.jrt.quizsystem.dao.quiz.QuizQuestionDao;
import com.jrt.quizsystem.domain.User;
import com.jrt.quizsystem.domain.quiz.Question;
import com.jrt.quizsystem.domain.quiz.Quiz;
import com.jrt.quizsystem.domain.quiz.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class QuizService {
   private final QuizDao quizDao;
   private final QuizQuestionDao qqDao;
   private final ChoiceService choiceService;

   @Autowired
   public QuizService(QuizDao quizDao, QuizQuestionDao qqDao,
                      ChoiceService choiceService) {
      this.quizDao = quizDao;
      this.qqDao = qqDao;
      this.choiceService = choiceService;
   }

   public int insertWhenQuizStart(int userId, int categoryId, Date startTime) {
      Quiz quiz = new Quiz();
      quiz.setUserId(userId);
      quiz.setCategoryId(categoryId);
      quiz.setName("The quiz");
      // will be modified when quiz end
      quiz.setScore(0);
      quiz.setStartTime(new Timestamp(startTime.getTime()));
      // will be modified when quiz end
      quiz.setEndTime(new Timestamp(startTime.getTime()));
      return quizDao.addQuiz(quiz);
   }

   public void updateWhenQuizEnd(int quizId, List<Question> questionList,
                                 int[] answerList, Date endTime) {
      Timestamp endTimestamp = new Timestamp(endTime.getTime());
      // calculate score
      int score = 0;
      for(int choiceId : answerList) {
         // if user selected an answer
         if(choiceId != -1) {
            // if user selected right answer
            if(choiceService.isRightAnswer(choiceId)) {
               score++;
            }
         }
      }
      // update Quiz table
      quizDao.updateQuizEndTime(quizId, score, endTimestamp);
      // update QuizQuestion table
      for(int index = 0; index < questionList.size(); ++index) {
         Question question = questionList.get(index);
         QuizQuestion quizQuestion = new QuizQuestion();
         quizQuestion.setQuizId(quizId);
         quizQuestion.setQuestionId(question.getQuestionId());
         // userChoiceId can be -1, meaning user didn't make a choice
         quizQuestion.setUserChoiceId(answerList[index]);
         quizQuestion.setOrderNum(index);
         qqDao.inserQuizQuestion(quizQuestion);
      }

   }

   public List<Quiz> getAllQuizByUserId(int userId) {
      return quizDao.getAllQuizByUserId(userId);
   }

   public Quiz getQuizById(int quizId) {
      return quizDao.getQuizById(quizId);
   }

   public List<Quiz> getAllQuizesInDateOrder(List<User> listOfUsers) {
      List<Quiz> quizList = new ArrayList<>();
      for(User user : listOfUsers) {
         quizList.addAll(quizDao.getAllQuizByUserId(user.getId()));
      }
      return makeQuizesInDateOrder(quizList);
   }

   /**
    * sort quizes in decreasing order of dates (i.e. from current to past)
    * @param quizList
    * @return
    */
   public List<Quiz> makeQuizesInDateOrder(List<Quiz> quizList) {
      Collections.sort(quizList, (q1, q2) -> {
         Date d1 = new Date(q1.getStartTime().getTime());
         Date d2 = new Date(q2.getStartTime().getTime());
         return -1 * d1.compareTo(d2);
      });
      return quizList;
   }

   public int getUserIdByQuizId(int quizId) {
      return quizDao.getUserIdByQuizId(quizId);
   }

   public List<Quiz> getAllQuizByCategoryId(int categoryId) {
      return quizDao.getAllQuizByCategoryId(categoryId);
   }
}
