package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.form.UserPageForm;
import ua.service.PostService;
import ua.service.UserPageService;

@Controller
public class IndexController {
	
	@Autowired
	private UserPageService userPageService;
	
	@Autowired
	private PostService postService;

	@RequestMapping("/")
	public String showIndex(Principal principal, Model model){
		model.addAttribute("writers", userPageService.findAll()).addAttribute("owners", userPageService.findAll()).addAttribute("posts", postService.findByOwners(principal));
		return "index";
	}
	
	@ModelAttribute("form")
	public UserPageForm getForm() {
		return new UserPageForm();
	}
	
	@RequestMapping("/admin")
	public String showAdmin() {
		return "adminPanel";
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
}
