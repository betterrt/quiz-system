package com.jrt.quizsystem.dao.quiz;

import com.jrt.quizsystem.domain.quiz.Choice;
import com.jrt.quizsystem.domain.quiz.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChoiceDao {
   private final JdbcTemplate jdbcTemplate;
   private final ChoiceRowMapper choiceRowMapper;

   private final QuizQuestionRowMapper qqRowMapper;

   @Autowired
   public ChoiceDao(JdbcTemplate jdbcTemplate, QuizQuestionRowMapper qqRowMapper,
                    ChoiceRowMapper choiceRowMapper) {
      this.jdbcTemplate = jdbcTemplate;
      this.choiceRowMapper = choiceRowMapper;
      this.qqRowMapper = qqRowMapper;
   }

   /**
    * update a choice of a given question
    * @param choiceId
    * @param description
    */
   public void updateChoice(int choiceId, String description) {
      String query = "UPDATE Choice SET description = ? WHERE choice_id = ?";
      jdbcTemplate.update(query, description, choiceId);
   }

   /**
    * return all choices of a given question
    * @param questionId
    * @return
    */
   public List<Choice> getAllChoicesOfQuestion(int questionId) {
      String query = "SELECT * FROM Choice WHERE question_id = ?";
      List<Choice> list = jdbcTemplate.query(query, choiceRowMapper, questionId);
      return list;
   }


   /**
    * return a user choice of a given question in a given quiz
    * @param quizId
    * @param questionId
    * @return
    */
   public Choice getUserChoiceByQuizQuestion(int quizId, int questionId) {
      String query1 = "SELECT * FROM QuizQuestion WHERE quiz_id = ?";
      List<QuizQuestion> list = jdbcTemplate.query(query1, qqRowMapper, quizId);
      // Key: question_id, value: user choice of that question
      Map<Integer, Choice> map = new HashMap<>();
      for(QuizQuestion qq: list) {
         // user_choice_id = -1 when user didn't make a choice
         int userChoiceId = qq.getUserChoiceId();
         Choice choice = null;
         if(userChoiceId == -1) {
            map.put(qq.getQuestionId(), null);
         } else {
            String query2 = "SELECT * FROM Choice WHERE choice_id = ?";
            choice = jdbcTemplate.queryForObject(query2, choiceRowMapper, userChoiceId);
            map.put(qq.getQuestionId(), choice);
         }
      }
      return map.get(questionId);
   }


   /**
    * add a choice to a given question
    * @param choice
    */
   public void addChoice(Choice choice) {
      String query = "INSERT INTO Choice (question_id, description, is_correct) values (?, ?, ?)";
      jdbcTemplate.update(query, choice.getQuestionId(),
            choice.getDescription(), choice.getIsCorrect());
   }

   public boolean isRightAnswer(int choiceId) {
      String query = "SELECT * FROM Choice WHERE choice_id = ?";
      List<Choice> list = jdbcTemplate.query(query, choiceRowMapper, choiceId);
      return list.get(0).getIsCorrect();
   }
}
