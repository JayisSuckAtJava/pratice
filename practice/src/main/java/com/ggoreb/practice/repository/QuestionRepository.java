package com.ggoreb.practice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggoreb.practice.model.Question;
import com.ggoreb.practice.model.User;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	List<Question> findByUser(User user);

}
