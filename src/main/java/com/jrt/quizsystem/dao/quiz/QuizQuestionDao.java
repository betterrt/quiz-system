package com.jrt.quizsystem.dao.quiz;

import com.jrt.quizsystem.domain.quiz.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QuizQuestionDao {
   private final JdbcTemplate jdbcTemplate;
   private final QuizQuestionRowMapper qqRowMapper;

   @Autowired
   public QuizQuestionDao(JdbcTemplate jdbcTemplate, QuizQuestionRowMapper qqRowMapper) {
      this.jdbcTemplate = jdbcTemplate;
      this.qqRowMapper = qqRowMapper;
   }

   public void inserQuizQuestion(QuizQuestion qq) {
      String query1 = "INSERT INTO QuizQuestion (quiz_id, question_id, user_choice_id," +
            " order_num) values (?, ?, ?, ?)";
      jdbcTemplate.update(query1, qq.getQuizId(),qq.getQuestionId(),
            qq.getUserChoiceId(), qq.getOrderNum());
   }

}
