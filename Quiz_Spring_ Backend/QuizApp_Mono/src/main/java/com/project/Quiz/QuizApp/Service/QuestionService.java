package com.project.Quiz.QuizApp.Service;


import com.project.Quiz.QuizApp.Model.Questions;
import com.project.Quiz.QuizApp.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {


    @Autowired
    private QuestionRepo repo;

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
        e.printStackTrace();
        }
           return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public List<Questions> getAllQuestionsByCategory(String category) {
        return repo.findByCategory(category);
    }

    public String addQuestion(Questions question) {
         repo.save(question);
         return "Success";
    }

    public String removeQuestion(int id) {
        repo.deleteById(id);
        return "Success";
    }

    public Questions updateQuestions( Questions question) {
          repo.save(question);
          return question;


    }
}
