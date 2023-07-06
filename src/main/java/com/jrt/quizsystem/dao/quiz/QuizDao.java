package com.jrt.quizsystem.dao.quiz;

import com.jrt.quizsystem.domain.User;
import com.jrt.quizsystem.domain.quiz.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class QuizDao {
   private final JdbcTemplate jdbcTemplate;
   private final QuizRowMapper quizRowMapper;

   @Autowired
   public QuizDao(JdbcTemplate jdbcTemplate, QuizRowMapper quizRowMapper) {
      this.jdbcTemplate = jdbcTemplate;
      this.quizRowMapper = quizRowMapper;
   }

   /**
    * add a new taken quiz into table, return the quiz_id of inserted_quiz
    * @param quiz
    * @return
    */
   public int addQuiz(Quiz quiz) {
      // insert
      String query1 = "INSERT INTO Quiz (user_id, category_id, name, score, start_time, end_time)"
            + "values (?, ?, ?, ?, ?, ?)";
      jdbcTemplate.update(query1, quiz.getUserId(), quiz.getCategoryId(), quiz.getName(),
            quiz.getScore(), quiz.getStartTime(), quiz.getEndTime());
      // get quiz_id
      String query2 = "SELECT quiz_id FROM Quiz WHERE user_id = ?";
      List<Integer> list = jdbcTemplate.queryForList(query2,
            Integer.class, quiz.getUserId());
      return list.get(list.size() - 1);
   }

   /**
    * get all quiz of a given user
    * @param userId
    * @return
    */
   public List<Quiz> getAllQuizByUserId(int userId) {
      String query = "SELECT * FROM Quiz WHERE user_id = ?";
      return jdbcTemplate.query(query, quizRowMapper, userId);
   }


   public Quiz getQuizById(int quizId) {
      String query = "SELECT * FROM Quiz WHERE quiz_id = ?";
      List<Quiz> list = jdbcTemplate.query(query, quizRowMapper, quizId);
      return list.get(0);
   }

   /**
    * Update quiz's score and endTime when user submitted the quiz
    * @param quizId
    * @param score
    * @param endTime
    */
   public void updateQuizEndTime(int quizId, int score, Timestamp endTime) {
      String query = "UPDATE Quiz SET score = ?, end_time = ? WHERE quiz_id = ?";
      jdbcTemplate.update(query, score, endTime, quizId);
   }

   public int getCategoryIdByQuizId(int quizId) {
      String query = "SELECT category_id FROM Quiz WHERE quiz_id = ?";
      return jdbcTemplate.queryForObject(query, Integer.class, quizId);
   }

   public int getUserIdByQuizId(int quizId) {
      String query = "SELECT user_id FROM Quiz WHERE quiz_id = ?";
      return jdbcTemplate.queryForObject(query, Integer.class, quizId);
   }

   public List<Quiz> getAllQuizByCategoryId(int categoryId) {
      String query = "SELECT * FROM Quiz WHERE category_id = ?";
      return jdbcTemplate.query(query, quizRowMapper, categoryId);
   }

}
