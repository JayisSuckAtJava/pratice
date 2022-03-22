package com.ggoreb.practice.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ggoreb.practice.model.Answer;
import com.ggoreb.practice.model.User;
import com.ggoreb.practice.repository.AnswerRepository;

@Controller
public class AnswerController {

	@Autowired
	AnswerRepository answerRepository;
	
	@PostMapping("/answer/create")
	public String method(Answer answer,HttpSession session) {
		 User id = (User) session.getAttribute("user");
		 answer.setUser(id);
		 answer.setCreateDate(new Date());
		 answerRepository.save(answer);
		
		
		//answerRepository.save(answer);
		return "redirect:/question/detail?id="+answer.getQuestion().getId();
	}
	
	@GetMapping("/answer/update")
	public String update(@RequestParam("id") Long id,Model model) {
		Optional<Answer> answer = answerRepository.findById(id);
		model.addAttribute("answer", answer.get());
		
		return "answer_update";
	}
	
	@PostMapping("/answer/update")
	public String update(Answer data) {
		Optional<Answer> answer = answerRepository.findById(data.getId());
		Answer result =answer.get();
		result.setContent(data.getContent());
		answerRepository.save(result);
		
		return "redirect:/question/detail?id="+result.getQuestion().getId();
	}
}
