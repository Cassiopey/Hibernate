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

import ua.entity.DayOfBirth;
import ua.form.filter.DayOfBirthFilterForm;
import ua.service.DayOfBirthService;

@Controller
public class DayOfBirthController {

	@Autowired
	private DayOfBirthService dayOfBirthService;
	
	@ModelAttribute("dayOfBirth")
	public DayOfBirth getDayOfBirth(){
		return new DayOfBirth();
	}
	
	@RequestMapping("/admin/dayOfBirth")
	public String show(Model model, @PageableDefault(size=31)Pageable pageable,@ModelAttribute(value="filter")DayOfBirthFilterForm form){
		model.addAttribute("page", dayOfBirthService.findAll(pageable, form));
		return "adminDayOfBirth";
	}
	
	@RequestMapping("admin/dayOfBirth/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault(5) Pageable pageable,@ModelAttribute(value="filter") DayOfBirthFilterForm form){
		dayOfBirthService.delete(id);
		return "redirect:/admin/dayOfBirth";
	}

	@RequestMapping("admin/dayOfBirth/update/{id}")
	public String updateDayOfBirth(@PathVariable int id, Model model, @PageableDefault(size=31) Pageable pageable,@ModelAttribute(value="filter")DayOfBirthFilterForm form){
		model.addAttribute("dayOfBirth", dayOfBirthService.findOne(id));
		model.addAttribute("page", dayOfBirthService.findAll(pageable, form));
		return "adminDayOfBirth";
	}
	@RequestMapping(value="/admin/dayOfBirth", method=RequestMethod.POST)
	public String saveDayOfBirth(@ModelAttribute("dayOfBirth")DayOfBirth dayOfBirth, BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable,@ModelAttribute(value="filter")DayOfBirthFilterForm form){
		if(br.hasErrors()){
			model.addAttribute("page", dayOfBirthService.findAll(pageable, form));
			return "adminDayOfBirth";
		}
		dayOfBirthService.save(dayOfBirth);
		return "redirect:/admin/dayOfBirth";
	}
}

