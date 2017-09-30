package ua.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Post;
import ua.entity.UserPage;
import ua.form.filter.PostFilterForm;
import ua.service.PostService;
import ua.service.UserPageService;
import ua.service.implementation.editor.UserPageEditor;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserPageService userPageService;
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(UserPage.class, new UserPageEditor(userPageService));
	
		
	}
	
	@ModelAttribute("adminPost")
	public Post getPost() {
		return new Post();
	}

	@ModelAttribute(value = "filter")
	public PostFilterForm getFilter() {
		return new PostFilterForm();
	}
	
	@RequestMapping("/post")
	public String showPost(Model model, Pageable pageable, @ModelAttribute(value = "filter") PostFilterForm filter) {
		model.addAttribute("owners", userPageService.findAll())
		.addAttribute("writers", userPageService.findAll()).addAttribute("posts", postService.findAll(pageable, filter));
		return "post";
	}
	
/*	@RequestMapping("/post")
	public String showWriten(Principal principal, Model model){
		model.addAttribute("owners", userPageService.findAll())
		.addAttribute("writers", userPageService.findAll()).addAttribute("posts", postService.findByWriter(principal));
		return "post";
	}*/


	@RequestMapping("/admin/post")
	public String show(Model model, Pageable pageable, @ModelAttribute(value = "filter") PostFilterForm filter) {
		model.addAttribute("owners", userPageService.findAll())
		.addAttribute("writers", userPageService.findAll()).addAttribute("posts", postService.findAll(pageable, filter));
		return "adminPost";
	}
	
	@RequestMapping(value = "/admin/post", method = RequestMethod.POST)
	public String savePost(@ModelAttribute("form") @Valid Post post,BindingResult br, Model model, @PageableDefault(5) Pageable pageable, @ModelAttribute(value = "filter") PostFilterForm filter) {
		if(br.hasErrors()){
			return "adminPost";
		}		
		postService.savePost(post);
		return "redirect:/admin/post";
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid Post post,BindingResult br, Model model, @PageableDefault(5) Pageable pageable, Principal principal,@ModelAttribute(value = "filter") PostFilterForm filter) {
		if(br.hasErrors()){
			model.addAttribute("owners", userPageService.findAll())
			.addAttribute("writers", userPageService.findAll()).addAttribute("posts", postService.findAll(pageable, filter));
			return "post";
		}
		
		postService.save(post,principal);
		return "redirect:/post";
	}




}
