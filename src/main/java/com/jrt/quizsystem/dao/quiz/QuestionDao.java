package com.jrt.quizsystem.dao.quiz;


import com.jrt.quizsystem.domain.quiz.Question;
import com.jrt.quizsystem.domain.quiz.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.*;

@Repository
public class QuestionDao {
    private final JdbcTemplate jdbcTemplate;
    private final QuestionRowMapper questionRowMapper;
    private final QuizQuestionRowMapper qqRowMapper;

    @Autowired
    public QuestionDao(JdbcTemplate jdbcTemplate, QuestionRowMapper questionRowMapper,
                       QuizQuestionRowMapper qqRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.questionRowMapper = questionRowMapper;
        this.qqRowMapper = qqRowMapper;
    }


    /**
     * Return 4 random questions of a given category
     * @param categoryId
     * @return
     */
    public List<Question> getQuestionsOfCategory(int categoryId) {
        String query = "SELECT * FROM Question WHERE category_id = ? ORDER BY RAND() LIMIT 5";
        return jdbcTemplate.query(query, questionRowMapper, categoryId);
    }

    /**
     * return all the questions in a quiz given quiz_id,
     * sorted in the order_num(i.e. the original appearing order in the quiz)
     * @param quizId
     * @return
     */
    public TreeMap<Integer, Question> getQuestionsOfQuiz(int quizId) {
        String query1 = "SELECT * FROM QuizQuestion WHERE quiz_id = ?";
        // list of question_id
        List<QuizQuestion> list = jdbcTemplate.query(query1, qqRowMapper, quizId);
        TreeMap<Integer, Question> map = new TreeMap<>();
        for(QuizQuestion qq : list) {
            String query2 = "SELECT * FROM Question WHERE question_id = ?";
            map.put(qq.getOrderNum(), jdbcTemplate.queryForObject(query2,
                  questionRowMapper, qq.getQuestionId()));
        }
        return map;
    }

    /**
     * add a question to a given category
     * @param question
     */
    public void addNewQuestion(Question question) {
        String query = "INSERT INTO Question (category_id, description, is_active) values (?, ?, ?)";
        jdbcTemplate.update(query, question.getCategoryId(),
              question.getDescription(), question.getIsActive());
    }

    /**
     * update the description of a question
     * @param questionId
     * @param description
     */
    public void updateQuestion(int questionId, String description) {
        String query = "UPDATE Question SET description = ? WHERE question_id = ?";
        jdbcTemplate.update(query, description, questionId);
    }

    /**
     * update the active status of a question
     * @param questionId
     * @param isActive
     */
    public void updateQuestion(int questionId, boolean isActive) {
        String query = "UPDATE Question SET is_active = ? WHERE question_id = ?";
        jdbcTemplate.update(query, isActive, questionId);
    }

    public int getQuestionIdByDescription(String description) {
        String query = "SELECT question_id FROM Question WHERE description = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, description);
    }

    public List<Question> getAllQuestions() {
        String query = "SELECT * FROM Question";
        return jdbcTemplate.query(query, questionRowMapper);
    }
}
