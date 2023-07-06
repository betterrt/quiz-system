package com.jrt.quizsystem.dao;

import com.jrt.quizsystem.domain.Feedback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackDao {
   private JdbcTemplate jdbcTemplate;
   private FeedbackRowMapper feedbackRowMapper;

   public FeedbackDao(JdbcTemplate jdbcTemplate, FeedbackRowMapper feedbackRowMapper) {
      this.jdbcTemplate = jdbcTemplate;
      this.feedbackRowMapper = feedbackRowMapper;
   }

   /**
    * Insert a new feedback into table Feedback
    * @param feedback
    */
   public void addFeedback(Feedback feedback){
      String query = "INSERT INTO Feedback (user_id, message, rating, feedback_date) " +
            "values (?, ?, ?, ?)";
      jdbcTemplate.update(query, feedback.getUserId(), feedback.getMessage(),
            feedback.getRating(),feedback.getDate());
   }

   /**
    * Get all feedbacks in table Feedback
    * @return List of users
    */
   public List<Feedback> getAllFeedbacks() {
      String query = "SELECT * FROM Feedback";
      List<Feedback> list = jdbcTemplate.query(query, feedbackRowMapper);
      return list;
   }
}
