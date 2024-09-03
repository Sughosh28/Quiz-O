package com.KN.QuestionService.Repository;

import com.KN.QuestionService.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Questions, Integer> {

   List<Questions> findByCategory(String category);


@Query(value="SELECT q.id FROM questions q Where q.category= ?1 ORDER BY RANDOM() LIMIT ?2" ,nativeQuery=true)
    List<Integer> findRandomQuestionsByCategory(String category, int numOfQues);
}
