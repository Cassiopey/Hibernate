package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.Country;
import ua.entity.DayOfBirth;
import ua.entity.MounthOfBirth;
import ua.entity.Sex;
import ua.entity.YearOfBirth;
import ua.form.UserPageForm;
import ua.form.filter.UserPageFilterForm;
import ua.service.CountryService;
import ua.service.DayOfBirthService;
import ua.service.MounthOfBirthService;
import ua.service.SexService;
import ua.service.UserPageService;
import ua.service.YearOfBirthService;
import ua.service.implementation.editor.CountryEditor;
import ua.service.implementation.editor.DayOfBirthEditor;
import ua.service.implementation.editor.MounthOfBirthEditor;
import ua.service.implementation.editor.SexEditor;
import ua.service.implementation.editor.YearOfBirthEditor;
@Controller
public class PeopleSearchController {
	
	@Autowired
	private UserPageService userPageService;
	
	@Autowired
	private CountryService countryService;

	@Autowired
	private SexService sexService;

	@Autowired
	private DayOfBirthService dayOfBirthService;

	@Autowired
	private MounthOfBirthService mounthOfBirthService;
	
	@Autowired
	private YearOfBirthService yearOfBirthService;
	

	@ModelAttribute("form")
	public UserPageForm getForm() {
		return new UserPageForm();
	}
	
	@ModelAttribute("filter")
	public UserPageFilterForm getFilter() {
		return new UserPageFilterForm();
	}
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
		binder.registerCustomEditor(Sex.class, new SexEditor(sexService));
		binder.registerCustomEditor(DayOfBirth.class, new DayOfBirthEditor(dayOfBirthService));
		binder.registerCustomEditor(MounthOfBirth.class, new MounthOfBirthEditor(mounthOfBirthService));
		binder.registerCustomEditor(YearOfBirth.class, new YearOfBirthEditor(yearOfBirthService));

	}
	
	@RequestMapping("/peopleSearch")
	public String showUserPages(Model model, Pageable pageable, @ModelAttribute(value = "filter") UserPageFilterForm filter) {
		model.addAttribute("countries", countryService.findAll()).addAttribute("daysOfBirth", dayOfBirthService.findAll()).addAttribute("mounsesOfBirth", mounthOfBirthService.findAll()).addAttribute("yearsOfBirth", yearOfBirthService.findAll())
		.addAttribute("sexs", sexService.findAll()).addAttribute("userPages", userPageService.findAll(pageable, filter));
		return "peopleSearch";
	}
}
