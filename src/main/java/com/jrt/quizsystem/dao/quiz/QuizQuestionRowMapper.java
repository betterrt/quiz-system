package com.jrt.quizsystem.dao.quiz;

import com.jrt.quizsystem.domain.quiz.QuizQuestion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class QuizQuestionRowMapper implements RowMapper<QuizQuestion> {
   @Override
   public QuizQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
      QuizQuestion quizQuestion = new QuizQuestion();
      quizQuestion.setQqId(rs.getInt("qq_id"));
      quizQuestion.setQuizId(rs.getInt("quiz_id"));
      quizQuestion.setQuestionId(rs.getInt("question_id"));
      quizQuestion.setUserChoiceId(rs.getInt("user_choice_id"));
      quizQuestion.setOrderNum(rs.getInt("order_num"));
      return quizQuestion;
   }
}
