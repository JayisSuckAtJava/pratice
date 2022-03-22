package com.ggoreb.practice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggoreb.practice.model.Answer;
import com.ggoreb.practice.model.Question;
import com.ggoreb.practice.model.User;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
	List<Answer> findByQuestion(Question question);
	List<Answer> findByUser(User user);

}
