package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.YearOfBirth;
import ua.form.filter.YearOfBirthFilterForm;

public interface YearOfBirthService {

	void save(String name);

	void save(YearOfBirth yearOfBirth);

	void delete(int id);

	void deleteByName(String name);

	YearOfBirth findOne(int id);

	YearOfBirth findByName(String name);

	List<YearOfBirth> findAll();

	Page<YearOfBirth> findAll(Pageable pageable);

	Page<YearOfBirth> findAll(Pageable pageable, YearOfBirthFilterForm form);

}
