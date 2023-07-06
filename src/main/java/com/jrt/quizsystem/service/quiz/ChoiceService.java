package com.jrt.quizsystem.service.quiz;

import com.jrt.quizsystem.dao.quiz.ChoiceDao;
import com.jrt.quizsystem.domain.quiz.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {
   private final ChoiceDao choiceDao;

   @Autowired
   public ChoiceService(ChoiceDao choiceDao) {
      this.choiceDao = choiceDao;
   }

   public void createNewChoice(int questionId, String description, boolean isCorrect) {
      Choice choice = new Choice();
      choice.setQuestionId(questionId);
      choice.setDescription(description);
      choice.setIsCorrect(isCorrect);
      choiceDao.addChoice(choice);
   }

   public void updateChoice(int choiceId, String description) {
      choiceDao.updateChoice(choiceId, description);
   }

   public List<Choice> getAllChoices(int questionId) {
      return choiceDao.getAllChoicesOfQuestion(questionId);
   }

   public Choice getUserChoiceByQuizQuestion(int quizId, int questionId) {
      return choiceDao.getUserChoiceByQuizQuestion(quizId, questionId);
   }


   public boolean isRightAnswer(int choiceId) {
      return choiceDao.isRightAnswer(choiceId);
   }
}
