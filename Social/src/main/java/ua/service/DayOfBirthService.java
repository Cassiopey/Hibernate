package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.DayOfBirth;
import ua.form.filter.DayOfBirthFilterForm;

public interface DayOfBirthService {

	void save(DayOfBirth dayOfBirth);

	void save(String name);

	DayOfBirth findByName(String name);

	void deleteByName(String name);

	List<DayOfBirth> findAll();

	void delete(int id);

	Page<DayOfBirth> findAll(Pageable pageable);

	DayOfBirth findOne(int id);

	Page<DayOfBirth> findAll(Pageable pageable, DayOfBirthFilterForm form);

}
