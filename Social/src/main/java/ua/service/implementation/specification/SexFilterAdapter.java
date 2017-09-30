package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Sex;
import ua.form.filter.SexFilterForm;

public class SexFilterAdapter implements Specification<Sex>{

	private String search = "";

	public SexFilterAdapter(SexFilterForm form) {
		if(form.getSearch()!=null)
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<Sex> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		return cb.like(cb.upper(root.get("name")), search.toUpperCase()+"%");
	}
}
