package com.jrt.quizsystem.service.quiz;

import com.jrt.quizsystem.dao.quiz.CategoryDao;
import com.jrt.quizsystem.dao.quiz.QuizDao;
import com.jrt.quizsystem.domain.quiz.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
   private final CategoryDao categoryDao;
   private final QuizDao quizDao;

   @Autowired
   public CategoryService(CategoryDao categoryDao, QuizDao quizDao) {
      this.categoryDao = categoryDao;
      this.quizDao = quizDao;
   }

   public Category getCategoryByQuizId(int quizId) {
      int categoryId = quizDao.getCategoryIdByQuizId(quizId);
      return categoryDao.findCategoryById(categoryId);
   }

   public List<Category> getAllCategories() {
      return categoryDao.getAllCategories();
   }

   public int getCategoryIdByName(String categoryName) {
      return categoryDao.getCategoryIdByName(categoryName);
   }
}
