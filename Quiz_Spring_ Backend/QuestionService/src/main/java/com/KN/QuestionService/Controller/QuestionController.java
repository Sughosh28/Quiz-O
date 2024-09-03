package com.KN.QuestionService.Controller;



import com.KN.QuestionService.Model.QuestionWrapper;
import com.KN.QuestionService.Model.Questions;
import com.KN.QuestionService.Model.Response;
import com.KN.QuestionService.Service.QuestionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    //generate quiz
    //get question for particular quiz


    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numOfQues){
        return service.getQuestionsForQuiz(category, numOfQues);

    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> id){
        return service.getQuestionsFromId(id);
    }


    @PostMapping("/getScore")

    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return service.getScore(responses);
    }

}
