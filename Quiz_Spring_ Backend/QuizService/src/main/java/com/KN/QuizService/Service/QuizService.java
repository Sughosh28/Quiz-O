package com.KN.QuizService.Service;



import com.KN.QuizService.Feign.QuizInterface;
import com.KN.QuizService.Model.QuestionWrapper;
import com.KN.QuizService.Model.Quiz;
import com.KN.QuizService.Model.Response;
import com.KN.QuizService.Repository.QuizRepo;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numOfQues, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numOfQues).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsId(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepo.findById(id).get();
//
         List<Integer> questionId= quiz.getQuestionsId();
         ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionFromId(questionId);

        return questions;
    }


    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {

ResponseEntity<Integer> score=quizInterface.getScore(responses);

        return score;
    }
}
