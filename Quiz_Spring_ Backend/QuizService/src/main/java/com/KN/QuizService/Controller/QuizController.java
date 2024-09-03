package com.KN.QuizService.Controller;



import com.KN.QuizService.Model.QuestionWrapper;
import com.KN.QuizService.Model.QuizDTO;
import com.KN.QuizService.Model.Response;
import com.KN.QuizService.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService service;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz( @RequestBody QuizDTO dto){
        return service.createQuiz(dto.getCategory(), dto.getNumOfQues(), dto.getTitle());
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
