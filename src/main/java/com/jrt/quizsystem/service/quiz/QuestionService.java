package com.jrt.quizsystem.service.quiz;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.jrt.quizsystem.dao.quiz.QuestionDao;
import com.jrt.quizsystem.domain.quiz.Choice;
import com.jrt.quizsystem.domain.quiz.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Service
public class QuestionService {
    private final QuestionDao questionDao;
    private final ChoiceService choiceService;

    @Autowired
    public QuestionService(QuestionDao questionDao, ChoiceService choiceService) {
        this.questionDao = questionDao;
        this.choiceService = choiceService;
    }

    public List<Question> getQuestionsOfCategory(int categoryId) {
        return questionDao.getQuestionsOfCategory(categoryId);
    }

    public List<Question> getQuestionsOfQuiz(int quizId) {
        List<Question> list = new ArrayList<>(questionDao.getQuestionsOfQuiz(quizId).values());
        return list;
    }

    public void createNewQuestion(int categoryId, String description) {
        Question question = new Question();
        question.setCategoryId(categoryId);
        question.setDescription(description);
        question.setIsActive(true);
        questionDao.addNewQuestion(question);
    }

    public int getQuestionIdByDescription(String description) {
        return questionDao.getQuestionIdByDescription(description);
    }

    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    public void editQuestionDesc(int questionId, String description) {
        questionDao.updateQuestion(questionId, description);
    }

    public void disableQuestion(int questionId) {
        questionDao.updateQuestion(questionId, false);
    }

    public void enableQuestion(int questionId) {
        questionDao.updateQuestion(questionId, true);
    }

}
