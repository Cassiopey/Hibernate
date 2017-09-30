package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Sex;
import ua.form.filter.SexFilterForm;

public interface SexService {

	void save(String name);

	Sex findByName(String name);

	void delete(String name);

	List<Sex> findAll();

	void delete(int id);

	Page<Sex> findAll(Pageable pageable);

	Sex findOne(int id);

	void save(Sex sex);

	Page<Sex> findAll(Pageable pageable, SexFilterForm form);
}
