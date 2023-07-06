package com.jrt.quizsystem.dao.quiz;

import com.jrt.quizsystem.domain.quiz.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CategoryRowMapper implements RowMapper<Category> {
   @Override
   public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
      Category category = new Category();
      category.setId(rs.getInt("category_id"));
      category.setName(rs.getString("name"));
      category.setImageUrl(rs.getString("image_url"));
      return category;
   }
}
