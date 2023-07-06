package com.jrt.quizsystem.dao.quiz;

import com.jrt.quizsystem.domain.quiz.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class QuestionRowMapper implements RowMapper<Question> {
   @Override
   public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
      Question question = new Question();
      question.setQuestionId(rs.getInt("question_id"));
      question.setCategoryId(rs.getInt("category_id"));
      question.setDescription(rs.getString("description"));
      question.setIsActive(rs.getBoolean("is_active"));
      return question;
   }
}
