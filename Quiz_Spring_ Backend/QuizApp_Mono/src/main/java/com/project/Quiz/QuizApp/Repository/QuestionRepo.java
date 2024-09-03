package com.project.Quiz.QuizApp.Repository;

import com.project.Quiz.QuizApp.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Questions, Integer> {

   List<Questions> findByCategory(String category);


@Query(value="SELECT * FROM questions q Where q.category= ?1 ORDER BY RANDOM() LIMIT ?2" ,nativeQuery=true)
    List<Questions> findRandomQuestionsByCategory(String category, int numOfQues);
}
