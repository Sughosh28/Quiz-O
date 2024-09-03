package com.project.Quiz.QuizApp.Controller;


import com.project.Quiz.QuizApp.Model.QuestionWrapper;
import com.project.Quiz.QuizApp.Model.Questions;
import com.project.Quiz.QuizApp.Model.Response;
import com.project.Quiz.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService service;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz( @RequestParam String category, @RequestParam int numOfQues, @RequestParam String title){
        return service.createQuiz(category,numOfQues,title);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return service.getQuizQuestions(id);
    }


    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){

        return service.calculateResult(id, responses);
    }

}
