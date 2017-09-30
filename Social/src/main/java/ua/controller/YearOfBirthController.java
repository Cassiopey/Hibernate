package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.YearOfBirth;
import ua.form.filter.YearOfBirthFilterForm;
import ua.service.YearOfBirthService;

@Controller
public class YearOfBirthController {

	@Autowired
	private YearOfBirthService yearOfBirthService;
	
	@ModelAttribute("yearOfBirth")
	public YearOfBirth getYearOfBirth(){
		return new YearOfBirth();
	}
	
	@RequestMapping("/admin/yearOfBirth")
	public String show(Model model, @PageableDefault(size=10)Pageable pageable,@ModelAttribute(value="filter") YearOfBirthFilterForm form) {
		model.addAttribute("page", yearOfBirthService.findAll(pageable, form));
		return "adminYearOfBirth";
	}

	@RequestMapping("/admin/yearOfBirth/delete/{id}")
	public String delete(@PathVariable int id) {
		yearOfBirthService.delete(id);
		return "redirect:/admin/yearOfBirth";
	}

	@RequestMapping("/admin/yearOfBirth/update/{id}")
	public String updateYearOfBirth(@PathVariable int id, Model model, @PageableDefault(size=31) Pageable pageable,@ModelAttribute(value="filter") YearOfBirthFilterForm form) {
		model.addAttribute("yearOfBirth", yearOfBirthService.findOne(id));
		model.addAttribute("page", yearOfBirthService.findAll(pageable, form));
		return "adminYearOfBirth";
	}

	@RequestMapping(value = "/admin/yearOfBirth", method = RequestMethod.POST)
	public String showYearOfBirth(@ModelAttribute("yearOfBirth") YearOfBirth yearOfBirth,
			BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable,@ModelAttribute(value="filter") YearOfBirthFilterForm form) {
		if (br.hasErrors()) {
			model.addAttribute("page", yearOfBirthService.findAll(pageable, form));
			return "adminYearOfBirth";

		}
		yearOfBirthService.save(yearOfBirth);
		return "redirect:/admin/yearOfBirth";
	}
}


