package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.DayOfBirth;
import ua.form.filter.DayOfBirthFilterForm;

public class DayOfBirthFilterAdapter implements Specification<DayOfBirth>{
	private String search = "";

	public DayOfBirthFilterAdapter(DayOfBirthFilterForm form) {
		if(form.getSearch()!=null)
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<DayOfBirth> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		return cb.like(cb.upper(root.get("name")), search.toUpperCase()+"%");
	}
	
	
}
