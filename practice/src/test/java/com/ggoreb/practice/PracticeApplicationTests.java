package com.ggoreb.practice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ggoreb.practice.model.Answer;
import com.ggoreb.practice.model.Question;
import com.ggoreb.practice.model.User;
import com.ggoreb.practice.repository.AnswerRepository;
import com.ggoreb.practice.repository.QuestionRepository;
import com.ggoreb.practice.repository.UserRepository;

@SpringBootTest
class PracticeApplicationTests {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
		
		List<Question> list = questionRepository.findAll();
		System.out.println(list);
	}
	
	@Test
	void test() {
		User user= userRepository.getById(2L);
		Question question = user.getQuestions().get(0);
		String q =question.getContent();
		System.out.println(q);
		
	}
	
	@Test
	void test2() {
		User user = userRepository.getById(2L);
		//Answer answer = user.getAnswers().get(0);
		//String a =answer.getContent();
		//System.out.println(a);
	}
	
	@Test
	void test3() {
		User user =new User();
		user.setId(1L);
		List<Answer> list = answerRepository.findByUser(user);
		Set<Answer> set = new HashSet<Answer>();
		
		Set<Answer> set2 = new HashSet<Answer>(list);
		
		for(Answer i : list) {
			set.add(i);
		}
		System.out.println(set.size());
		System.out.println(set2.size());

		System.out.println(set);
		/*
		System.out.println(set.equals(set2));
		Iterator<Answer> it =set.iterator();
		
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}*/

}
	}
