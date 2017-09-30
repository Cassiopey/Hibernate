package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.MounthOfBirth;
import ua.form.filter.MounthOfBirthFilterForm;

public class MounthOfBirthFilterAdapter implements Specification<MounthOfBirth>{
	private String search = "";

	public MounthOfBirthFilterAdapter(MounthOfBirthFilterForm form) {
		if(form.getSearch()!=null)
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<MounthOfBirth> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		return cb.like(cb.upper(root.get("name")), search.toUpperCase()+"%");
	}
	
	
}
