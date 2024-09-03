package com.project.Quiz.QuizApp.Service;


import com.project.Quiz.QuizApp.Model.QuestionWrapper;
import com.project.Quiz.QuizApp.Model.Questions;
import com.project.Quiz.QuizApp.Model.Quiz;
import com.project.Quiz.QuizApp.Model.Response;
import com.project.Quiz.QuizApp.Repository.QuestionRepo;
import com.project.Quiz.QuizApp.Repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionRepo repo;

    public ResponseEntity<String> createQuiz(String category, int numOfQues, String title) {

        List<Questions> questions = repo.findRandomQuestionsByCategory(category, numOfQues);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepo.findById(id);

        List<Questions> questionsFromDB = quiz.get().getQuestions();


        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Questions q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion_title(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Questions> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRight_answer())) {
                right++;

            }
                i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
