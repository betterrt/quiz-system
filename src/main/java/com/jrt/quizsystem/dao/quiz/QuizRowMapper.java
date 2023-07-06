package com.jrt.quizsystem.dao.quiz;

import com.jrt.quizsystem.domain.quiz.Quiz;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class QuizRowMapper implements RowMapper<Quiz> {
   @Override
   public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
      Quiz quiz = new Quiz();
      quiz.setQuizId(rs.getInt("quiz_id"));
      quiz.setUserId(rs.getInt("user_id"));
      quiz.setCategoryId(rs.getInt("category_id"));
      quiz.setName(rs.getString("name"));
      quiz.setScore(rs.getInt("score"));
      quiz.setStartTime(rs.getTimestamp("start_time"));
      quiz.setEndTime(rs.getTimestamp("end_time"));
      return quiz;
   }
}
