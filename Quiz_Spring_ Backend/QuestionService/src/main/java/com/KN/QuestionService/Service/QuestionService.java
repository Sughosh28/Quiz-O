package com.KN.QuestionService.Service;



import com.KN.QuestionService.Model.QuestionWrapper;
import com.KN.QuestionService.Model.Questions;
import com.KN.QuestionService.Model.Response;
import com.KN.QuestionService.Repository.QuestionRepo;
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

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int numOfQues) {
        List<Integer> questions= repo.findRandomQuestionsByCategory(category, numOfQues);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> id) {
        List<QuestionWrapper> wrappers= new ArrayList<>();

        List<Questions> questions= new ArrayList<>();

        for(Integer qid:id ){
            questions.add(repo.findById(qid).get());
        }


        for(Questions question:questions){
            QuestionWrapper wrapper= new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestion_title(question.getQuestion_title());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
        wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
        for (Response response : responses) {
            Questions question= repo.findById(response.getId()).get();

            if (response.getResponse().equals(question.getRight_answer())) {
                right++;
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);

    }
}
