package com.jrt.quizsystem.dao;

import com.jrt.quizsystem.domain.Feedback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class FeedbackRowMapper implements RowMapper<Feedback> {
   @Override
   public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
      Feedback feedback = new Feedback();
      feedback.setMessage(rs.getString("message"));
      feedback.setRating(rs.getInt("rating"));
      feedback.setUserId(rs.getInt("user_id"));
      feedback.setDate(rs.getTimestamp("feedback_date"));
      return feedback;
   }
}
