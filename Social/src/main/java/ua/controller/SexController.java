package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Sex;
import ua.form.filter.SexFilterForm;
import ua.service.SexService;

@Controller
public class SexController {
	
	@Autowired
	private SexService sexService;
	
	@ModelAttribute("sex")
	public Sex getSex(){
		return new Sex();
	}
	
	@ModelAttribute(value="filter")
	public SexFilterForm getFilter(){
		return new SexFilterForm();
	}
	
	@RequestMapping("/admin/sex")
	public String show(Model model, @PageableDefault(size=5,sort="name") Pageable pageable,@ModelAttribute(value="filter")SexFilterForm form){
		model.addAttribute("page", sexService.findAll(pageable, form));
		return "adminSex";
	}
	
	@RequestMapping("/admin/sex/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault(5) Pageable pageable,@ModelAttribute(value="filter") SexFilterForm form){
		sexService.delete(id);
		return "redirect:/admin/sex"+getParams(pageable, form);
	}
	@RequestMapping("/admin/sex/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault(size=5) Pageable pageable,@ModelAttribute(value="filter")SexFilterForm form){
		model.addAttribute("sex", sexService.findOne(id));
		model.addAttribute("page", sexService.findAll(pageable, form));
		return "adminSex";
	}
	
	@RequestMapping(value= "/admin/sex", method=RequestMethod.POST)
	public String saveCountry(@ModelAttribute("sex") Sex sex, BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable,@ModelAttribute(value="filter") SexFilterForm form){
		if(br.hasErrors()){
			model.addAttribute("page", sexService.findAll(pageable));
			return "adminSex";
}
		sexService.save(sex);
		return "redirect:/admin/sex"+getParams(pageable, form);
}

	private String getParams(Pageable pageable, SexFilterForm form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		buffer.append(form.getSearch());
		return buffer.toString();
	}
}
