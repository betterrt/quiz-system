package com.jrt.quizsystem.service;

import com.jrt.quizsystem.dao.FeedbackDao;
import com.jrt.quizsystem.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class FeedbackService {

   private final FeedbackDao feedbackDao;
   @Autowired
   public FeedbackService(FeedbackDao feedbackDao) {
      this.feedbackDao = feedbackDao;
   }

   public void createNewFeedback(int starRating, String feedbackText, int userId) {
      Feedback feedback = new Feedback();
      feedback.setRating(Integer.valueOf(starRating));
      feedback.setMessage(feedbackText);
      feedback.setUserId(userId);
      feedback.setDate(new Timestamp(new Date().getTime()));
      feedbackDao.addFeedback(feedback);
   }

   public List<Feedback> getAllFeedback() {
      return feedbackDao.getAllFeedbacks();
   }
}
