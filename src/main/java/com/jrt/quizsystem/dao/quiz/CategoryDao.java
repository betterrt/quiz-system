package com.jrt.quizsystem.dao.quiz;

import com.jrt.quizsystem.domain.quiz.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {
   private final JdbcTemplate jdbcTemplate;
   private final CategoryRowMapper categoryRowMapper;

   @Autowired
   public CategoryDao(JdbcTemplate jdbcTemplate, CategoryRowMapper categoryRowMapper) {
      this.jdbcTemplate = jdbcTemplate;
      this.categoryRowMapper = categoryRowMapper;
   }

   /**
    * Find category with given id
    * @param id
    * @return
    */
   public Category findCategoryById(int id) {
      String query = "SELECT * FROM Category WHERE category_id = ?";
      List<Category> list = jdbcTemplate.query(query, categoryRowMapper, id);
      return list.get(0);
   }

   public int getCategoryIdByName(String categoryName) {
      String query = "SELECT * FROM Category WHERE name = ?";
      List<Category> list = jdbcTemplate.query(query, categoryRowMapper, categoryName);
      if(list.size() == 0) {
         return -1;
      }
      return list.get(0).getId();
   }

   /**
    * Get all categories in table Category
    * @return List of category
    */
   public List<Category> getAllCategories() {
      String query = "SELECT * FROM Category";
      List<Category> list = jdbcTemplate.query(query, categoryRowMapper);
      return list;
   }
}
