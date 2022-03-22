package com.ggoreb.practice.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ggoreb.practice.model.Answer;
import com.ggoreb.practice.model.Question;
import com.ggoreb.practice.model.User;
import com.ggoreb.practice.repository.AnswerRepository;
import com.ggoreb.practice.repository.QuestionRepository;
import com.ggoreb.practice.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerRepository answerRepository;
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	@PostMapping("/signup")
	public String signupPost(@ModelAttribute User user) {
		userRepository.save(user);
		return "redirect:/signin";
	}
	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}
	@PostMapping("/signin")
	public String signinPost(@ModelAttribute User user, HttpSession session,Model model) {
		Optional<User> opt = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
		if(opt.isPresent()) {
			session.setAttribute("user", opt.get());
			String uri =(String) session.getAttribute("requestUri");
			System.out.println(uri+"  컨트롤러 에서 uri 출력");
			return "redirect:"+uri;
		}
		return "redirect:/question/list";
	}
	@GetMapping("/signout")
	public String signout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/mypage")
	public String mypage(HttpSession session,Model model) {
		User  user = (User) session.getAttribute("user");
		List<Question> questions=questionRepository.findByUser(user);
		List<Answer> answers =answerRepository.findByUser(user);
		
	
		model.addAttribute("qSize", questions.size());
		model.addAttribute("aSize", answers.size());
		model.addAttribute("qList", questions);
		model.addAttribute("aList", answers);
		return "mypage";
		
	}
	
	@GetMapping("/mypage2")
	public String mypage2(HttpSession session,Model model) {
		User  user = (User) session.getAttribute("user");
		List<Question> questions=user.getQuestions();
		List<Answer> answers =user.getAnswers();
		model.addAttribute("qList", questions);
		model.addAttribute("aList", answers);
		return "mypage";
		
	}
}
