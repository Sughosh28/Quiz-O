package com.project.Quiz.QuizApp.Repository;


import com.project.Quiz.QuizApp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {



}
