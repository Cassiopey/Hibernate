package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import ua.service.implementation.validator.RegestrationValidator;

@Controller
public class UserPageController {
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

	@InitBinder("form")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
		binder.registerCustomEditor(Sex.class, new SexEditor(sexService));
		binder.registerCustomEditor(DayOfBirth.class, new DayOfBirthEditor(dayOfBirthService));
		binder.registerCustomEditor(MounthOfBirth.class, new MounthOfBirthEditor(mounthOfBirthService));
		binder.registerCustomEditor(YearOfBirth.class, new YearOfBirthEditor(yearOfBirthService));
		binder.setValidator(new RegestrationValidator(userPageService));
	}

	@ModelAttribute("form")
	public UserPageForm getForm() {
		return new UserPageForm();
	}

	
	

	@RequestMapping("/registration")
	public String register(Model model) {
		model.addAttribute("countries", countryService.findAll()).addAttribute("daysOfBirth", dayOfBirthService.findAll()).addAttribute("mounsesOfBirth", mounthOfBirthService.findAll()).addAttribute("yearsOfBirth", yearOfBirthService.findAll())
				.addAttribute("sexs", sexService.findAll());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid UserPageForm form, BindingResult br,Model model) {
		if(br.hasErrors()){
			model.addAttribute("countries", countryService.findAll()).addAttribute("daysOfBirth", dayOfBirthService.findAll()).addAttribute("mounsesOfBirth", mounthOfBirthService.findAll()).addAttribute("yearsOfBirth", yearOfBirthService.findAll())
			.addAttribute("sexs", sexService.findAll());
			return "registration";
		}
		userPageService.save(form);
		return "redirect:/login";
	}

	@ModelAttribute("filter")
	public UserPageFilterForm getFilter() {
		return new UserPageFilterForm();
	}

	@RequestMapping("/admin/userPage")
	public String showUserPages(Model model, Pageable pageable, @ModelAttribute(value = "filter") UserPageFilterForm filter) {
		model.addAttribute("countries", countryService.findAll()).addAttribute("daysOfBirth", dayOfBirthService.findAll()).addAttribute("mounsesOfBirth", mounthOfBirthService.findAll()).addAttribute("yearsOfBirth", yearOfBirthService.findAll())
				.addAttribute("sexs", sexService.findAll()).addAttribute("userPages", userPageService.findAll(pageable, filter));
		return "adminUserPage";
	}

	@RequestMapping(value = "/admin/userPage", method = RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid UserPageForm form, Model model, BindingResult br, @PageableDefault(5) Pageable pageable, @ModelAttribute(value = "filter") UserPageFilterForm filter) {
		if (br.hasErrors()) {
			return "adminUserPage";
		}
		userPageService.save(form);
		return "redirect:/admin/userPage" + getParams(pageable, filter);
	}

	@RequestMapping(value = "/admin/userPage/update/{id}")
	public String update(Model model, @PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value = "filter") UserPageFilterForm filter) {
		model.addAttribute("form", userPageService.findForForm(id));
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("sexs", sexService.findAll());
		model.addAttribute("daysOfBirch", dayOfBirthService.findAll());
		model.addAttribute("mounsesOfBirth", mounthOfBirthService.findAll());
		model.addAttribute("yearsOfBirth", yearOfBirthService.findAll());
		model.addAttribute("userPages", userPageService.findAll(pageable, filter));
		return "adminUserPage";
	}

	@RequestMapping(value = "/admin/userPage/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault(5) Pageable pageable, @ModelAttribute(value = "filter") UserPageFilterForm filter) {
		userPageService.delete(id);
		return "redirect:/admin/userPage" + getParams(pageable, filter);
	}

	private String getParams(Pageable pageable, UserPageFilterForm form) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber() + 1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if (pageable.getSort() != null) {
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order) -> {
				buffer.append(order.getProperty());
				if (order.getDirection() != Direction.ASC)
					buffer.append(",desc");
			});
		}

		for (Integer i : form.getCountryIds()) {
			buffer.append("&countryIds=");
			buffer.append(i.toString());
		}
		for (Integer i : form.getSexIds()) {
			buffer.append("&sexIds=");
			buffer.append(i.toString());
		}
		for (Integer i : form.getDayOfBirthIds()) {
			buffer.append("&dayOfBirthIds=");
			buffer.append(i.toString());
		}
		for (Integer i : form.getMounthOfBirthIds()) {
			buffer.append("&mounthOfBirthIds=");
			buffer.append(i.toString());
		}
		for (Integer i : form.getYearOfBirthIds()) {
			buffer.append("&yearOfBirthIds=");
			buffer.append(i.toString());
		}
		return buffer.toString();
	}
}
