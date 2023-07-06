package com.jrt.quizsystem.dao.quiz;

import com.jrt.quizsystem.domain.quiz.Choice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ChoiceRowMapper implements RowMapper<Choice> {
   @Override
   public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
      Choice choice = new Choice();
      choice.setChoiceId(rs.getInt("choice_id"));
      choice.setQuestionId(rs.getInt("question_id"));
      choice.setDescription(rs.getString("description"));
      choice.setIsCorrect(rs.getBoolean("is_correct"));
      return choice;
   }
}
