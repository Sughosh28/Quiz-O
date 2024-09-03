package com.project.Quiz.QuizApp.Controller;


import com.project.Quiz.QuizApp.Model.Questions;
import com.project.Quiz.QuizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions(){
        return service.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getAllQuestionsByCategory(@PathVariable String category){
        return new ResponseEntity<>(service.getAllQuestionsByCategory(category), HttpStatus.OK);
    }
    @PutMapping("/updateQuestion{id}")
    public ResponseEntity<Questions> updateQuestions( @PathVariable int id, @RequestBody Questions question){
        return  new ResponseEntity<>(service.updateQuestions( question), HttpStatus.ACCEPTED);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity< String> addQuestion(@RequestBody  Questions question){
        return new ResponseEntity<>( service.addQuestion(question), HttpStatus.CREATED);
    }


    @DeleteMapping("/removeQuestion{id}")
    public ResponseEntity<String> removeQuestion(@PathVariable int id){
       return new ResponseEntity<>(service.removeQuestion(id), HttpStatus.OK);
    }

}
