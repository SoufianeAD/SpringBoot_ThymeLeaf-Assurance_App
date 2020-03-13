package com.spring.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.main.entities.User;
import com.spring.main.repositories.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "Login";
	}
	
	@GetMapping("/homeForm")
	public String homeFrom() {
		return "Home";
	}
	
	@PostMapping("/login")
	public String login(Model model, @RequestParam("userName") String userName, @RequestParam("password") String password) {
		User user = userRepository.findByUserNameAndPassword(userName, password);
		if(user == null) {
			model.addAttribute("message", "Nom d'utilisateur ou mot de passe incorrecte!");
			return "redirect:/user/loginForm";
		}
		
		return "redirect:/user/homeForm";
	}
}
