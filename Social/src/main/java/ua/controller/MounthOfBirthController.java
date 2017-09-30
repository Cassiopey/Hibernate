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

import ua.entity.MounthOfBirth;
import ua.form.filter.MounthOfBirthFilterForm;
import ua.service.MounthOfBirthService;

@Controller
public class MounthOfBirthController {

	@Autowired
	private MounthOfBirthService mounthOfBirthService;

	@ModelAttribute("mounthOfBirth")
	public MounthOfBirth getMounthOfBirth() {
		return new MounthOfBirth();
	}

	@RequestMapping("/admin/mounthOfBirth")
	public String show(Model model,
			@PageableDefault(size = 31) Pageable pegeable,
			@ModelAttribute(value = "filter") MounthOfBirthFilterForm form) {
		model.addAttribute("page", mounthOfBirthService.findAll(pegeable, form));
		return "adminMounthOfBirth";
	}

	@RequestMapping("admin/mounthOfBirth/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault(5) Pageable pageable,@ModelAttribute(value="filter") MounthOfBirthFilterForm form) {
		mounthOfBirthService.delete(id);
		return "redirect:/admin/mounthOfBirth";
	}

	@RequestMapping("admin/mounthOfBirth/update/{id}")
	public String updateMounthOfBirth(@PathVariable int id, Model model,
			@PageableDefault(size = 12) Pageable pageable,
			@ModelAttribute(value = "filter") MounthOfBirthFilterForm form) {
		model.addAttribute("mounthOfBirth", mounthOfBirthService.findOne(id));
		model.addAttribute("page", mounthOfBirthService.findAll(pageable));
		return "adminMounthOfBirth";
	}

	@RequestMapping(value = "/admin/mounthOfBirth", method = RequestMethod.POST)
	public String showMounthOfBirth(
			@ModelAttribute("mounthOfBirth") MounthOfBirth mounthOfBirth,
			BindingResult br, Model model,
			@PageableDefault(size = 5) Pageable pageable,
			@ModelAttribute(value = "filter") MounthOfBirthFilterForm form) {
		if (br.hasErrors()) {
			model.addAttribute("page", mounthOfBirthService.findAll(pageable));
			return "adminMounthOfBirth";
		}
		mounthOfBirthService.save(mounthOfBirth);
		return "redirect:/admin/mounthOfBirth";
	}
}